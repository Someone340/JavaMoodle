package org.services;

import org.DAO.UserDAOClass;
import org.entity.UserEntity;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сервисный класс, выступающий прослойкой между доступом к данным(DAO)
 * и консольным интерфейсом, реализующий интерфейс Service
 */
public class UserService implements Service{

    /** Объект доступа для взаимодействия с базой данных */
    private UserDAOClass usersDao = new UserDAOClass();

    public UserService() {
    }

    /**
     * Метод для создания нового пользователя
     * @param user объект, содержащий данные нового пользователя
     */
    @Override
    public void createUser(UserEntity user) {
        usersDao.save(user);
    }

    /**
     * Метод для получения информации о конкретном пользователе по его идентификатору
     * @param id уникальный идентификатор пользователя
     * @return возвращает найденный объект UserEntity
     */
    @Override
    public UserEntity readUser(int id) {
        return usersDao.findByID(id);
    }

    /**
     * Метод, обновляющий данные о пользователе
     * @param user объект для обновления данных
     */
    @Override
    public void updateUser(UserEntity user) {
        usersDao.update(user);
    }

    /**
     * метод предназначенный для удаления пользователя по его ID
     * @param id уникальный идентификатор пользователя
     */
    @Override
    public void deleteUser(int id) {
        usersDao.delete(id);
    }

    /**
     * Метод для консольного отображения всех строк из базы данных
     */
    @Override
    public void printAll() {
        List<UserEntity> users = usersDao.printAll();
        for (UserEntity user : users) {
            System.out.println(user.toString());
        }
    }

    /**
     * Вспомогательный метод, используемый при обновлении данных у пользователя.
     * Предназначен для получения данных о времени создания строки.
     * @param id уникальный идентификатор пользователя
     * @return возвращает дату создания пользователя
     */
    @Override
    public LocalDateTime getDateById(int id) {
        UserEntity user = usersDao.findByID(id);
        return user.getCreatedAt();
    }
}
