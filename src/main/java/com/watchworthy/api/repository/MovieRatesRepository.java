package com.watchworthy.api.repository;

import com.watchworthy.api.entity.MovieRates;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRatesRepository extends JpaRepository<MovieRates, Integer> {
    List<MovieRates> findByMovieId(Integer movieId);

}
