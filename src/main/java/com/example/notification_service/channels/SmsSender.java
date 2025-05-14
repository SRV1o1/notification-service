package com.example.notification_service.channels;

import com.example.notification_service.dtos.NotificationMessageDTO;
import org.springframework.stereotype.Component;

@Component("SMS")
public class SmsSender implements NotificationChannelSender {
    @Override
    public void sendChannelNotification(NotificationMessageDTO dto) {
        System.out.println("📱 Sending SMS to " + dto.getRecipient() + ": " + dto.getMessage());
    }
}
