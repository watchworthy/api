package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository <Episode, Integer> {
}
