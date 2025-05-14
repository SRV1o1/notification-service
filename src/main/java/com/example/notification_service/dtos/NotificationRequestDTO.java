package com.example.notification_service.dtos;

import com.example.notification_service.models.NotificationType;
import lombok.Data;

@Data
public class NotificationRequestDTO {
    private String recipient;
    private String message;
    private NotificationType type;
}
