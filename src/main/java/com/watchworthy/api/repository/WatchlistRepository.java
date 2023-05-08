package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.WatchList;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Query to get the watchlist from a user
//Select m.title, m.overview from movie m join
//watchlists w on m.Id = w.movie_Id where
//w.user_Id = 2
public interface WatchlistRepository extends JpaRepository<WatchList , Integer> {
}
