package com.watchworthy.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tvshow_watchlists")
public class TvShowWatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long userId;
    private Integer tvShowId;
    private boolean isWatched;


    public TvShowWatchList(Long userId,Integer tvShowId,boolean isWatched){
        this.userId=userId;
        this.tvShowId=tvShowId;
        this.isWatched =isWatched;

    }
}
