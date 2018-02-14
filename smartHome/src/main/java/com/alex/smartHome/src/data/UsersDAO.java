package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.User;
import org.hibernate.*;

/**
 * Created by cosma on 16.05.2017.
 */
public class UsersDAO {
    private SessionFactory factory;

    public UsersDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public User find(int id) {
        Session session = factory.openSession();
        User user = null;
        try {
            Query query = session.createQuery("FROM users WHERE id = :id");
            query.setParameter("id", id);
            user = (User) query.list().get(0);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return user;
    }

    public void insert(User user) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(int id) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(find(id));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public boolean adminCheck() {
        Session session = factory.openSession();
        try {
            Query query = session.createQuery("FROM users WHERE admin = 1");
            return query.list().size() > 0;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }
}
