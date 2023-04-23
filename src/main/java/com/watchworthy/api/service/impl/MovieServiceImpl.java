package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.integration.tmdb.TMDBClient;
import com.watchworthy.api.integration.tmdb.model.MovieDetailsResult;
import com.watchworthy.api.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final TMDBClient tmdbClient;

    @Value("${service.client.secrets.tmdb-secret}")
    private String tmdbSecret;

    @Override
    public MovieDTO getMovieDetails(String movieId) {
        MovieDetailsResult movieDetailsResult = tmdbClient.getMovieDetails(tmdbSecret, movieId);
        return MovieDTO.builder()
                .id(movieDetailsResult.getId())
                .title(movieDetailsResult.getTitle())
                .overview(movieDetailsResult.getOverview())
                .releaseDate(movieDetailsResult.getRelease_date())
                .posterPath(movieDetailsResult.getPoster_path())
                .build();
    }
}
