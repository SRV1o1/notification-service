package com.example.notification_service.channels;

import com.example.notification_service.dtos.NotificationMessageDTO;
import org.springframework.stereotype.Component;

@Component("PUSH")
public class PushSender implements NotificationChannelSender {
    @Override
    public void sendChannelNotification(NotificationMessageDTO dto) {
        System.out.println("📲 Sending Push Notification to " + dto.getRecipient() + ": " + dto.getMessage());
    }
}
