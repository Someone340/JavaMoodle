package org.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.utils.HybernateSessionFactory;
import org.entity.UserEntity;

import java.util.List;
/**
 * Реализация интерфейса UserDAO для работы с сущностью UseerEntity
 */

public class UserDAOClass implements UserDAO{

    /**
     * Метод, выполняющий поиск объекта по его ID
     * @param id уникальный идентификатор пользователя
     * @return объект UserEntity
     */
    @Override
    public UserEntity findByID(int id) {
        return HybernateSessionFactory.getSessionFactory().openSession().get(UserEntity.class, id);
    }

    /**
     * Метод, добавляющий новый объект в базу данных.
     * @param user объект для добавления
     */
    @Override
    public void save(UserEntity user) {
        Session session = HybernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Created successfully!");
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    /**
     * Метод, обновляющий данные в базе данных
     * @param user объект, который будет обновляться
     */
    @Override
    public void update(UserEntity user) {
        Session session = HybernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            System.out.println("Updated successfully!");
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    /**
     * Метод, удаляющий строку из базы данных
     * @param id уникальный идентификатор строки для удаления из базы даанных
     */
    @Override
    public void delete(int id) {
        Session session = HybernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(findByID(id));
            transaction.commit();
            System.out.println("Deleted successfully!");
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
    }

    /**
     * Метод, возвращающий все строки из базы данных для их последующего отображения
     * @return возвращает  List<UserEntity>, содержащий все строки таблицы
     */
    @Override
    public List<UserEntity> printAll() {
        List<UserEntity> users = (List<UserEntity>)  HybernateSessionFactory.getSessionFactory().openSession().createQuery("From UserEntity").list();
        return users;
    }
}
