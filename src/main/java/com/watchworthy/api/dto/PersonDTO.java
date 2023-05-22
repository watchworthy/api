package com.watchworthy.api.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Integer id;
    private String name;
    private String gender;
    private String posterPath;
}
