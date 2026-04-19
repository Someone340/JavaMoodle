package org.spring.producers;

import org.spring.DTO.UserEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    public UserProducerService(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String email, String action) {
        UserEvent event = new UserEvent(email, action);
        kafkaTemplate.send("user-topic", event);
    }
}
