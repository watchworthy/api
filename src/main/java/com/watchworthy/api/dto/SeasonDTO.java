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
public class SeasonDTO {
    private Integer id;
    private String airDate;
    private Set<EpisodeDTO> episodes;
    private String name;
    private String overview;
    private String posterPath;
    private int seasonNumber;
}
