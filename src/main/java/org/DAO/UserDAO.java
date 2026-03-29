package org.DAO;

import org.user.User;

import java.util.List;

public interface UserDAO {
    User findByID(int id);
    void save(User user);
    void update(User user);
    void delete(int id);
    List<User> printAll();
}
