package org.spring.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.DTO.UserEvent;
import org.spring.enums.Actions;
import org.springframework.beans.factory.annotation.Value; // Добавлен импорт
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducerService {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserProducerService.class);

    @Value("${user.topic.name}")
    private String userTopic;

    public UserProducerService(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String email, Actions action) {
        logger.info("Sending information about {} action made for account: {}", action.toString(), email);
        UserEvent event = new UserEvent(email, action);
        kafkaTemplate.send(userTopic, event);
    }
}
