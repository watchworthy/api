package com.watchworthy.api.service;

import com.watchworthy.api.dto.MovieDTO;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    MovieDTO getMovieDetails(String movieId);
}
