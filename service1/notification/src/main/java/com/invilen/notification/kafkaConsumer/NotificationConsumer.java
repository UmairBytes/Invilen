package com.invilen.notification.kafkaConsumer;

import com.invilen.notification.dto.OrderConfirmation;
import com.invilen.notification.email.EmailService;
import com.invilen.notification.notification.Notification;
import com.invilen.notification.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-topic")
    public void consumerOrderConfirmation(OrderConfirmation orderConfirmation) throws MessagingException {
        System.out.println("successfully fetched from kafka");
        notificationRepository.save(
                Notification.builder()
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        System.out.println("Saved to notification database");
        emailService.sendOrderConfirmation(orderConfirmation);
        System.out.println("Successfully send order confirmation email");

    }
}
