package com.watchworthy.api.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "tv_show")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TvShow {
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

    @Column(name = "trailer_id")
    private String trailerId;

    @Column(name = "average_rating")
    private Double averageRating;

    @Column(name = "number_of_seasons")
    private int numberOfSeasons;

    @Column(name = "number_of_episodes")
    private int numberOfEpisodes;

    @Column(name = "in_production")
    private boolean inProduction;

    private String status;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(
            mappedBy = "tvshow",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private Set<Season> seasons = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tvshows")
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tvshows")
    private Set<Person> people = new HashSet<>();

    //tv show comment

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;


}
