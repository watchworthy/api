package com.watchworthy.api.repository;

import com.watchworthy.api.entity.TestParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestParentRepository extends JpaRepository<TestParent , Long> {
}
