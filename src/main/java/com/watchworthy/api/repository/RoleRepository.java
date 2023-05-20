package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role , Integer> {
}
