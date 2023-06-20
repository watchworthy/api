package com.watchworthy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TvShowGenreDTO {
    private Long id;
    private String name;
    private Integer tvShowId;
    private Integer genreId;
}
