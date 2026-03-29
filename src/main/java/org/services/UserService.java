package org.services;

import org.DAO.UserDAOClass;
import org.user.User;

import java.time.LocalDateTime;
import java.util.List;


public class UserService implements Service{
    private UserDAOClass usersDao = new UserDAOClass();

    public UserService() {
    }

    @Override
    public void createUser(User user) {
        usersDao.save(user);
    }

    @Override
    public User readUser(int id) {
        return usersDao.findByID(id);
    }

    @Override
    public void updateUser(User user) {
        usersDao.update(user);
    }

    @Override
    public void deleteUser(int id) {
        usersDao.delete(id);
    }

    @Override
    public void printAll() {
        List<User> users = usersDao.printAll();
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Override
    public LocalDateTime getDateById(int id) {
        User user = usersDao.findByID(id);
        return user.getCreatedAt();
    }
}
