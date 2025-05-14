package com.example.notification_service.channels;

import com.example.notification_service.dtos.NotificationMessageDTO;

public interface NotificationChannelSender {

    void sendChannelNotification(NotificationMessageDTO dto);
}
