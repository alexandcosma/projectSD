package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.HeatingObj;
import org.hibernate.*;

/**
 * Created by cosma on 16.05.2017.
 */
public class HeatingSourceDAO {
    private SessionFactory factory;

    public HeatingSourceDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public HeatingObj find() {
        HeatingObj heatingObj = null;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            Query query = session.createQuery("FROM heating_source");
            heatingObj = (HeatingObj) query.list().get(0);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return heatingObj;
    }

    public void insert(HeatingObj heatingObj) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(heatingObj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(find());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
