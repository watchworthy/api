package com.watchworthy.api.repository;

import com.watchworthy.api.entity.TestChild;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestChildRepository extends JpaRepository<TestChild, Long> {
}
