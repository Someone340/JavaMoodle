package org.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.DTO.UserEvent;
import org.spring.services.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendDirect(@RequestBody UserEvent event) {
        logger.info("Sending notification about {} action to user {}", event.getAction(), event.getEmail());
        emailService.sendNotification(event.getEmail(), event.getAction());
        return ResponseEntity.ok().build();
    }
}