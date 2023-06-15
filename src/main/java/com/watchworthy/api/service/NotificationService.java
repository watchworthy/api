package com.watchworthy.api.service;

import com.watchworthy.api.entity.Notifications;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NotificationService {
    List<Notifications> getNotificationsByUserId (Long userId);
    boolean markAsRead (Integer id);
    int countNotificationsNotReadByUserId(Long userId);
}
