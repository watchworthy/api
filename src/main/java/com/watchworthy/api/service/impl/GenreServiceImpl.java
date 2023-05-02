package com.watchworthy.api.service.impl;

import com.watchworthy.api.dto.GenreDTO;
import com.watchworthy.api.dto.MovieDTO;
import com.watchworthy.api.entity.Genre;
import com.watchworthy.api.repository.GenreRepository;
import com.watchworthy.api.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public GenreServiceImpl(GenreRepository genreRepository, ModelMapper modelMapper) {
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(GenreDTO genreDTO) {
        genreRepository.save(convertToEntity(genreDTO));
    }

    @Override
    public void update(Integer genreId, GenreDTO genreDTO) {
        Genre genre = genreRepository.findById(genreId).orElse(null);
        if (genre != null) {
            genreDTO.setId(genreId);
            genreRepository.save(convertToEntity(genreDTO));
        }
    }

    @Override
    public GenreDTO getGenre(Integer id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        return genre != null ? convertToDto(genre) : null;
    }

    @Override
    public List<GenreDTO> getGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        genreRepository.findById(id).ifPresent(genre -> genreRepository.deleteById(id));
    }

    public GenreDTO convertToDto(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }

    public Genre convertToEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }
}
