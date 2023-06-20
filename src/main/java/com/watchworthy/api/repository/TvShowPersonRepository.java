package com.watchworthy.api.repository;

import com.watchworthy.api.entity.TvShowPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TvShowPersonRepository extends JpaRepository<TvShowPerson, Integer> {
    List<TvShowPerson> findByPersonId(Integer personId);
    List<TvShowPerson> findAllByTvshowId(Integer tvId);

}
