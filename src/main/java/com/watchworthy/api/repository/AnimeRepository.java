package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Anime;
import com.watchworthy.api.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
