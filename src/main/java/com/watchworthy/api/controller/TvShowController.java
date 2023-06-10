package com.watchworthy.api.controller;

import com.watchworthy.api.dto.*;
import com.watchworthy.api.model.PageModel;
import com.watchworthy.api.service.EpisodeService;
import com.watchworthy.api.service.SeasonService;
import com.watchworthy.api.service.TvShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tv")
public class TvShowController {
    private final EpisodeService episodeService;
    private final SeasonService seasonService;
    private final TvShowService tvShowService;

    public TvShowController(EpisodeService episodeService, SeasonService seasonService, TvShowService tvShowService) {
        this.episodeService = episodeService;
        this.seasonService = seasonService;
        this.tvShowService = tvShowService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createTvShow(@RequestBody TvShowDTO tvShowDTO) {
        tvShowService.save(tvShowDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{tvId}")
    public ResponseEntity<Void> updateTvShow(@PathVariable("tvId") Integer tvId, @RequestBody TvShowDTO tvShowDTO) {
        tvShowService.update(tvId, tvShowDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{tvId}")
    public ResponseEntity<TvShowDTO> getTvShow(@PathVariable("tvId") Integer tvId) {
        TvShowDTO seasonDTO = tvShowService.getTvShow(tvId);
        if (seasonDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(seasonDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public PageModel<TvShowDTO> getMovies(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size,
            @RequestParam(name = "q", defaultValue = "") String q,
            @RequestParam(name = "genre", defaultValue = "") Integer genre
    ) {
        return tvShowService.getTvShows(page, size, q,genre);
    }

    @DeleteMapping("/{tvId}")
    public ResponseEntity<Void> deleteTvShow(@PathVariable("tvId") Integer tvId) {
        tvShowService.delete(tvId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/genre", method = RequestMethod.POST)
    public void addGenre(
            @RequestBody TvShowGenreDTO tvShowGenreDTO) {
        tvShowService.addGenre(tvShowGenreDTO);
    }

    @RequestMapping(path = "/person", method = RequestMethod.POST)
    public void addPerson(
            @RequestBody TvShowPersonDTO tvShowPersonDTO) {
        tvShowService.addPersonToTvShow(tvShowPersonDTO);
    }

    @PostMapping("/season/{tvId}")
    public ResponseEntity<Void> createSeason(@PathVariable("tvId") Integer tvId, @RequestBody SeasonDTO seasonDTO) {
        seasonService.save(tvId, seasonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/season/{tvId}/{id}")
    public ResponseEntity<Void> updateSeason(@PathVariable("tvId") Integer tvId, @PathVariable("id") Integer seasonId, @RequestBody SeasonDTO seasonDTO) {
        seasonService.update(seasonId, seasonDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/season/{tvId}/{id}")
    public ResponseEntity<SeasonDTO> getSeason(@PathVariable("tvId") Integer tvId, @PathVariable("id") Integer seasonId) {
        SeasonDTO seasonDTO = seasonService.getSeason(seasonId);
        if (seasonDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(seasonDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/season/{tvId}")
    public ResponseEntity<List<SeasonDTO>> getSeasons(@PathVariable("tvId") Integer tvId) {
        List<SeasonDTO> seasonDTOs = seasonService.getSeasons(tvId);
        return ResponseEntity.status(HttpStatus.OK).body(seasonDTOs);
    }

    @DeleteMapping("/season/{tvId}/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable("tvId") Integer tvId, @PathVariable("id") Integer seasonId) {
        seasonService.delete(seasonId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/season/{tvId}/{seasonNumber}/episode")
    public ResponseEntity<Void> createEpisode(@PathVariable("tvId") Integer tvId, @PathVariable Integer seasonNumber, @RequestBody EpisodeDTO episodeDTO) {
        episodeService.save(tvId, seasonNumber, episodeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/season/{tvId}/{seasonNumber}/episode/{id}")
    public ResponseEntity<Void> updateEpisode(@PathVariable("tvId") Integer tvId, @PathVariable String seasonNumber, @PathVariable("id") Integer episodeId, @RequestBody EpisodeDTO episodeDTO) {
        episodeService.update(episodeId, episodeDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/season/{tvId}/{seasonNumber}/episode/{id}")
    public ResponseEntity<EpisodeDTO> getEpisode(@PathVariable("tvId") Integer tvId, @PathVariable String seasonNumber, @PathVariable("id") Integer episodeId) {
        EpisodeDTO episodeDTO = episodeService.getEpisode(episodeId);
        if (episodeDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(episodeDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/season/{tvId}/{seasonNumber}/episode")
    public ResponseEntity<List<EpisodeDTO>> getEpisodes(@PathVariable("tvId") Integer tvId, @PathVariable Integer seasonNumber) {
        List<EpisodeDTO> episodeDTOs = episodeService.getEpisodes(tvId, seasonNumber);
        return ResponseEntity.status(HttpStatus.OK).body(episodeDTOs);
    }

    @DeleteMapping("/season/{tvId}/{seasonNumber}/episode/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable("tvId") Integer tvId, @PathVariable String seasonNumber, @PathVariable("id") Integer episodeId) {
        episodeService.delete(episodeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/list/{personId}",method = RequestMethod.GET)
    public List<TvShowDTO> getTvShowsByPersonId(@PathVariable("personId") Integer personId){
        return tvShowService.getTvShowsByPerson(personId);
    }


    //WatchList TVShow Methods
    @RequestMapping(path = "/addtvshowtowatchlist/{userId}/{tvShowId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addTvShowToWatchList(@PathVariable Long userId, @PathVariable Integer tvShowId) {
        boolean result = tvShowService.addTvShowToWatchList(userId,tvShowId);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/removetvshowfromwatchlist/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeTvShowFromWatchlist (@PathVariable Integer id){
        boolean result = tvShowService.removeTvShowfromWatchList(id);
        if(result){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/gettvshowswatchlistbyuserid/{userId}",method = RequestMethod.GET)
    public ResponseEntity<List<TvShowWatchListDTO>> getTvShowWatchlistByUserId(@PathVariable("userId") Long userId) {
        List<TvShowWatchListDTO> tvShows = tvShowService.getWatchListTvShowsByUserId(userId);
        if (tvShows.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(tvShows, HttpStatus.OK);
        }
    }


    //Comments TvSHows Stuff

    @RequestMapping(path = "/addcommenttvshows/{tvShowId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<Void> addCommentToTvShows (@PathVariable Integer tvShowId, @PathVariable Long userId, @RequestBody AddCommentDTO addCommentDTO){
        boolean result =  tvShowService.addCommentToTvShows(userId,tvShowId,addCommentDTO);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/removecommenttvshows/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeCommentTvShows (@PathVariable Integer id){
        boolean result =  tvShowService.removeCommentTvShows(id);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/updatecommenttvshow/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCommentTvShows (@PathVariable Integer id, @RequestBody String text){
        boolean result =  tvShowService.updateCommentTvShows(id,text);
        if(result){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

}
