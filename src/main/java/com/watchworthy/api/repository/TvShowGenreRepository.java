package com.watchworthy.api.repository;

import com.watchworthy.api.entity.MovieGenre;
import com.watchworthy.api.entity.TvShowGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowGenreRepository extends JpaRepository<TvShowGenre, Integer> {
    List<TvShowGenre> findTvShowGenreByTvshowId(Integer tvId);
}
