package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role , Integer> {
    Optional<Role> findRoleByName(String name);
}
