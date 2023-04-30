package com.watchworthy.api.controller;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.service.MovieService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    public MovieDTO update(
            @PathVariable("id") Integer id,
            @RequestBody MovieDTO movieDto) {
       return movieService.updateMovie(id,movieDto);
    }

}
