package com.watchworthy.api.controller;

import com.watchworthy.api.entity.Notifications;
import com.watchworthy.api.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin(origins = "*")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController (NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @GetMapping("/{userId}")
    public List<Notifications> getNotificationsByUserId (@PathVariable Long userId){
        return notificationService.getNotificationsByUserId(userId);
    }
    @GetMapping("/countNotificationsNotRead/{userId}")
    public int getNoticationCountNotRead (@PathVariable Long userId){
        return notificationService.countNotificationsNotReadByUserId(userId);
    }
    @PutMapping("/markAsRead/{id}")
    public ResponseEntity<Void> markAsRead (@PathVariable int id){
        boolean result = notificationService.markAsRead(id);
        if(result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
