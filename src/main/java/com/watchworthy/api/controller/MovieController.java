package com.watchworthy.api.controller;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.service.MovieService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public MovieDTO getMovieDetails(@PathVariable("id") String id){
        return movieService.getMovieDetails(id);
    }

    @RequestMapping(path = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }
}
