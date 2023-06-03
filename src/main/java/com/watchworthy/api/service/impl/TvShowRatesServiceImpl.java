package com.watchworthy.api.service.impl;

import com.watchworthy.api.entity.*;
import com.watchworthy.api.repository.TvShowRatesRepository;
import com.watchworthy.api.repository.TvShowRepository;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.TvShowRatesService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class TvShowRatesServiceImpl implements TvShowRatesService {

    private final TvShowRepository tvShowRepository;
    private final UserRepository userRepository;

    private final TvShowRatesRepository tvShowRatesRepository;


    public TvShowRatesServiceImpl(TvShowRepository tvShowRepository, UserRepository userRepository,  TvShowRatesRepository tvShowRatesRepository){

        this.tvShowRepository = tvShowRepository;
        this.userRepository = userRepository;
        this.tvShowRatesRepository = tvShowRatesRepository;

    }

    @Override
    public boolean rateTvShow(Long userId, Integer tvShowId, Double rateNum) {
        User useExists = userRepository.findById(userId).orElse(null);
        TvShow tvShowExists = tvShowRepository.findById(tvShowId).orElse(null);
        if(useExists==null || tvShowExists==null){
            return false;
        }
        if (rateNum < 1 || rateNum > 5) {
            return false;
        }
        TvShowRates existingRating = tvShowRatesRepository.findByUserIdAndTvShowId(userId, tvShowId);
        if (existingRating != null) {
            existingRating.setRateNum(rateNum);
            tvShowRatesRepository.save(existingRating);
            return true;
        }

        TvShowRates tvShowRates = new TvShowRates();
        tvShowRates.setTvShowId(tvShowId);
        tvShowRates.setUserId(userId);
        tvShowRates.setRateNum(rateNum);
        tvShowRatesRepository.save(tvShowRates);

        return true;
    }

    public double calculateTvShowRateNum(Integer tvShowId) {
        List<TvShowRates> tvShowRatesList = tvShowRatesRepository.findByTvShowId(tvShowId);
        if (tvShowRatesList.isEmpty()) {
            return 0.0;
        }

        double totalRating = 0.0;
        for (TvShowRates tvShowRates : tvShowRatesList) {
            totalRating += tvShowRates.getRateNum();
        }

        return totalRating / ((List<?>) tvShowRatesList).size();
    }



    @Override
    public boolean removeTvShowRate(Long userId , Integer tvShowId) {
        TvShowRates existingRating = tvShowRatesRepository.findByUserIdAndTvShowId(userId, tvShowId);
        if(existingRating != null){
            tvShowRatesRepository.delete(existingRating);
            return true;
        }
        return false;
    }

    @Override
    public Double findTvShowRate(Long userId, Integer tvShowId) {
        User useExists = userRepository.findById(userId).orElse(null);
        TvShow tvShowExists = tvShowRepository.findById(tvShowId).orElse(null);
        if(useExists==null || tvShowExists==null){
            return null;
        }
        TvShowRates existingRating = tvShowRatesRepository.findByUserIdAndTvShowId(userId, tvShowId);
        if (existingRating != null) {
            return existingRating.getRateNum();
        }
        return null;
    }





}
