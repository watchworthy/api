package com.watchworthy.api.controller;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.MovieGenreDTO;
import com.watchworthy.api.dto.WatchListDTO;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.exception.EmptyValueExistException;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public MovieDTO getMovieDetails(@PathVariable("id") Integer id) {
        return movieService.getMovieDetails(id);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public PageModel<MovieDTO> getMovies(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size,
            @RequestParam(name = "q", defaultValue = "") String q
    ) {
        return movieService.getMovies(page, size, q);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody MovieDTO movieDto) {
        movieService.save(movieDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public MovieDTO update(
            @PathVariable("id") Integer id,
            @RequestBody MovieDTO movieDto) {
        return movieService.updateMovie(id, movieDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(
            @PathVariable("id") Integer id) {
        movieService.delete(id);
    }

    @RequestMapping(path = "/genre", method = RequestMethod.POST)
    public void addGenre(
            @RequestBody MovieGenreDTO movieGenreDTO) {
        movieService.addGenre(movieGenreDTO);
    }
    @RequestMapping(path = "/addtowatchlist/{userId}/{movieId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addMovieToWatchList(@PathVariable Long userId, @PathVariable Integer movieId) {
       boolean result = movieService.addToWatchList(userId,movieId);
       if(result){
           return ResponseEntity.ok().build();
       }else {
           return ResponseEntity.badRequest().build();
       }
    }
    @RequestMapping(path = "/removewatchlist/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeMovieFromWatchlist (@PathVariable Integer id){
        boolean result = movieService.removeWatchList(id);
        if(result){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }
    @RequestMapping(path = "/getwatchlistbyuserid/{userId}",method = RequestMethod.GET)
    public ResponseEntity<List<WatchListDTO>> getWatchlistByUserId(@PathVariable("userId") Long userId) {
        List<WatchListDTO> movies = movieService.getWatchListMoviesByUserId(userId);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }
}
