package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e.name, e.date, e.posterPath FROM event e WHERE e.userId = :userId")
    List<Object[]> getEventListByUserId(@Param("userId") Long userId);
}
