package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isActive;

    public User(String email, String firstName,String lastName, String password, boolean isActive){
        this.email=email;
        this.firstName=firstName;
        this.lastName = lastName;
        this.password = password;
        this.isActive = isActive;

    }
}
