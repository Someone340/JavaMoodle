package org.spring.services;

import org.spring.DTO.UserDTO;
import org.entity.UserEntity;
import org.spring.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        List<UserEntity> entities = userRepository.findAll();
        List<UserDTO> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(mapToDTO(entity));
        }
        return dtos;
    }

    public UserDTO create(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setAge(dto.getAge());

        UserEntity saved = userRepository.save(entity);
        return mapToDTO(saved);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(UserEntity entity) {
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getAge()
        );
    }
}
