package com.example.notification_service.controller;

import com.example.notification_service.dtos.NotificationRequestDTO;
import com.example.notification_service.models.Notification;
import com.example.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/post")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDTO request) {
        Notification savedNotification = notificationService.createNotification(request);
        return ResponseEntity.status(201).body("Notification created with ID: " + savedNotification.getId());
    }

}
