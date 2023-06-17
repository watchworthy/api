package com.watchworthy.api.repository;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.WatchListDTO;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.MoviePerson;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.time.LocalDate;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer>, JpaSpecificationExecutor<Movie> {
    @Query("SELECT m.id, m.title,m.overview , m.posterPath,m.releaseDate, w.id from movie m join  watchlists w on m.id = w.movieId where w.userId = :userId")
    List<Object[]> getWatchlistByUserId(@Param("userId") Long userId);

    List<Movie> findByReleaseDateGreaterThan(LocalDate currentDate);


    @Query("SELECT m FROM movie m LEFT JOIN m.comments c GROUP BY m ORDER BY COUNT(c) DESC")
    List<Movie> getPopularMovies(Pageable pageable);
}
