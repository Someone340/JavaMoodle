package org.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.utils.HybernateSessionFactory;
import org.user.User;

import java.util.List;

public class UserDAOClass implements UserDAO{
    @Override
    public User findByID(int id) {
        return HybernateSessionFactory.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public void save(User user) {
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
        session.close();
    }

    @Override
    public void update(User user) {
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
        session.close();
    }

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
        session.close();
    }

    @Override
    public List<User> printAll() {
        List<User> users = (List<User>)  HybernateSessionFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}
