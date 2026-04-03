package org.DAO;

import org.entity.UserEntity;
import java.util.List;

/**
 * Интерфейс для реализации CRUD операций с сущностью UserEntity
 */
public interface UserDAO {

    /**
     * Метод, выполняющий поиск объекта по его ID
     * @param id уникальный идентификатор пользователя
     * @return объект UserEntity
     */
    UserEntity findByID(int id);

    /**
     * Метод, добавляющий новый объект в базу данных.
     * @param user объект для добавления
     */
    void save(UserEntity user);

    /**
     * Метод, обновляющий данные в базе данных
     * @param user объект, который будет обновляться
     */
    void update(UserEntity user);

    /**
     * Метод, удаляющий строку из базы данных
     * @param id уникальный идентификатор строки для удаления из базы даанных
     */
    void delete(int id);

    /**
     * Метод, возвращающий все строки из базы данных для их последующего отображения
     * @return возвращает  List<UserEntity>, содержащий все строки таблицы
     */
    List<UserEntity> printAll();
}
