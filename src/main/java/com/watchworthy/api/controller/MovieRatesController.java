package com.watchworthy.api.controller;

import com.watchworthy.api.dto.AddCommentDTO;
import com.watchworthy.api.service.MovieRatesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movierates")
@CrossOrigin(origins = "*")
public class MovieRatesController {
    private final MovieRatesService movieRatesService;

    public MovieRatesController (MovieRatesService movieRatesService){
        this.movieRatesService = movieRatesService;
    }

    @RequestMapping(path = "/ratemovie/{movieId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<Void> rateMovie (@PathVariable Integer movieId, @PathVariable Long userId, @RequestParam Double rateNum){
        boolean result =  movieRatesService.rateMovie(userId,movieId,rateNum);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
    @RequestMapping(path = "average-rating/{movieId}", method = RequestMethod.GET)
    public ResponseEntity<Double> calculateAverageRating(@PathVariable("movieId") Integer movieId) {
        double averageRating = movieRatesService.calculateMovieRateNum(movieId);
        return ResponseEntity.ok(averageRating);
    }
    @RequestMapping(path = "getuserratenum/{movieId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Double> calculateAverageRating(@PathVariable("movieId") Integer movieId, @PathVariable("userId") Long userId) {
        double findRateNum = movieRatesService.findMovieRate(userId,movieId);
        return ResponseEntity.ok(findRateNum);
    }

    @RequestMapping(path = "/removemovierate/{movieId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<Void> removeMovieRate (@PathVariable Integer movieId, @PathVariable Long userId){
        boolean result =  movieRatesService.removeMovieRate(userId,movieId);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
