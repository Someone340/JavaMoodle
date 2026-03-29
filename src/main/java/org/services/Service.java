package org.services;

import org.user.User;

import java.time.LocalDateTime;

public interface Service {
    void createUser(User user);
    User readUser(int id);
    void updateUser(User user);
    void deleteUser(int id);
    void printAll();
    LocalDateTime getDateById(int id);
}
