package com.invilen.order.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    public void sendSuccessOrderConfirmation(OrderConfirmation orderConfirmation) {
        System.out.println("Sending message to kafka");
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "orderConfirmation")
                .build();
        kafkaTemplate.send(message);
        System.out.println("message successfully send to kafka");

    }
}
