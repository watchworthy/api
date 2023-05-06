package com.watchworthy.api.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenContainingResponse {
    private HttpStatus httpStatus;
    private String message;
    private String token;
}