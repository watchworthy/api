package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.EpisodeDTO;
import com.watchworthy.api.entity.Episode;
import com.watchworthy.api.entity.Season;
import com.watchworthy.api.repository.EpisodeRepository;
import com.watchworthy.api.repository.SeasonRepository;
import com.watchworthy.api.service.EpisodeService;
import com.watchworthy.api.service.StorageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    public EpisodeServiceImpl(EpisodeRepository episodeRepository, SeasonRepository seasonRepository, ModelMapper modelMapper, StorageService storageService) {
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;
        this.modelMapper = modelMapper;
        this.storageService = storageService;
    }

    @Override
    public void save(Integer tvShowId, Integer seasonNumber, EpisodeDTO episodeDTO) {
        Episode episode = convertToEntity(episodeDTO);

        Season season = seasonRepository.findAll()
                .stream()
                .filter(x -> x.getSeasonNumber() == seasonNumber)
                .findFirst()
                .orElse(null);

        if (season != null) {
            episode.setSeason(season);
            season.getEpisodes().add(episode);
            episodeRepository.save(episode);
            seasonRepository.save(season);
        } else {
            throw new NoSuchElementException("Season not found");
        }
    }

    @Override
    public void update(Integer episodeId, EpisodeDTO episodeDTO) {
        Episode episode = episodeRepository.findById(episodeId).orElse(null);
        if (episode != null) {
            episodeDTO.setId(episodeId);
            episodeRepository.save(convertToEntity(episodeDTO));
        }
    }

    @Override
    public EpisodeDTO getEpisode(Integer id) {
        Episode episode = episodeRepository.findById(id).orElse(null);
        return episode != null ? convertToDto(episode) : null;
    }

    @Override
    public List<EpisodeDTO> getEpisodes(Integer tvShowId, Integer seasonId) {
        List<Episode> episodes = episodeRepository.findAll()
                .stream()
                .filter(episode -> episode.getSeason().getTvshow().getId().equals(tvShowId)
                        && episode.getSeason().getId().equals(seasonId))
                .toList();

        return episodes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        episodeRepository.findById(id).ifPresent(episode -> episodeRepository.deleteById(id));
    }

    @Override
    public void addPoster(Integer episodeId, MultipartFile file) {
        String posterPath = storageService.uploadFile(file);
        Episode episode = episodeRepository.findById(episodeId).orElse(null);
        if(episode != null){
            episode.setPosterPath(posterPath);
            episodeRepository.save(episode);
        }
    }

    public EpisodeDTO convertToDto(Episode episode) {
        return modelMapper.map(episode, EpisodeDTO.class);
    }

    public Episode convertToEntity(EpisodeDTO episodeDTO) {
        return modelMapper.map(episodeDTO, Episode.class);
    }
}
