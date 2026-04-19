package org.spring.repository;

import org.spring.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями пользователей
 * Обеспечивает стандартные методы CRUD
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}