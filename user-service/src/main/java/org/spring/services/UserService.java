package org.spring.services;

import org.spring.DTO.UserDTO;
import org.spring.entity.UserEntity;
import org.spring.enums.Actions;
import org.spring.producers.UserProducerService;
import org.spring.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final UserProducerService producerService;

    public UserService(UserRepository userRepository, UserProducerService producerService) {
        this.userRepository = userRepository;
        this.producerService = producerService;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserEntity create(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());

        UserEntity saved = userRepository.save(entity);
        producerService.sendNotification(saved.getEmail(), Actions.CREATE);
        return saved;
    }

    public UserEntity findById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public UserEntity update(int id, UserDTO dto) {
        UserEntity entity = findById(id);

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());

        return userRepository.save(entity);
    }

    public void delete(int id) {
        UserEntity entity = findById(id);
        producerService.sendNotification(entity.getEmail(), Actions.DELETE);
        userRepository.deleteById(id);
    }
}
