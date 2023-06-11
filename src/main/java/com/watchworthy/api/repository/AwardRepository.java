package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Award;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

    List<Award> findByWinner(boolean winner);

    List<Award> findByCategory(String category);

    List<Award> findByYear(int year);

    List<Award> findByMovieId(Long movieId);

}
