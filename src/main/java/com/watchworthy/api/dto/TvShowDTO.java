package com.watchworthy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TvShowDTO {
    private Integer id;
    private String title;
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;
    private Double averageRating;
    private int numberOfSeasons;
    private int numberOfEpisodes;
    private boolean inProduction;
    private String status;
    private Set<SeasonDTO> seasons;
    private Set<GenreDTO> genres;
    private Set<PersonDTO> people;
}
