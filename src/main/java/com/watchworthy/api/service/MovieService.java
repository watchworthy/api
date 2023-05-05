package com.watchworthy.api.service;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.MovieGenreDTO;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.model.PageModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    MovieDTO getMovieDetails(Integer movieId);
    PageModel<MovieDTO> getMovies(Integer page, Integer size, String q);
    void save(MovieDTO movieDTO);
    MovieDTO updateMovie(Integer id, MovieDTO movieDTO);
    void delete(Integer id);
    void addGenre(MovieGenreDTO movieGenreDTO);
    boolean addToWatchList(Long userId,Integer movieId);
    List<Movie> getWatchListMoviesByUserId(Long userId);
    boolean removeWatchList(Integer id);
}
