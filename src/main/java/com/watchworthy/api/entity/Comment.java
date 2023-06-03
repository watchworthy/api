package com.watchworthy.api.entity;

import jakarta.persistence.*;
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
    private String text;
    private String firstName;
    private String lastName;
    private LocalDateTime dateTimeCreated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    //tvshow stuff

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tvshow_id")
    private TvShow tvShow;

}
