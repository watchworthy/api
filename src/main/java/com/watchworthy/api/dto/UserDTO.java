package com.watchworthy.api.dto;

import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
        private Long userId;
        private String email;
        private String firstName;
        private String lastName;
        private String password;

}
