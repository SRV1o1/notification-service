package com.example.notification_service.channels;

import com.example.notification_service.dtos.NotificationMessageDTO;
import org.springframework.stereotype.Component;

@Component("EMAIL")
public class EmailSender implements NotificationChannelSender {

    @Override
    public void sendChannelNotification(NotificationMessageDTO dto) {
        System.out.println("📧 Sending Email to " + dto.getRecipient() + ": " + dto.getMessage());
        throw new RuntimeException();
    }
}