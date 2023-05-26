package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.SeasonDTO;
import com.watchworthy.api.entity.Season;
import com.watchworthy.api.entity.TvShow;
import com.watchworthy.api.repository.SeasonRepository;
import com.watchworthy.api.repository.TvShowRepository;
import com.watchworthy.api.service.SeasonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;
    private final TvShowRepository tvShowRepository;
    private final ModelMapper modelMapper;

    public SeasonServiceImpl(SeasonRepository seasonRepository, TvShowRepository tvShowRepository, ModelMapper modelMapper) {
        this.seasonRepository = seasonRepository;
        this.tvShowRepository = tvShowRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(Integer tvId, SeasonDTO seasonDTO) {
        TvShow tvShow = tvShowRepository.findById(tvId)
                .orElseThrow(() -> new NoSuchElementException("TV show not found"));
        Season season = convertToEntity(seasonDTO);
        season.setTvshow(tvShow);
        tvShow.getSeasons().add(season);
        seasonRepository.save(season);
        tvShowRepository.save(tvShow);
    }

    @Override
    public void update(Integer seasonId, SeasonDTO seasonDTO) {
        Season season = seasonRepository.findById(seasonId).orElse(null);
        if (season != null) {
            seasonDTO.setId(seasonId);
            seasonRepository.save(convertToEntity(seasonDTO));
        }
    }

    @Override
    public SeasonDTO getSeason(Integer id) {
        Season season = seasonRepository.findById(id).orElse(null);
        return season != null ? convertToDto(season) : null;
    }

    @Override
    public List<SeasonDTO> getSeasons(Integer tvId) {
        List<Season> seasons = seasonRepository.findAll()
                .stream()
                .filter(x -> {
                    TvShow tvShow = x.getTvshow();
                    return tvShow != null && tvShow.getId().equals(tvId);
                })
                .toList();

        if (seasons.isEmpty()) {
            return new ArrayList<>();
        }

        return seasons.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        seasonRepository.findById(id).ifPresent(season -> seasonRepository.deleteById(id));
    }

    public SeasonDTO convertToDto(Season season) {
        return modelMapper.map(season, SeasonDTO.class);
    }

    public Season convertToEntity(SeasonDTO seasonDTO) {
        return modelMapper.map(seasonDTO, Season.class);
    }
}
