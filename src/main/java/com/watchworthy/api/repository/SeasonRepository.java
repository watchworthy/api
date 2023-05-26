package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeasonRepository extends JpaRepository <Season, Integer> {
}
