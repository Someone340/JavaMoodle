package org.services;

import org.DAO.UserDAOClass;
import org.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserDAOClass userDAOMock;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser() {
        UserEntity user = new UserEntity("TestUser", "testSave@gmail.com", 20, LocalDateTime.now());
        userService.createUser(user);

        verify(userDAOMock, times(1)).save(user);
    }
    
    @Test
    void readUser() {
        UserEntity user = new UserEntity("TestUser", "testSave@gmail.com", 20, LocalDateTime.now());
        when(userDAOMock.findByID(0)).thenReturn(user);

        UserEntity read = userService.readUser(0);

        assertEquals(user, read);
    }

    @Test
    void updateUser() {
        UserEntity user = new UserEntity("TestUser", "testSave@gmail.com", 20, LocalDateTime.now());
        userService.createUser(user);

        user.setName("Test2");
        userService.updateUser(user);

        assertEquals("Test2", user.getName());
    }

    @Test
    void deleteUser() {
        userService.deleteUser(0);

        verify(userDAOMock).delete(0);
    }

    @Test
    void printAll() {
        List<UserEntity> users = new ArrayList<>();
        UserEntity user1 = new UserEntity("Test1", "test1@gmail.com", 20, LocalDateTime.now());
        UserEntity user2 = new UserEntity("Test2", "test2@gmail.com", 20, LocalDateTime.now());

        users.add(user1);
        users.add(user2);

        when(userDAOMock.printAll()).thenReturn(users);

        userService.printAll();

        assertEquals(user1, users.get(0));
        assertEquals(user2, users.get(1));
        verify(userDAOMock).printAll();
    }

    @Test
    void getDateById() {
        LocalDateTime now = LocalDateTime.now();
        UserEntity user = new UserEntity("TestUser", "testSave@gmail.com", 20, now);

        when(userDAOMock.findByID(1)).thenReturn(user);

        LocalDateTime dateTest = userService.getDateById(1);

        assertEquals(now, dateTest);
    }
}
