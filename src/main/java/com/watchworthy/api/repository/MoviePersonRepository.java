package com.watchworthy.api.repository;

import com.watchworthy.api.entity.MoviePerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviePersonRepository extends JpaRepository<MoviePerson, Integer> {
    List<MoviePerson> findByPersonId(Integer personId);
}
