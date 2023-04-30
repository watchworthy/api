package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.MovieRepository;
import com.watchworthy.api.repository.MovieSpecification;
import com.watchworthy.api.service.MovieService;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Override
    public MovieDTO getMovieDetails(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            return convertToDto(movie);
        }
        return null;
    }

    @Override
    public PageModel<MovieDTO> getMovies(Integer page, Integer size, String q) {
        page = page != null ? Math.max(page - 1, 0) : 0;
        size = size != null && size > 0 ? size : 20;

        Pageable pageable = PageRequest.of(page, size);

        Specification<Movie> specification = StringUtils.isBlank(q) ? null : new MovieSpecification(q);
        Page<Movie> moviePage = movieRepository.findAll(specification, pageable);

        return PageModel.<MovieDTO>builder()
                .total(moviePage.getTotalElements())
                .size(moviePage.getSize())
                .page(moviePage.getNumber() + 1)
                .data(moviePage.map(this::convertToDto).getContent())
                .build();
    }

    @Override
    public void save(MovieDTO movieDTO)  {
        movieRepository.save(convertToEntity(movieDTO));
    }

    @Override
    public MovieDTO updateMovie(Integer id, MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movieDTO.setId(movie.getId());
            movieRepository.save(convertToEntity(movieDTO));
            return movieDTO;
        } else {
//            TODO: add proper exception handling
            throw new RuntimeException("Movie with id " + id + " not found");
        }
    }

    public MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    public Movie convertToEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

}
