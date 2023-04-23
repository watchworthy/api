package com.watchworthy.api.integration.tmdb;

import com.watchworthy.api.config.FeignConfig;
import com.watchworthy.api.integration.tmdb.model.MovieDetailsResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "tmdb-service",
        url = "${service.client.url.tmdb-service}",
        configuration = FeignConfig.class)
public interface TMDBClient {
    @GetMapping(value = "/movie/{movieId}?api_key={apiKey}&language=en",
            produces = "application/json")
    MovieDetailsResult getMovieDetails(
            @PathVariable("apiKey")
            String apiKey,
            @PathVariable("movieId")
            String movieId
    );
}
