package com.watchworthy.api.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasicResponse {
    private HttpStatus httpStatus;
    private String message;

}
