package com.watchworthy.api.repository;

import com.watchworthy.api.entity.Notifications;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notifications, Integer> {

}
