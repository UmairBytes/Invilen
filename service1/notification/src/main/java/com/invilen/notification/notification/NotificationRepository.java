package com.invilen.notification.notification;

import org.springframework.data.mongodb.repository.MongoRepository;

import javax.management.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
