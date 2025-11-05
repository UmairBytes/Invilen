package com.invilen.notification.kafkaConsumer;

import com.invilen.notification.dto.OrderConfirmation;
import com.invilen.notification.email.EmailService;
import com.invilen.notification.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "order-topic")
    public void consumerOrderConfirmation(OrderConfirmation orderConfirmation){
        System.out.println("successfully fetched from kafka");
        emailService.sendOrderConfirmation(orderConfirmation);
        System.out.println("Successfully send order confirmation email");

    }
}
