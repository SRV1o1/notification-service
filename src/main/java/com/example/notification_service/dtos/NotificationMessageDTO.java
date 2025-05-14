package com.example.notification_service.dtos;

import com.example.notification_service.models.NotificationType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor //need to check this functioning.
public class NotificationMessageDTO {
    private Long id;
    private String recipient;
    private String message;
    private NotificationType type;
}
