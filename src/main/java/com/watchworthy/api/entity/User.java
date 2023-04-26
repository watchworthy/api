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
    private String address;

    public User(String email, String firstName,String lastName, String password, String address){
        this.email=email;
        this.firstName=firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
    }
}
