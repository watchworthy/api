package com.watchworthy.api.service;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.TvShowDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecommendationService {
    List<MovieDTO> getRecommendedMovies(Long userId);
    List<TvShowDTO> getRecommendedShows(Long userId);
}
