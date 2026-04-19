package org.spring.controllers;

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

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<Void> sendDirect(@RequestBody UserEvent event) {
        emailService.sendNotification(event.getEmail(), event.getAction());
        return ResponseEntity.ok().build();
    }
}