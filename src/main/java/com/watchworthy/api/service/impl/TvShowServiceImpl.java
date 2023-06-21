package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.entity.*;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.repository.*;
import com.watchworthy.api.service.StorageService;
import com.watchworthy.api.service.TvShowService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TvShowServiceImpl implements TvShowService {
    private final TvShowRepository tvShowRepository;
    private final PersonRepository personRepository;
    private final TvShowGenreRepository tvShowGenreRepository;
    private final TvShowPersonRepository tvShowPersonRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    private final TvShowWatchlistRepository tvShowWatchlistRepository;
    private final ModelMapper modelMapper;
    private final StorageService storageService;

    public TvShowServiceImpl(TvShowRepository tvShowRepository,
                             SeasonRepository seasonRepository,
                             PersonRepository personRepository, TvShowGenreRepository tvShowGenreRepository,
                             TvShowPersonRepository tvShowPersonRepository,
                             UserRepository userRepository, ModelMapper modelMapper,
                             TvShowWatchlistRepository tvShowWatchlistRepository,
                             CommentRepository commentRepository, StorageService storageService) {
        this.tvShowRepository = tvShowRepository;
        this.personRepository = personRepository;
        this.tvShowGenreRepository = tvShowGenreRepository;
        this.tvShowPersonRepository = tvShowPersonRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.tvShowWatchlistRepository = tvShowWatchlistRepository;
        this.commentRepository = commentRepository;
        this.storageService = storageService;
    }

    @Override
    public void save(TvShowDTO tvShowDTO) {
        tvShowRepository.save(dtoToEntity(tvShowDTO));
    }

    @Override
    public void update(Integer tvShowId, TvShowDTO tvShowDTO) {
        TvShow tvShow = tvShowRepository.findById(tvShowId).orElse(null);
        if (tvShow != null) {
            tvShowDTO.setId(tvShowId);
            tvShowRepository.save(dtoToEntity(tvShowDTO));
        }
    }

    @Override
    public TvShowDTO getTvShow(Integer id) {
        TvShow tvShow = tvShowRepository.findById(id).orElse(null);
        return tvShow != null ? entityToDTO(tvShow) : null;
    }

    @Override
    public PageModel<TvShowDTO> getTvShows(Integer page, Integer size, String q, Integer genre) {
        page = page != null ? Math.max(page - 1, 0) : 0;
        size = size != null && size > 0 ? size : 20;

        Pageable pageable = PageRequest.of(page, size);

        TvShowSpecification specification = new TvShowSpecification(q,genre);
        Page<TvShow> tvShows = tvShowRepository.findAll(specification, pageable);

        return PageModel.<TvShowDTO>builder()
                .total(tvShows.getTotalElements())
                .size(tvShows.getSize())
                .page(tvShows.getNumber() + 1)
                .data(tvShows.map(this::entityToDTO).getContent())
                .build();
    }

    @Override
    public List<TvShowDTO> getTvShowsByPerson(Integer personId) {
        List<TvShowPerson> tvShowPersonList = tvShowPersonRepository.findByPersonId(personId);
        List<TvShow> tvShows = new ArrayList<>();

        for (TvShowPerson tvShowPerson : tvShowPersonList) {
            Integer tvShowId = tvShowPerson.getTvshow().getId();
            tvShowRepository.findById(tvShowId).ifPresent(tvShows::add);
        }

        List<TvShowDTO> tvShowDTOs = new ArrayList<>();
        for(TvShow tvShow: tvShows){
            TvShowDTO tvShowDTO = entityToDTO(tvShow);
            tvShowDTOs.add(tvShowDTO);
        }
        return tvShowDTOs;
    }

    @Override
    public void addPoster(Integer tvId, MultipartFile file) {
        String posterPath = storageService.uploadFile(file);
        TvShow tvShow = tvShowRepository.findById(tvId).orElse(null);
        if(tvShow != null){
            tvShow.setPosterPath(posterPath);
            tvShowRepository.save(tvShow);
        }
    }

    @Override
    public void delete(Integer id) {
        tvShowRepository.findById(id).ifPresent(tvShow -> tvShowRepository.deleteById(id));
    }

    @Override
    public void addGenre(TvShowGenreDTO tvShowGenreDTO) {
    tvShowGenreRepository.save(tvShowGenreDTOToEntity(tvShowGenreDTO));
    }

    @Override
    public List<TvShowGenreDTO> getGenres(Integer tvId) {
        List<TvShowGenre> genres = tvShowGenreRepository.findTvShowGenreByTvshowId(tvId);
        List<TvShowGenreDTO> genreDTOs = new ArrayList<>();
        for(TvShowGenre genre: genres){
            TvShowGenreDTO tvShowGenreDTO = TvShowGenreDTO.builder()
                    .id(genre.getId())
                    .name(genre.getGenre().getName())
                    .genreId(genre.getGenre().getId())
                    .tvShowId(genre.getTvshow().getId())
                    .build();
            genreDTOs.add(tvShowGenreDTO);
        }
        return genreDTOs;
    }

    @Override
    public List<CommentDTO> getComments(Integer tvId) {
       List<Comment> comments = commentRepository.findAllByTvShowId(tvId);
       List<CommentDTO> commentDTOS = new ArrayList<>();
       for(Comment comment: comments){
           CommentDTO commentDTO =
                   CommentDTO.builder()
                           .id(comment.getId())
                           .text(comment.getText())
                           .dateTimeCreated(comment.getDateTimeCreated())
                           .firstName(comment.getFirstName())
                           .lastName(comment.getLastName())
                           .build();
           commentDTOS.add(commentDTO);
       }
       return commentDTOS;
    }

    @Override
    public List<TvShowPersonDTO> getPeople(Integer tvId) {
        List<TvShowPerson> people = tvShowPersonRepository.findAllByTvshowId(tvId);
        List<TvShowPersonDTO> personDTOS = new ArrayList<>();
        for(TvShowPerson person: people){
            TvShowPersonDTO personDTO = TvShowPersonDTO.builder()
                    .person(PersonDTO
                            .builder()
                            .name(person.getPerson().getName())
                            .id(person.getPerson()
                                    .getId())
                            .biography(person.getPerson().getBiography())
                            .posterPath(person.getPerson().getPosterPath())
                            .gender(person.getPerson().getGender())
                            .build())
                    .tvShowId(person.getTvshow().getId())
                    .id(person.getId())
                    .build();
            personDTOS.add(personDTO);
        }
        return personDTOS;
    }


    @Override
    public void addPersonToTvShow(Integer tvId, Integer personId) {
        TvShow tvShow = tvShowRepository.findById(tvId).orElse(null);
        if(tvShow == null){
            throw new RuntimeException("Tv show not found");
        }
        Person person = personRepository.findById(personId).orElse(null);
        if(person == null){
            throw new RuntimeException("Person not found");
        }
        TvShowPerson tvShowPerson = TvShowPerson.builder()
                        .person(person)
                                .tvshow(tvShow).build();
    tvShowPersonRepository.save(tvShowPerson);
    }

//    @Override
//    public void getSeasons(Integer tvShowId) {
//
//    }
//
//    @Override
//    public void getPeople(Integer tvShowId) {
//
//    }

//    public TvShowDTO entityToDTO(TvShow tvShow) {
//        return modelMapper.map(tvShow, TvShowDTO.class);
//    }

    private TvShowDTO entityToDTO(TvShow tvShow){
        return TvShowDTO.builder()
                .id(tvShow.getId())
                .status(tvShow.getStatus())
                .inProduction(tvShow.isInProduction())
                .numberOfSeasons(tvShow.getNumberOfSeasons())
                .overview(tvShow.getOverview())
                .trailerId(tvShow.getTrailerId())
                .averageRating(tvShow.getAverageRating())
                .posterPath(tvShow.getPosterPath())
                .numberOfEpisodes(tvShow.getNumberOfEpisodes())
                .title(tvShow.getTitle())
                .releaseDate(tvShow.getReleaseDate())
                .build();
    }
    private TvShow dtoToEntity(TvShowDTO model) {
        return TvShow.builder()
                .id(model.getId())
                .status(model.getStatus())
                .averageRating(model.getAverageRating())
                .overview(model.getOverview())
                .inProduction(model.isInProduction())
                .numberOfSeasons(model.getNumberOfSeasons())
                .releaseDate(model.getReleaseDate())
                .posterPath(model.getPosterPath())
                .trailerId(model.getTrailerId())
                .build();
    }

    public TvShow convertToEntity(TvShowDTO tvShowDTO) {
        return modelMapper.map(tvShowDTO, TvShow.class);
    }

    public TvShowGenre tvShowGenreDTOToEntity(TvShowGenreDTO tvShowGenreDTO) {
        return modelMapper.map(tvShowGenreDTO, TvShowGenre.class);
    }

    public TvShowPerson tvSHowPersonToEntity(TvShowPersonDTO tvShowPersonDTO) {
        return modelMapper.map(tvShowPersonDTO, TvShowPerson.class);
    }

    @Override
    public boolean addTvShowToWatchList(Long userId, Integer tvShowId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }
        TvShow tvShow = tvShowRepository.findById(tvShowId).orElse(null);
        if (tvShow == null) {
            return false;
        }
        TvShowWatchList tvShowWatchListExists = tvShowWatchlistRepository.findByTvShowIdAndUserId(tvShowId, userId);
        if (tvShowWatchListExists != null) {
            return false;
        }
        TvShowWatchList tvShowWatchList = new TvShowWatchList(
                userId,
                tvShowId,
                false
        );
        tvShowWatchlistRepository.save(tvShowWatchList); // Use non-static instance
        return true;
    }


    @Override
    public List<TvShowWatchListDTO> getWatchListTvShowsByUserId(Long userId) {
        List<Object[]> results = tvShowRepository.getWatchlistByUserId(userId);
        return results.stream()
                .map(r -> new TvShowWatchListDTO((Integer) r[0], (String) r[1], (String) r[2], (String) r[3], (LocalDate) r[4],(Integer) r[5]))
                .collect(Collectors.toList());
    }

    @Override
    public boolean removeTvShowfromWatchList(Integer id) {
        TvShowWatchList tvShowWatchList = tvShowWatchlistRepository.findById(id).orElse(null);
        if(tvShowWatchList == null){
            return false;
        }
        tvShowWatchlistRepository.delete(tvShowWatchList);
        return true;
    }

    @Override
    public boolean addCommentToTvShows(Long userId, Integer tvShowId, AddCommentDTO addCommentDTO) {
        User user = userRepository.findById(userId).orElse(null);
        TvShow tvShow = tvShowRepository.findById(tvShowId).orElse(null);

        if (user == null || tvShow == null || addCommentDTO.getText().trim().isEmpty()) {
            return false;
        }

        Comment comment = new Comment();
        comment.setFirstName(user.getFirstName());
        comment.setLastName(user.getLastName());
        comment.setTvShow(tvShow);
        comment.setUser(user);
        comment.setText(addCommentDTO.getText());
        comment.setDateTimeCreated(LocalDateTime.now());

        commentRepository.save(comment);
        return true;
    }


    @Override
    public boolean removeCommentTvShows(Integer id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null){
            return false;
        }
        commentRepository.delete(comment);
        return true;
    }

    @Override
    public boolean updateCommentTvShows(Integer id, String text) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null){
            return false;
        }
        comment.setText(text);
        commentRepository.save(comment);
        return true;
    }

}
