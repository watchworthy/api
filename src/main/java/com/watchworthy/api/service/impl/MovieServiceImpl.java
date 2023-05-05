package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.dto.MovieGenreDTO;
import com.watchworthy.api.entity.Movie;
import com.watchworthy.api.entity.MovieGenre;
import com.watchworthy.api.entity.User;
import com.watchworthy.api.entity.WatchList;
import com.watchworthy.api.exception.MovieNotFoundException;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.*;
import com.watchworthy.api.service.MovieService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final UserRepository userRepository;
    private final WatchlistRepository watchlistRepository;
    private final ModelMapper modelMapper;

    @Override
    public MovieDTO getMovieDetails(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            return convertToDto(movie);
        } else {
            throw new MovieNotFoundException();
        }
    }

    @Override
    public PageModel<MovieDTO> getMovies(Integer page, Integer size, String q) {
        page = page != null ? Math.max(page - 1, 0) : 0;
        size = size != null && size > 0 ? size : 20;

        Pageable pageable = PageRequest.of(page, size);

        Specification<Movie> specification = StringUtils.isBlank(q) ? null : new MovieSpecification(q);
        Page<Movie> moviePage = movieRepository.findAll(specification, pageable);

        return PageModel.<MovieDTO>builder()
                .total(moviePage.getTotalElements())
                .size(moviePage.getSize())
                .page(moviePage.getNumber() + 1)
                .data(moviePage.map(this::convertToDto).getContent())
                .build();
    }

    @Override
    public void save(MovieDTO movieDTO) {
        movieRepository.save(convertToEntity(movieDTO));
    }

    @Override
    public MovieDTO updateMovie(Integer id, MovieDTO movieDTO) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movieDTO.setId(movie.getId());
            movieRepository.save(convertToEntity(movieDTO));
            return movieDTO;
        } else {
//            TODO: add proper exception handling
            throw new RuntimeException("Movie with id " + id + " not found");
        }
    }

    @Override
    public void delete(Integer id) {
        movieRepository.findById(id).ifPresent(movie -> movieRepository.deleteById(id));
    }

    @Override
    public void addGenre(MovieGenreDTO movieGenreDTO) {
        movieGenreRepository.save(movieGenreDTOToEntity(movieGenreDTO));
    }
    @Override
    public boolean addToWatchList(Long userId, Integer movieId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user==null){
            return false;
        }
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie == null){
            return false;
        }
        WatchList watchList = new WatchList(
                userId,
                movieId,
                false
        );
        watchlistRepository.save(watchList);
        return true;
    }

    @Override
    public List<Movie> getWatchListMoviesByUserId(Long userId) {
        return movieRepository.getWatchlistByUserId(userId);
    }

    @Override
    public boolean removeWatchList(Integer id) {
        WatchList watchList = watchlistRepository.findById(id).orElse(null);
        if(watchList == null){
            return false;
        }
        watchlistRepository.delete(watchList);
        return true;
    }

    public MovieDTO convertToDto(Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }

    public Movie convertToEntity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

    public MovieGenre movieGenreDTOToEntity(MovieGenreDTO movieGenreDTO) {
        return modelMapper.map(movieGenreDTO, MovieGenre.class);
    }

    public MovieGenreDTO movieGenreToDTO(MovieGenre movieGenre) {
        return modelMapper.map(movieGenre, MovieGenreDTO.class);
    }


}
