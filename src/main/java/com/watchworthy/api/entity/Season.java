package com.watchworthy.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "season")
@Getter
@Setter
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "air_date")
    private String airDate;

    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(
            mappedBy = "season",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @Builder.Default
    private Set<Episode> episodes = new HashSet<>();

    private String name;

    private String overview;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "season_number")
    private int seasonNumber;

    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tvshow_id",
            foreignKey = @ForeignKey(name = "fk_tvshow_season_fk"))
    private TvShow tvshow;

}
