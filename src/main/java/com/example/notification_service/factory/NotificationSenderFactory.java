package com.example.notification_service.factory;

import com.example.notification_service.channels.NotificationChannelSender;
import com.example.notification_service.models.NotificationType;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class NotificationSenderFactory {

    private final Map<String, NotificationChannelSender> senders;

    public NotificationChannelSender getSender(NotificationType type) {
        return senders.get(type.name());
    }
}