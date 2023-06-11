package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.AwardDTO;
import com.watchworthy.api.entity.Award;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.repository.AwardRepository;
import com.watchworthy.api.repository.MovieRepository;
import com.watchworthy.api.service.AwardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AwardServiceImpl implements AwardService {
    private final AwardRepository awardRepository;
    private final MovieRepository movieRepository;

    public AwardServiceImpl(AwardRepository awardRepository, MovieRepository movieRepository) {
        this.awardRepository = awardRepository;
        this.movieRepository = movieRepository; // Assign the injected MovieRepository to the field
    }
    @Override
    public AwardDTO getAwardDetails(Long id) {
        Optional<Award> optionalAward = awardRepository.findById(id);
        Award award = optionalAward.orElseThrow(NoSuchElementException::new);
        return convertToAwardDTO(award);
    }


    @Override
    public List<AwardDTO> getAwards() {
        List<Award> awards = awardRepository.findAll();
        return convertToAwardDTOList(awards);
    }

    @Override
    public void saveAward(AwardDTO awardDto) {
        Award award = convertToAwardEntity(awardDto);
        awardRepository.save(award);
    }

    @Override
    public AwardDTO updateAward(Long id, AwardDTO awardDto) {
        Award existingAward = awardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Award not found"));

        existingAward.setName(awardDto.getName());
        existingAward.setCategory(awardDto.getCategory());
        existingAward.setWinner(awardDto.isWinner());
        existingAward.setYear(awardDto.getYear());
        existingAward.setDescription(awardDto.getDescription());
        existingAward.setMovie(getMovieEntity(awardDto.getMovieId())); // Assuming you have a method to retrieve the Movie entity

        Award updatedAward = awardRepository.save(existingAward);
        return convertToAwardDTO(updatedAward);
    }

    @Override
    public void deleteAward(Long id) {
        awardRepository.deleteById(id);
    }

    // Helper methods for converting between entities and DTOs

    private AwardDTO convertToAwardDTO(Award award) {
        AwardDTO awardDto = new AwardDTO();
        awardDto.setId(award.getId());
        awardDto.setName(award.getName());
        awardDto.setCategory(award.getCategory());
        awardDto.setWinner(award.isWinner());
        awardDto.setYear(award.getYear());
        awardDto.setDescription(award.getDescription());
        awardDto.setMovieId(award.getMovie().getId()); // Assuming you have a method to retrieve the ID of the associated Movie

        return awardDto;
    }

    private List<AwardDTO> convertToAwardDTOList(List<Award> awards) {
        List<AwardDTO> awardDtos = new ArrayList<>();
        for (Award award : awards) {
            awardDtos.add(convertToAwardDTO(award));
        }
        return awardDtos;
    }

    private Award convertToAwardEntity(AwardDTO awardDto) {
        Award award = new Award();
        award.setName(awardDto.getName());
        award.setCategory(awardDto.getCategory());
        award.setWinner(awardDto.isWinner());
        award.setYear(awardDto.getYear());
        award.setDescription(awardDto.getDescription());
        award.setMovie(getMovieEntity(awardDto.getMovieId())); // Assuming you have a method to retrieve the Movie entity

        return award;
    }

    private Movie getMovieEntity(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId.intValue()); // Convert Long to Integer
        return movieOptional.orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }


}

