package org.DAO;

import org.entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.utils.HybernateSessionFactory;

import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Testcontainers
class UserDAOClassTest {

    private UserDAOClass userDAO;

    @Container
    private final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15.2")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass");

    @BeforeEach
    void setUp() {
        HybernateSessionFactory.shutdownSessionFactory();

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(UserEntity.class);

        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());

        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        SessionFactory testFactory = configuration.buildSessionFactory(builder.build());
        HybernateSessionFactory.setSessionFactory(testFactory);

        userDAO = new UserDAOClass();
    }

    @AfterEach
    void tearDown() {
        HybernateSessionFactory.shutdownSessionFactory();
    }

    @Test
    void save() {
        UserEntity user = new UserEntity("TestUser", "testSave@gmail.com", 20, LocalDateTime.now());

        userDAO.save(user);

        UserEntity check = userDAO.findByID(user.getId());
        assertEquals("TestUser", check.getName());
    }

    @Test
    void update() {
        UserEntity user = new UserEntity("TestUserUpdate", "testUpdate@gmail.com", 20, LocalDateTime.now());
        userDAO.save(user);

        user.setName("Changed name");
        userDAO.update(user);

        UserEntity updated = userDAO.findByID(user.getId());
        assertEquals("Changed name", updated.getName());
    }

    @Test
    void delete() {
        UserEntity user = new UserEntity("TestUserRemove", "testRemove@gmail.com", 20, LocalDateTime.now());
        userDAO.save(user);

        UserEntity check = userDAO.findByID(user.getId());

        if (check.getName().equals("TestUserRemove")) {
            userDAO.delete(user.getId());
            UserEntity deleted = userDAO.findByID(user.getId());
            assertNull(deleted);
        }
    }

    @Test
    void print() {
        UserEntity user1 = new UserEntity("Test1", "test1@gmail.com", 20, LocalDateTime.now());
        UserEntity user2 = new UserEntity("Test2", "test2@gmail.com", 20, LocalDateTime.now());

        userDAO.save(user1);
        userDAO.save(user2);

        List<UserEntity> users = userDAO.printAll();
        assertEquals(users.get(0).getName(), user1.getName());
        assertEquals(users.get(1).getName(), user2.getName());
    }
}
