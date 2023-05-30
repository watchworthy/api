package com.watchworthy.api.service;

import org.springframework.stereotype.Service;

@Service
public interface MovieRatesService {
    boolean rateMovie (Long userId, Integer movieId, Double rateNum);
    double calculateMovieRateNum (Integer movieId);
    boolean removeMovieRate (Long userId , Integer movieId);
    Double findMovieRate (Long userId, Integer movieId);
}
