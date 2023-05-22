package com.watchworthy.api.repository;

import com.watchworthy.api.entity.MovieGenre;
import com.watchworthy.api.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Integer> {
}
