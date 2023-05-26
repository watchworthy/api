package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Season;
import com.watchworthy.api.entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowRepository extends JpaRepository <TvShow, Integer> {
}
