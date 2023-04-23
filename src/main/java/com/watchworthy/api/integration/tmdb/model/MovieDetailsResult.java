package com.watchworthy.api.integration.tmdb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsResult {

    private Long id;
    private String title;
    private String overview;
    private LocalDate release_date;
    private String poster_path;
    private String backdrop_path;
    private Double vote_average;
    private Integer vote_count;

}

