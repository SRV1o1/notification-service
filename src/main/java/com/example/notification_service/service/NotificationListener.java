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
            Optional<Notification> optional = notificationRepository.findById(dto.getId());
            if (optional.isPresent()) {
                Notification notification = optional.get();
                notification.setStatus(NotificationStatus.PENDING);
                notificationRepository.save(notification);
                processNotification(notification, dto);
            } else {
                handleNotificationNotFound(dto.getId());
            }
        } catch (Exception e) {
            e.printStackTrace(); // handle malformed JSON etc.
        }
    }

    private void processNotification(Notification notification, NotificationMessageDTO dto) {
        // If retry count is less than 3, attempt sending
        if (notification.getRetryCount() < 3) {
            boolean isSent = trySendingNotification(dto);
            if (isSent) {
                notification.setStatus(NotificationStatus.SENT);
                notificationRepository.save(notification);
            } else {
                notification.setRetryCount(notification.getRetryCount() + 1);
                notification.setStatus(NotificationStatus.FAILED); // Set to FAILED for now, could be PENDING if you prefer
                notificationRepository.save(notification);
                System.out.println("Retry attempt " + notification.getRetryCount() + " failed");
            }
        } else {
            notification.setStatus(NotificationStatus.STOPPED);
            notificationRepository.save(notification);
            System.out.println("Retry limit reached for Notification ID: " + notification.getId());
        }
    }


    private boolean trySendingNotification(NotificationMessageDTO dto) {
        try {
            NotificationChannelSender channelSender = notificationChannelSender.getSender(dto.getType());
            channelSender.sendChannelNotification(dto);
            return true;
        } catch (Exception e) {
            System.out.println("Sending notification failed" + e);
            return false;
        }
    }

    private void handleNotificationNotFound(Long id) {
        System.out.println("Notification not found for ID: " + id);
    }

}
