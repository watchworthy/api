package com.watchworthy.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Integer id;
    @NotEmpty(message = "Title is mandatory")
    private String title;
    @NotEmpty(message = "Overview is mandatory!!!!")
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;
    private String trailerLink;
    private List<GenreDTO> genres;
    private List<CommentDTO> comments;
    private List<PersonDTO> people;
}
