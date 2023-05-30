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
        MovieRates existingRating = movieRatesRepository.findByUserIdAndMovieId(userId, movieId);
        if (existingRating != null) {
            existingRating.setRateNum(rateNum);
            movieRatesRepository.save(existingRating);
            return true;
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
            return 0.0;
        }

        double totalRating = 0.0;
        for (MovieRates movieRates : movieRatesList) {
            totalRating += movieRates.getRateNum();
        }

        return totalRating / ((List<?>) movieRatesList).size();
    }

    @Override
    public boolean removeMovieRate(Long userId , Integer movieId) {
        MovieRates existingRating = movieRatesRepository.findByUserIdAndMovieId(userId, movieId);
        if(existingRating != null){
            movieRatesRepository.delete(existingRating);
            return true;
        }
        return false;
    }

    @Override
    public Double findMovieRate(Long userId, Integer movieId) {
        User useExists = userRepository.findById(userId).orElse(null);
        Movie movieExists = movieRepository.findById(movieId).orElse(null);
        if(useExists==null || movieExists==null){
            return null;
        }
        MovieRates existingRating = movieRatesRepository.findByUserIdAndMovieId(userId, movieId);
        if (existingRating != null) {
            return existingRating.getRateNum();
        }
        return null;
    }
}
