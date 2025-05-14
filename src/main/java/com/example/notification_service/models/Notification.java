package com.example.notification_service.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recipient; // email/phone/device token

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type; // EMAIL, SMS, PUSH

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationStatus status; // PENDING, SENT, FAILED

    @Column(nullable = false)
    private LocalDateTime createdAt;
}
