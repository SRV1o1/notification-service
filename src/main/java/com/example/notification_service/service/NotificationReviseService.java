package com.example.notification_service.service;

import com.example.notification_service.channels.NotificationChannelSender;
import com.example.notification_service.dtos.NotificationMessageDTO;
import com.example.notification_service.factory.NotificationSenderFactory;
import com.example.notification_service.models.Notification;
import com.example.notification_service.models.NotificationStatus;
import com.example.notification_service.repository.NotificationRepository;
import com.example.notification_service.utils.NotificationMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationReviseService {
    private final NotificationRepository notificationRepository;
    private final NotificationSenderFactory notificationSenderFactory;

    @Scheduled(fixedRate = 10000)  // Runs every 5 minutes
    public void reviseFailedNotifications() {
        System.out.println(".......");
        List<Notification> failedNotifications = notificationRepository.findByStatusIn(List.of(NotificationStatus.FAILED, NotificationStatus.PENDING));

        for (Notification notification : failedNotifications) {
            if (notification.getRetryCount() < 3) {
                NotificationMessageDTO dto =NotificationMapper.toMessageDTO(notification);
                boolean isSent = trySendingNotification(dto);
                if (isSent) {
                    notification.setStatus(NotificationStatus.SENT);
                    notificationRepository.save(notification);
                } else {
                    notification.setRetryCount(notification.getRetryCount() + 1);
                    notification.setStatus(NotificationStatus.FAILED);
                    notificationRepository.save(notification);
                }
            }  else {
                notification.setStatus(NotificationStatus.STOPPED);
                notificationRepository.save(notification);
            }
        }
    }
    private boolean trySendingNotification(NotificationMessageDTO dto) {
        try {
            NotificationChannelSender channelSender = notificationSenderFactory.getSender(dto.getType());
            channelSender.sendChannelNotification(dto);
            return true;
        } catch (Exception e) {
            System.out.println("Sending notification failed" + e);
            return false;
        }
    }

}
