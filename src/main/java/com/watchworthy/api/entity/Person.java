package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "person")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "poster_path")
    private String posterPath;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_person",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")})
    private Set<Movie> movies = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tvshow_person",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "tvshow_id")})
    private Set<TvShow> tvshows = new HashSet<>();

    @Column(name = "biography", length = 1000)
    private String biography;

}
