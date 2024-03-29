package com.watchworthy.api.service;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.model.PageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TvShowService {
    void save(TvShowDTO TvShowDTO);
    void update(Integer TvShowId ,TvShowDTO TvShowDTO);
    TvShowDTO getTvShow(Integer id);
    PageModel<TvShowDTO> getTvShows(Integer page, Integer size, String q, Integer genre);
    void delete(Integer id);
    void addGenre(TvShowGenreDTO tvShowGenreDTO);
    void addPersonToTvShow(Integer tvId, Integer personId);
//    void getSeasons(Integer tvShowId);
//    void getPeople(Integer tvShowId);
    List<TvShowDTO> getTvShowsByPerson(Integer personId);
    void addPoster(Integer tvId , MultipartFile file);

    boolean addTvShowToWatchList(Long userId,Integer movieId);
    List<TvShowWatchListDTO> getWatchListTvShowsByUserId(Long userId);
    boolean removeTvShowfromWatchList(Integer id);

    //comments stuff

    boolean addCommentToTvShows (Long userId , Integer tvShowId, AddCommentDTO addCommentDTO);
    boolean removeCommentTvShows(Integer id);
    boolean updateCommentTvShows(Integer id, String text);

    List<TvShowGenreDTO> getGenres(Integer tvId);

    List<CommentDTO> getComments(Integer tvId);

    List<TvShowPersonDTO> getPeople(Integer tvId);
}
