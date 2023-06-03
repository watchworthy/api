package com.watchworthy.api.service;
import com.watchworthy.api.entity.MovieRates;
import org.springframework.stereotype.Service;

public interface TvShowRatesService {

    boolean rateTvShow (Long userId, Integer tvShowId, Double rateNum);
    double calculateTvShowRateNum (Integer tvShowId);
    boolean removeTvShowRate (Long userId , Integer tvShowId);
    Double findTvShowRate (Long userId, Integer tvShowId);


}
