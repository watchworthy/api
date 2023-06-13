package com.watchworthy.api.service.impl;

import com.watchworthy.api.entity.Notifications;
import com.watchworthy.api.repository.NotificationRepository;
import com.watchworthy.api.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    public NotificationServiceImpl (NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }
    @Override
    public List< Notifications> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    @Override
    public boolean markAsRead(Integer id) {
        Notifications notification = notificationRepository.findById(id).orElse(null);
        if(notification == null){
            return false;
        }
        notification.setRead(true);
        notificationRepository.save(notification);

        return true;
    }

    @Override
    public int countNotificationsNotReadByUserId(Long userId) {
        return notificationRepository.countByUserIdAndIsRead(userId,false);
    }
}
