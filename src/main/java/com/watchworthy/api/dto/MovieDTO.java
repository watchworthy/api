package com.watchworthy.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@AllArgsConstructor

public class MovieDTO {

    private Integer id;
    @NotEmpty(message = "Title is mandatory")
    private String title;
    @NotEmpty(message = "Overview is mandatory!!!!")
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;
    private List<GenreDTO> genres;
    private HashSet<CommentDTO> comments;
    private List<PersonDTO> people;
    private String trailerId;

    public MovieDTO(){

    }
}
