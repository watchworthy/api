package com.watchworthy.api.controller;

import com.watchworthy.api.dto.GenreDTO;
import com.watchworthy.api.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/genre")
@CrossOrigin(origins = "*")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<Void> createGenre(@RequestBody GenreDTO genreDTO) {
        genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGenre(@PathVariable("id") Integer genreId, @RequestBody GenreDTO genreDTO) {
        genreService.update(genreId, genreDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable("id") Integer genreId) {
        GenreDTO genreDTO = genreService.getGenre(genreId);
        if (genreDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(genreDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenres() {
        List<GenreDTO> genreDTOs = genreService.getGenres();
        return ResponseEntity.status(HttpStatus.OK).body(genreDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable("id") Integer genreId) {
        genreService.delete(genreId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
