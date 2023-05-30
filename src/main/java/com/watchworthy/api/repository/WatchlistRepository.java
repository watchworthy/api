package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.WatchList;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WatchlistRepository extends JpaRepository<WatchList , Integer> {
    WatchList findByMovieIdAndUserId(Integer movieId,Long userId);
}
