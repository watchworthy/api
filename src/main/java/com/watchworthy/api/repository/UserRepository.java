package com.watchworthy.api.repository;

import com.watchworthy.api.entity.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    @Modifying
    @Query("update User u set u.active = false where u.id = :userId")
    void deactivateAccount(@Param("userId") Long userId);
}
