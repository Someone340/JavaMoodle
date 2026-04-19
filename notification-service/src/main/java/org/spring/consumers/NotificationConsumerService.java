package org.spring.consumers;

import org.spring.DTO.UserEvent;
import org.spring.services.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {
    private final EmailService emailService;

    public NotificationConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-topic", groupId = "notification-group")
    public void consume(UserEvent event) {
        emailService.sendNotification(event.getEmail(), event.getAction());
    }
}