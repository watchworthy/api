package com.watchworthy.api.repository;

import com.watchworthy.api.entity.TestChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestChildRepository extends JpaRepository<TestChild, Long> {
    Optional<TestChild> findByIdAndTestParent_Id(Long childId, Long parentId);
}
