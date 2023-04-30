package com.watchworthy.api.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Integer id;
    private String title;
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;

}
