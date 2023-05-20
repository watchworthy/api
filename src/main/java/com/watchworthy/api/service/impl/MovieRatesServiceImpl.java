package com.watchworthy.api.service.impl;

import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.MovieRates;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.repository.MovieRatesRepository;
import com.watchworthy.api.repository.MovieRepository;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.MovieRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieRatesServiceImpl implements MovieRatesService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final MovieRatesRepository movieRatesRepository;

    @Override
    public boolean rateMovie(Long userId, Integer movieId, Double rateNum) {
        User useExists = userRepository.findById(userId).orElse(null);
        Movie movieExists = movieRepository.findById(movieId).orElse(null);
        if(useExists==null || movieExists==null){
            return false;
        }
        if (rateNum < 1 || rateNum > 5) {
            return false;
        }
        MovieRates movieRates = new MovieRates();
        movieRates.setMovieId(movieId);
        movieRates.setUserId(userId);
        movieRates.setRateNum(rateNum);
        movieRatesRepository.save(movieRates);

        return true;
    }
    public double calculateMovieRateNum(Integer movieId) {
        List<MovieRates> movieRatesList = movieRatesRepository.findByMovieId(movieId);
        if (movieRatesList.isEmpty()) {
            return 0.0; // Return 0 if there are no ratings for the movie
        }

        double totalRating = 0.0;
        for (MovieRates movieRates : movieRatesList) {
            totalRating += movieRates.getRateNum();
        }

        return totalRating / ((List<?>) movieRatesList).size();
    }

}
