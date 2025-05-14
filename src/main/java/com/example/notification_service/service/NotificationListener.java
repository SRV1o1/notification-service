package com.example.notification_service.service;

import com.example.notification_service.channels.NotificationChannelSender;
import com.example.notification_service.dtos.NotificationMessageDTO;
import com.example.notification_service.factory.NotificationSenderFactory;
import com.example.notification_service.models.Notification;
import com.example.notification_service.models.NotificationStatus;
import com.example.notification_service.repository.NotificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class NotificationListener {

    private final NotificationRepository notificationRepository;
    private final ObjectMapper objectMapper;
    private final NotificationSenderFactory notificationChannelSender;

    @RabbitListener(queues = "notification.queue")
    public void listen(String messageJson) {
        try {
            NotificationMessageDTO dto = objectMapper.readValue(messageJson, NotificationMessageDTO.class);

            System.out.println("Received Notification ID: " + dto.getId());
            NotificationChannelSender channelSender = notificationChannelSender.getSender(dto.getType());
            channelSender.sendChannelNotification(dto);

            Optional<Notification> optional = notificationRepository.findById(dto.getId());

            if (optional.isPresent()) {
                Notification notification = optional.get();
                notification.setStatus(NotificationStatus.SENT); // or FAILED
                notificationRepository.save(notification);
            } else {
                System.out.println("Notification not found for ID: " + dto.getId());
            }

        } catch (Exception e) {
            e.printStackTrace(); // handle malformed JSON etc.
        }
    }

}
