package com.watchworthy.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tvshow_genre")
@Getter
@Setter
public class TvShowGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tvshow_id")
    private TvShow tvshow;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
