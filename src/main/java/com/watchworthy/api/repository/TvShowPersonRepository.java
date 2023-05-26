package com.watchworthy.api.repository;

import com.watchworthy.api.entity.MoviePerson;
import com.watchworthy.api.entity.TvShow;
import com.watchworthy.api.entity.TvShowPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TvShowPersonRepository extends JpaRepository<TvShowPerson, Integer> {
}
