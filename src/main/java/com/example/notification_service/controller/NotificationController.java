package com.example.notification_service.controller;

import com.example.notification_service.dtos.NotificationRequestDTO;
import com.example.notification_service.models.Notification;
import com.example.notification_service.repository.NotificationRepository;
import com.example.notification_service.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    @PostMapping("/post")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequestDTO request) {
        Notification savedNotification = notificationService.createNotification(request);
        return ResponseEntity.status(201).body("Notification created with ID: " + savedNotification.getId());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Notification> sendNotification(@PathVariable Long id) {
        return notificationRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get/all")
    public Page<Notification> getAllNotifications(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return notificationRepository.findAll(pageable);//todo: return a dto not every DB fields.
    }

}
