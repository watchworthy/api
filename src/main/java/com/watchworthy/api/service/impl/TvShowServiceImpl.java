package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.*;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.*;
import com.watchworthy.api.service.TvShowService;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PageModel<TvShowDTO> getTvShows(Integer page, Integer size, String q) {
        page = page != null ? Math.max(page - 1, 0) : 0;
        size = size != null && size > 0 ? size : 20;

        Pageable pageable = PageRequest.of(page, size);

        Specification<TvShow> specification = StringUtils.isBlank(q) ? null : new TvShowSpecification(q);
        Page<TvShow> tvShows = tvShowRepository.findAll(specification, pageable);

        return PageModel.<TvShowDTO>builder()
                .total(tvShows.getTotalElements())
                .size(tvShows.getSize())
                .page(tvShows.getNumber() + 1)
                .data(tvShows.map(this::convertToDto).getContent())
                .build();
    }

    @Override
    public List<TvShowDTO> getTvShowsByPerson(Integer personId) {
        List<TvShowPerson> tvShowPersonList = tvShowPersonRepository.findByPersonId(personId);
        List<TvShow> tvShows = new ArrayList<>();

        for (TvShowPerson tvShowPerson : tvShowPersonList) {
            Integer tvShowId = tvShowPerson.getTvshow().getId();
            tvShowRepository.findById(tvShowId).ifPresent(tvShows::add);
        }

        List<TvShowDTO> tvShowDTOs = new ArrayList<>();
        for(TvShow tvShow: tvShows){
            TvShowDTO tvShowDTO = convertToDto(tvShow);
            tvShowDTOs.add(tvShowDTO);
        }
        return tvShowDTOs;
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
