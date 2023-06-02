package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.TvShowWatchList;
import com.watchworthy.api.entity.WatchList;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TvShowWatchlistRepository extends JpaRepository<TvShowWatchList , Integer> {
    TvShowWatchList findByTvShowIdAndUserId(Integer tvShowId, Long userId);
}
