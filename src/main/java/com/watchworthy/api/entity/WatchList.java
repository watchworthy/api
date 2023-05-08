package com.watchworthy.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "watchlists")
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long userId;
    private Integer movieId;
    private boolean isWatched;

    public WatchList(Long userId,Integer movieId,boolean isWatched){
        this.userId=userId;
        this.movieId=movieId;
        this.isWatched =isWatched;
    }
}
