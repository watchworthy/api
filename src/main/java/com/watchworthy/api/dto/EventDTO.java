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
public class EventDTO {
    private Integer id;

    @NotEmpty(message = "Name is mandatory")
    private String name;

    private LocalDate date;
    private String posterPath;
//    private List<CommentDTO> comments;
}
