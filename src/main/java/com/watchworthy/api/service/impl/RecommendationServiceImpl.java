package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.TvShowDTO;
import com.watchworthy.api.entity.Genre;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.TvShow;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.repository.GenreRepository;
import com.watchworthy.api.repository.MovieRepository;
import com.watchworthy.api.repository.TvShowRepository;
import com.watchworthy.api.repository.UserRepository;
import com.watchworthy.api.service.RecommendationService;
import org.jetbrains.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final TvShowRepository tvShowRepository;

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public RecommendationServiceImpl(UserRepository userRepository, MovieRepository movieRepository, TvShowRepository tvShowRepository, GenreRepository genreRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.tvShowRepository = tvShowRepository;
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> getRecommendedMovies(Long userId) {
        List<Movie> movies = movieRepository.findByGenresIn(getUserPreferences(userId));
        Collections.shuffle(movies);

        int moviesCount = Math.min(14, movies.size());
        List<MovieDTO> preferred = new ArrayList<>();
        for (int i = 0; i < moviesCount; i++) {
            Movie movie = movies.get(i);
            MovieDTO movieDTO = convertToDto(movie);
            preferred.add(movieDTO);
        }
        return preferred;
    }

    @Override
    public List<TvShowDTO> getRecommendedShows(Long userId) {

        Set<Genre> genres =  getUserPreferences(userId);

        List<TvShow> tvShows = tvShowRepository.findByGenresIn(genres);
        Collections.shuffle(tvShows);

        int tvShowsCount = Math.min(14, tvShows.size());
        List<TvShowDTO> preferred = new ArrayList<>();
        for (int i = 0; i < tvShowsCount; i++) {
            TvShow tvShow = tvShows.get(i);
            TvShowDTO tvShowDTO = convertShowToDto(tvShow);
            preferred.add(tvShowDTO);
        }
        return preferred;
    }

    private Set<Genre> getUserPreferences(Long userId) {
        User user = getUser(userId);
        List<String> preferredGenres = null;
        if (user != null) {
            preferredGenres = user.getPreferredGenres();
        }

        Set<Genre> genres = new HashSet<>();
        if (preferredGenres != null) {
            for (String genreName : preferredGenres) {
                Genre genre = genreRepository.findGenreByName(genreName);
                if (genre != null) {
                    genres.add(genre);
                }
            }
        }
        return genres;
    }

    @Nullable
    private User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    private MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
    private TvShowDTO convertShowToDto(TvShow tv) {
        return modelMapper.map(tv, TvShowDTO.class);
    }

}
