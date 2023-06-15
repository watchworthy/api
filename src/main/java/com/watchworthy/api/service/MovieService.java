package com.watchworthy.api.service;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.Comment;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.model.PageModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface MovieService {
    MovieDTO getMovieDetails(Integer movieId);
    Movie findById(Integer movieId);
    PageModel<MovieDTO> getMovies(Integer page, Integer size, String q, Integer genre);
    void save(MovieDTO movieDTO);
    MovieDTO updateMovie(Integer id, MovieDTO movieDTO);
    void delete(Integer id);
    void addGenre(MovieGenreDTO movieGenreDTO);
    boolean addToWatchList(Long userId,Integer movieId);
    List<WatchListDTO> getWatchListMoviesByUserId(Long userId);
    boolean removeWatchList(Integer id);
    boolean addCommentToMovies (Long userId , Integer movieId, AddCommentDTO addCommentDTO);
    boolean removeComment(Integer id);
    boolean updateComment(Integer id, String text);
    void addPersonToMovie(MoviePersonDTO moviePersonDTO);
    List<MovieDTO> getMoviesByPerson(Integer personId);

    List<MovieDTO> getUpcomingMovies();

    void addPoster(Integer movieId , MultipartFile file);

    List<MovieDTO> getNowPlayingMovies();

    List<MovieDTO> getPopularMovies();
}
