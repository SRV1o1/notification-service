package com.example.notification_service.service;

import com.example.notification_service.models.Notification;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NotificationSender {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend("notification.queue", message); // This sends a message to specified queue.
    }
}
