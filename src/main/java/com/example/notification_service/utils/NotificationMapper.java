package com.example.notification_service.utils;

import com.example.notification_service.dtos.NotificationMessageDTO;
import com.example.notification_service.models.Notification;


public class NotificationMapper {
    public static NotificationMessageDTO toMessageDTO(Notification notification) {
        NotificationMessageDTO dto = new NotificationMessageDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setRecipient(notification.getRecipient());
        dto.setType(notification.getType());
        return dto;
    }
}
