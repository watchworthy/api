package com.watchworthy.api.service;

import com.watchworthy.api.dto.EpisodeDTO;
import java.util.List;

public interface EpisodeService {
    void save(Integer tvShowId, Integer seasonNumber, EpisodeDTO episodeDTO);
    void update(Integer episodeId ,EpisodeDTO episodeDTO);
    EpisodeDTO getEpisode(Integer id);
    List<EpisodeDTO> getEpisodes(Integer tvShowId, Integer seasonId);
    void delete(Integer id);
}
