package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.*;
import com.watchworthy.api.exception.MovieNotFoundException;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.*;
import com.watchworthy.api.service.MovieService;
import com.watchworthy.api.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final MoviePersonRepository moviePersonRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final WatchlistRepository watchlistRepository;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

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
    public Movie findById(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            return movie;
        } else {
            throw new MovieNotFoundException();
        }
    }
    @Override
    public PageModel<MovieDTO> getMovies(Integer page, Integer size, String q, Integer genre) {
        page = page != null ? Math.max(page - 1, 0) : 0;
        size = size != null && size > 0 ? size : 20;

        Pageable pageable = PageRequest.of(page, size);

        MovieSpecification movieSpecification = new MovieSpecification(q,genre);
        Page<Movie> moviePage = movieRepository.findAll(movieSpecification, pageable);

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

        WatchList watchListExists = watchlistRepository.findByMovieIdAndUserId(movieId,userId);
        if(watchListExists !=null){
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
    public List<WatchListDTO> getWatchListMoviesByUserId(Long userId) {
        List<Object[]> results = movieRepository.getWatchlistByUserId(userId);
        return results.stream()
                .map(r -> new WatchListDTO((Integer) r[0], (String) r[1], (String) r[2], (String) r[3], (LocalDate) r[4],(Integer) r[5]))
                .collect(Collectors.toList());
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

    @Override
    public boolean addCommentToMovies(Long userId, Integer movieId, AddCommentDTO addCommentDTO) {
        User user =userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null || addCommentDTO.getText().trim().isEmpty()) {
            return false;
        }

        Comment comment = new Comment();
        comment.setFirstName(user.getFirstName());
        comment.setLastName(user.getLastName());
        comment.setMovie(movie);
        comment.setUser(user);
        comment.setText(addCommentDTO.getText());
        comment.setDateTimeCreated(LocalDateTime.now());

        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean removeComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null){
            return false;
        }
        commentRepository.delete(comment);
        return true;
    }

    @Override
    public boolean updateComment(Integer id, String text) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null){
            return false;
        }
        comment.setText(text);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public void addPersonToMovie(MoviePersonDTO moviePersonDTO) {
        moviePersonRepository.save(moviePersonDTOToEntity(moviePersonDTO));
    }

    public MovieDTO convertToDto(Movie movie) {
        MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

        // Include the comments
        HashSet<CommentDTO> commentDTOs = new HashSet<>();
        for (Comment comment : movie.getComments()) {
            CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
            commentDTOs.add(commentDTO);
        }
        movieDTO.setComments(commentDTOs);

        return movieDTO;
    }


    @Override
    public List<MovieDTO> getMoviesByPerson(Integer personId) {
        List<MoviePerson> moviePersonList = moviePersonRepository.findByPersonId(personId);
        List<Movie> movies = new ArrayList<>();

        for (MoviePerson moviePerson : moviePersonList) {
            Integer movieId = moviePerson.getMovie().getId();
            movieRepository.findById(movieId).ifPresent(movies::add);
        }

        // Convert Movie objects to MovieDTO and return the list
        List<MovieDTO> movieDTOs = new ArrayList<>();
        for(Movie movie: movies){
            MovieDTO movieDto = convertToDto(movie);
            movieDTOs.add(movieDto);
        }
        return movieDTOs;
    }

    @Override
    public List<MovieDTO> getUpcomingMovies() {
        LocalDate currentDate = LocalDate.now();
        List<Movie> movies = movieRepository.findAll().stream().filter(x->x.getReleaseDate().isAfter(currentDate)).toList();
        List<MovieDTO> movieDTOS = new ArrayList<>();
        for(Movie movie : movies){
            MovieDTO movieDTO =  convertToDto(movie);
            movieDTOS.add(movieDTO);
        }
        return movieDTOS;
    }

    @Override
    public void addPoster(Integer movieId , MultipartFile file) {
        String posterPath = storageService.uploadFile(file);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if(movie != null){
            movie.setPosterPath(posterPath);
            movieRepository.save(movie);
        }
    }

    @Override
    public List<MovieDTO> getNowPlayingMovies() {
        return null;
    }
    public List<MovieDTO> getPopularMovies() {
        Pageable pageable = PageRequest.of(0, 20);
        List<Movie> movies = movieRepository.getPopularMovies(pageable);
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
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

    public MoviePerson moviePersonDTOToEntity(MoviePersonDTO moviePersonDTO) {
        return modelMapper.map(moviePersonDTO, MoviePerson.class);
    }

    public MoviePersonDTO moviePersonToDto(MoviePerson moviePerson) {
        return modelMapper.map(moviePerson, MoviePersonDTO.class);
    }


}
