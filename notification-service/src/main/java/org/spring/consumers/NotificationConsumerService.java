package org.spring.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.DTO.UserEvent;
import org.spring.services.EmailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationConsumerService.class);

    public NotificationConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${user.topic.name}", groupId = "notification-group")
    public void consume(UserEvent event) {
        logger.info("Consumed notification about {} action for user {}", event.getAction(), event.getEmail());
        emailService.sendNotification(event.getEmail(), event.getAction());
    }
}