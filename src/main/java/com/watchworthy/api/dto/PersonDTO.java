package com.watchworthy.api.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Integer id;
    private String name;
    private String gender;
    private String posterPath;
    private String biography;
}
