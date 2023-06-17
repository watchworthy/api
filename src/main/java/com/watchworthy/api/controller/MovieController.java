package com.watchworthy.api.controller;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.Comment;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.exception.EmptyValueExistException;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "*")
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
            @RequestParam(name = "q", defaultValue = "") String q,
            @RequestParam(name = "genre", required = false) Integer genre
    ) {
        return movieService.getMovies(page, size, q,genre);
    }

    @RequestMapping(path = "/upcoming", method = RequestMethod.GET)
    public List<MovieDTO> getUpcomingMovies() {
        return movieService.getUpcomingMovies();
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
    @RequestMapping(path = "/removemoviefromwatchlist/{id}", method = RequestMethod.DELETE)
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
    @RequestMapping(path = "/addcommenttomovies/{movieId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<Void> addCommentToMovies (@PathVariable Integer movieId, @PathVariable Long userId, @RequestBody AddCommentDTO addCommentDTO){
        boolean result =  movieService.addCommentToMovies(userId,movieId,addCommentDTO);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/removecomment/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeComment (@PathVariable Integer id){
        boolean result =  movieService.removeComment(id);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/updatecomment/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateComment (@PathVariable Integer id, @RequestBody String text){
        boolean result =  movieService.updateComment(id,text);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    public void addPerson(
            @RequestBody MoviePersonDTO moviePersonDTO) {
        movieService.addPersonToMovie(moviePersonDTO);
    }

    @RequestMapping(path = "/list/{personId}",method = RequestMethod.GET)
    public List<MovieDTO> getMoviesByPersonId(@PathVariable("personId") Integer personId){
        return movieService.getMoviesByPerson(personId);
    }

    @RequestMapping(path = "{movieId}/poster",method = RequestMethod.POST)
    public void addPoster(@PathVariable Integer movieId,@RequestParam("file") MultipartFile file){
        movieService.addPoster(movieId,file);
    }

    @RequestMapping(path = "/popular", method = RequestMethod.GET)
    public List<MovieDTO> getPopularMovies() {
        return movieService.getPopularMovies();
    }
}
