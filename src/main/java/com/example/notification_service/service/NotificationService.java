package com.example.notification_service.service;

import com.example.notification_service.dtos.NotificationMessageDTO;
import com.example.notification_service.dtos.NotificationRequestDTO;
import com.example.notification_service.models.Notification;
import com.example.notification_service.models.NotificationStatus;
import com.example.notification_service.repository.NotificationRepository;
import com.example.notification_service.utils.NotificationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    NotificationRepository repository;
    NotificationSender sender;

    public Notification createNotification(NotificationRequestDTO request) {
        Notification notification = new Notification();
        notification.setRecipient(request.getRecipient());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setStatus(NotificationStatus.PENDING);
        notification.setCreatedAt(LocalDateTime.now());


        Notification savedNotification = repository.save(notification);
        NotificationMessageDTO dto = NotificationMapper.toMessageDTO(savedNotification);
        sender.sendMessage(dto);

        return savedNotification;
    }
}
