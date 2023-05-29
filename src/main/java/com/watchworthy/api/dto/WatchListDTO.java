package com.watchworthy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WatchListDTO {
    private Integer id;
    private String title;
    private String overview;
    private String posterPath;
    private LocalDate releaseDate;

}
