package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.*;
import com.watchworthy.api.repository.SeasonRepository;
import com.watchworthy.api.repository.TvShowGenreRepository;
import com.watchworthy.api.repository.TvShowPersonRepository;
import com.watchworthy.api.repository.TvShowRepository;
import com.watchworthy.api.service.TvShowService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TvShowServiceImpl implements TvShowService {
    private final TvShowRepository tvShowRepository;
    private final TvShowGenreRepository tvShowGenreRepository;
    private final TvShowPersonRepository tvShowPersonRepository;
    private final ModelMapper modelMapper;

    public TvShowServiceImpl(TvShowRepository tvShowRepository, SeasonRepository seasonRepository, TvShowGenreRepository tvShowGenreRepository, TvShowPersonRepository tvShowPersonRepository, ModelMapper modelMapper) {
        this.tvShowRepository = tvShowRepository;
        this.tvShowGenreRepository = tvShowGenreRepository;
        this.tvShowPersonRepository = tvShowPersonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(TvShowDTO tvShowDTO) {
        tvShowRepository.save(convertToEntity(tvShowDTO));
    }

    @Override
    public void update(Integer tvShowId, TvShowDTO tvShowDTO) {
        TvShow tvShow = tvShowRepository.findById(tvShowId).orElse(null);
        if (tvShow != null) {
            tvShowDTO.setId(tvShowId);
            tvShowRepository.save(convertToEntity(tvShowDTO));
        }
    }

    @Override
    public TvShowDTO getTvShow(Integer id) {
        TvShow tvShow = tvShowRepository.findById(id).orElse(null);
        return tvShow != null ? convertToDto(tvShow) : null;
    }

    @Override
    public List<TvShowDTO> getTvShows() {
        List<TvShow> tvShows = tvShowRepository.findAll();
        return tvShows.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        tvShowRepository.findById(id).ifPresent(tvShow -> tvShowRepository.deleteById(id));
    }

    @Override
    public void addGenre(TvShowGenreDTO tvShowGenreDTO) {
    tvShowGenreRepository.save(tvShowGenreDTOToEntity(tvShowGenreDTO));
    }

    @Override
    public void addPersonToTvShow(TvShowPersonDTO tvShowPersonDTO) {
    tvShowPersonRepository.save(tvSHowPersonToEntity(tvShowPersonDTO));
    }

//    @Override
//    public void getSeasons(Integer tvShowId) {
//
//    }
//
//    @Override
//    public void getPeople(Integer tvShowId) {
//
//    }

    public TvShowDTO convertToDto(TvShow tvShow) {
        return modelMapper.map(tvShow, TvShowDTO.class);
    }

    public TvShow convertToEntity(TvShowDTO tvShowDTO) {
        return modelMapper.map(tvShowDTO, TvShow.class);
    }

    public TvShowGenre tvShowGenreDTOToEntity(TvShowGenreDTO tvShowGenreDTO) {
        return modelMapper.map(tvShowGenreDTO, TvShowGenre.class);
    }

    public TvShowPerson tvSHowPersonToEntity(TvShowPersonDTO tvShowPersonDTO) {
        return modelMapper.map(tvShowPersonDTO, TvShowPerson.class);
    }
}
