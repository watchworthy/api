package com.watchworthy.api.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashSet;

@Data
@Builder
@AllArgsConstructor
public class AnimeDTO {

    private Long id;
    private String name;
    private String category;
    private HashSet<ReplyDTO> replyDTOS;


}
