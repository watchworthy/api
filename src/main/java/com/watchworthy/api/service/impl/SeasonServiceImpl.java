package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.SeasonDTO;
import com.watchworthy.api.entity.Season;
import com.watchworthy.api.entity.TvShow;
import com.watchworthy.api.repository.SeasonRepository;
import com.watchworthy.api.repository.TvShowRepository;
import com.watchworthy.api.service.SeasonService;
import com.watchworthy.api.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;
    private final TvShowRepository tvShowRepository;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    public SeasonServiceImpl(SeasonRepository seasonRepository, TvShowRepository tvShowRepository, ModelMapper modelMapper, StorageService storageService) {
        this.seasonRepository = seasonRepository;
        this.tvShowRepository = tvShowRepository;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }

    @Override
    public void save(Integer tvId, SeasonDTO seasonDTO) {
        TvShow tvShow = tvShowRepository.findById(tvId)
                .orElseThrow(() -> new NoSuchElementException("TV show not found"));
        Season season = convertToEntity(seasonDTO);
        season.setTvshow(tvShow);
        seasonRepository.save(season);
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

    @Override
    public void addPoster(Integer seasonId, MultipartFile file) {
        String posterPath = storageService.uploadFile(file);
        Season season = seasonRepository.findById(seasonId).orElse(null);
        if(season != null){
            season.setPosterPath(posterPath);
            seasonRepository.save(season);
        }
    }

//    public SeasonDTO convertToDto(Season season) {
//        return modelMapper.map(season, SeasonDTO.class);
//    }


    public SeasonDTO convertToDto(Season season){
        return SeasonDTO.builder()
                .overview(season.getOverview())
                .seasonNumber(season.getSeasonNumber())
                .posterPath(season.getPosterPath())
                .airDate(season.getAirDate())
                .id(season.getId())
                .name(season.getName())
                .build();
    }
//    public Season convertToEntity(SeasonDTO seasonDTO) {
//        return modelMapper.map(seasonDTO, Season.class);
//    }
    public Season convertToEntity(SeasonDTO seasonDTO){
        return Season.builder()
                .airDate(seasonDTO.getAirDate())
                .seasonNumber(seasonDTO.getSeasonNumber())
                .overview(seasonDTO.getOverview())
                .id(seasonDTO.getId())
                .name(seasonDTO.getName())
                .posterPath(seasonDTO.getPosterPath())
                .build();
    }
}
