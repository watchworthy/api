package com.watchworthy.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

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
    @NotEmpty(message = "Overview is mandatory")
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;
    private List<GenreDTO> genres;

}
