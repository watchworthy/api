package com.watchworthy.api.service;

import com.watchworthy.api.dto.TvShowDTO;
import com.watchworthy.api.dto.TvShowGenreDTO;
import com.watchworthy.api.dto.TvShowPersonDTO;
import com.watchworthy.api.model.PageModel;

import java.util.List;

public interface TvShowService {
    void save(TvShowDTO TvShowDTO);
    void update(Integer TvShowId ,TvShowDTO TvShowDTO);
    TvShowDTO getTvShow(Integer id);
    PageModel<TvShowDTO> getTvShows(Integer page, Integer size, String q);
    void delete(Integer id);
    void addGenre(TvShowGenreDTO tvShowGenreDTO);
    void addPersonToTvShow(TvShowPersonDTO tvShowPersonDTO);
//    void getSeasons(Integer tvShowId);
//    void getPeople(Integer tvShowId);
}
