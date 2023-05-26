package com.watchworthy.api.service;

import com.watchworthy.api.dto.SeasonDTO;

import java.util.List;

public interface SeasonService {
    void save(Integer tvId, SeasonDTO seasonDTO);
    void update(Integer seasonId ,SeasonDTO seasonDTO);
    SeasonDTO getSeason(Integer id);
    List<SeasonDTO> getSeasons(Integer tvId);
    void delete(Integer id);
}
