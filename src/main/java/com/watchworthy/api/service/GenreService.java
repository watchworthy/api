package com.watchworthy.api.service;

import com.watchworthy.api.dto.GenreDTO;

import java.util.List;

public interface GenreService {
   void save(GenreDTO genreDTO);
   void update(Integer genderId ,GenreDTO genreDTO);
   GenreDTO getGenre(Integer id);
   List<GenreDTO> getGenres();
   void delete(Integer id);
}
