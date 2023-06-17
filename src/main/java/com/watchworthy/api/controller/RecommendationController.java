package com.watchworthy.api.controller;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.TvShowDTO;
import com.watchworthy.api.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommended")
@CrossOrigin(origins = "*")
public class RecommendationController {
    private final RecommendationService recommendationService;


    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping("/movies/{userId}")
    public ResponseEntity<List<MovieDTO>> getRecommendedMovies(@PathVariable("userId") Long userId) {
        List<MovieDTO> recommendedMovies = recommendationService.getRecommendedMovies(userId);
        return ResponseEntity.ok(recommendedMovies);
    }

    @GetMapping("/tv/{userId}")
    public ResponseEntity<List<TvShowDTO>> getRecommendedShows(@PathVariable("userId") Long userId) {
        List<TvShowDTO> recommendedShows = recommendationService.getRecommendedShows(userId);
        return ResponseEntity.ok(recommendedShows);
    }
}
