package org.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.DTO.UserDTO;
import org.spring.assemblers.UserAssembler;
import org.spring.entity.UserEntity;
import org.spring.services.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.List;

@Tag(name = "Пользователи", description = "Управление данными пользователей")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserAssembler assembler;

    public UserController(UserService userService, UserAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping
    public CollectionModel<UserDTO> getAll() {
        logger.info("Called method getAll");
        return assembler.toCollectionModel(userService.findAll());
    }

    @Operation(summary = "Найти пользователя по ID")
    @ApiResponse(responseCode = "200", description = "Пользователь найден")
    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable int id) {
        logger.info("Called method findById");
        UserEntity entity = userService.findById(id);
        return ResponseEntity.ok(assembler.toModel(entity));
    }

    @Operation(summary = "Создать нового пользователя")
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {
        logger.info("Called method create");
        UserEntity created = userService.create(dto);
        return new ResponseEntity<>(assembler.toModel(created), HttpStatus.CREATED);
    }

    @Operation(summary = "Обновить данные пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable int id, @RequestBody UserDTO dto) {
        logger.info("Called method update");
        UserEntity updated = userService.update(id, dto);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        logger.info("Called method delete");
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}