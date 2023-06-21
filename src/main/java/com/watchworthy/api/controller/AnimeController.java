package com.watchworthy.api.controller;

import com.watchworthy.api.dto.AnimeDTO;
import com.watchworthy.api.dto.ReplyDTO;
import com.watchworthy.api.service.AnimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/anime")
@CrossOrigin(origins = "*")
public class AnimeController {
    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAnimeDetails(@PathVariable("id") Long id) {
        try {
            AnimeDTO animeDTO = animeService.getAnimeDetails(id);
            return ResponseEntity.ok(animeDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<AnimeDTO> getAnimeList() {
        return animeService.getAnimeList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveAnime(@RequestBody AnimeDTO animeDTO) {
        animeService.saveAnime(animeDTO);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public AnimeDTO updateAnime(
            @PathVariable("id") Long id,
            @RequestBody AnimeDTO animeDTO) {
        return animeService.updateAnime(id, animeDTO);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteAnime(@PathVariable("id") Long id) {
        animeService.deleteAnime(id);
    }

    @RequestMapping(path = "/addreply/{animeId}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addReplyToAnime(
            @PathVariable Long animeId,
            @PathVariable Long userId,
            @RequestBody ReplyDTO replyDTO) {
        boolean result = animeService.addReplyToAnime(userId, animeId, replyDTO);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/removereply/{replyId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeReply(@PathVariable Long replyId) {
        boolean result = animeService.removeReply(replyId);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(path = "/updatereply/{replyId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateReply(
            @PathVariable Long replyId,
            @RequestBody String text) {
        boolean result = animeService.updateReply(replyId, text);
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
