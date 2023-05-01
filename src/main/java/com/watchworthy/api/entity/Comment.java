package com.watchworthy.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.type.IntersectionType;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long userId;
    private Integer movieId;
    private LocalDateTime dateTimeCreated;
    private String message;
}
