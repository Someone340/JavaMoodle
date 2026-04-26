package org.spring;

import org.junit.jupiter.api.Test;
import org.spring.DTO.UserEvent;
import org.spring.enums.Actions;
import org.spring.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import java.time.Duration;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
@ActiveProfiles("test")
@EmbeddedKafka(partitions = 1, topics = {"user-topic"})
class NotificationServiceTest {

    @Autowired
    private KafkaTemplate<String, UserEvent> kafkaTemplate;

    @MockBean
    private EmailService emailService;

    @Test
    void mockTest() {
        UserEvent event = new UserEvent("test@example.com", Actions.CREATE);

        kafkaTemplate.send("user-topic", event);

        await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                    verify(emailService).sendNotification(eq("test@example.com"), eq("CREATE"));
                });
    }
}
