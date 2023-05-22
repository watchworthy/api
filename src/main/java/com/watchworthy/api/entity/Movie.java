package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "movie")
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "overview", length = 1000)
    private String overview;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "average_rating")
    private Double averageRating;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "movies")
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "movies")
    private Set<Person> people = new HashSet<>();

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    public void calculateAverageRating(List<Integer> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            averageRating = 0.0;
            return;
        }

        double sum = 0.0;
        for (Integer rating : ratings) {
            sum += rating;
        }
        averageRating = sum / ratings.size();
    }
}
