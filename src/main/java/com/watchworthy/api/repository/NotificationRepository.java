package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Notifications;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notifications, Integer> {
    List<Notifications> findByUserId(Long userId);
    int countByUserIdAndIsRead(Long userId, boolean isRead);
}
