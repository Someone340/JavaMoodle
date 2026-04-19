package org.spring.services;

import org.spring.DTO.UserEvent;
import org.springframework.kafka.annotation.KafkaListener;

public class ListenerService {
    private final EmailService emailService;

    public ListenerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(
            topics = "user-topic",
            groupId = "notification-group",
            containerFactory = "kafkaListenerContainerFactory"
    )

    public void listen(UserEvent event) {
        emailService.sendNotification(event.getEmail(), event.getAction());
    }
}
