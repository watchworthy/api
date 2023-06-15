package com.watchworthy.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor

public class MovieDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Title is mandatory")
    private String title;
    @NotEmpty(message = "Overview is mandatory!!!!")
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;
    private List<GenreDTO> genres;
    private List<CommentDTO> comments;
    private List<PersonDTO> people;


    public MovieDTO(){

    }
}
