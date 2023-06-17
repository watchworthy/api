package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Genre;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.TvShow;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TvShowRepository extends  JpaRepository<TvShow, Integer>, JpaSpecificationExecutor<TvShow> {

    @Query("SELECT m.id, m.title,m.overview , m.posterPath,m.releaseDate, w.id from tv_show m join  watchlists w on m.id = w.movieId where w.userId = :userId")
    List<Object[]> getWatchlistByUserId(@Param("userId") Long userId);

    List<TvShow> findByGenresIn(Collection<Genre> genres);

}
