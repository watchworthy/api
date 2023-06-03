package com.watchworthy.api.repository;

import com.watchworthy.api.entity.MovieRates;
import com.watchworthy.api.entity.TvShowRates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowRatesRepository  extends JpaRepository<TvShowRates, Integer> {

    List<TvShowRates> findByTvShowId(Integer tvShowId);
    TvShowRates findByUserIdAndTvShowId(Long userId, Integer tvShowId);
}
