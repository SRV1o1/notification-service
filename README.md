Notification Service Platform – RESTful Microservice

A scalable, multi-channel notification service built with Spring Boot, MySQL, and RabbitMQ. This platform enables reliable, asynchronous notification delivery across various channels (Email, SMS, Push), with full tracking and retry mechanisms.


Overview

The Notification Service Platform is designed to send notifications (Email, SMS, Push) efficiently, asynchronously, and reliably. The platform ensures fault tolerance with retry logic and robust message processing through RabbitMQ. The service tracks the status of each notification (PENDING, SENT, FAILED) and supports automatic retries upon failure.
Technologies

    Spring Boot – to create the RESTful microservice.

    MySQL – to store notification data.

    RabbitMQ – for reliable, asynchronous message processing.

    Docker – for easy deployment across different environments.

    JWT – for secure API access with role-based authentication.

Features

    Create and track notifications through a RESTful API.

    Multi-channel support: Email, SMS, Push notifications.

    Asynchronous message processing with RabbitMQ and retry logic.

    Automatic status tracking (PENDING, SENT, FAILED) and retry on failure.

    Secure API with JWT and role-based access control.

    Dockerized for easy deployment.

Future Improvements

    Add role-based access control to restrict sensitive APIs (e.g., limit access to notification history or admin operations).
    Extend to support real message delivery via third-party providers.
