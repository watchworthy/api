package com.watchworthy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor

public class WatchListDTO implements Serializable {
    private Integer id;
    private String title;
    private String overview;
    private String posterPath;
    private LocalDate releaseDate;
    private Integer watchlistId;

    public WatchListDTO(Integer id, String title, String overview, String posterPath, LocalDate releaseDate, Integer watchlistId){
        this.id=id;
        this.title = title;
        this.overview = overview;
        this.posterPath=posterPath;
        this.releaseDate = releaseDate;
        this.watchlistId=watchlistId;
    }
}
