package com.watchworthy.api.repository;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.WatchListDTO;
import com.watchworthy.api.entity.Movie;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {
    @Query("SELECT m.title,m.overview , m.posterPath,m.releaseDate  from movie m join  watchlists w on m.id = w.movieId where w.userId = :userId")
    List<Object[]> getWatchlistByUserId(@Param("userId") Long userId);
}
