package com.example.notification_service.service;

import com.example.notification_service.dtos.NotificationMessageDTO;
import com.example.notification_service.models.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Component
@Service
public class NotificationSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    public void sendMessage(NotificationMessageDTO dto) {
        try {
            String messageJson = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend("notification.queue", messageJson); // This sends a message to specified queue.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
