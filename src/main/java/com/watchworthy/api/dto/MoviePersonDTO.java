package com.watchworthy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoviePersonDTO {
    private Long id;
    private Integer movieId;
    private Integer personId;
}
