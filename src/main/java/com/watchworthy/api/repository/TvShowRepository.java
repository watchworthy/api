package com.watchworthy.api.repository;

import com.watchworthy.api.entity.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TvShowRepository extends  JpaRepository<TvShow, Integer>, JpaSpecificationExecutor<TvShow> {
}
