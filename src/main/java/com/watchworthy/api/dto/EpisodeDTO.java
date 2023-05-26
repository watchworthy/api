package com.watchworthy.api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EpisodeDTO {
    private Integer id;
    private String name;
    private String overview;
    private LocalDate releaseDate;
    private String posterPath;
    private int seasonNumber;
}
