package com.example.notification_service.repository;

import com.example.notification_service.models.Notification;
import com.example.notification_service.models.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusIn(List<NotificationStatus> statuses);
}