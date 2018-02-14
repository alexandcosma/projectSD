package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.HeatingObj;
import org.hibernate.*;

import java.util.List;

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
        List<HeatingObj> heatingObjs;
        try {
            Query query = session.createQuery("FROM HeatingObj");
            heatingObjs = query.list();
            heatingObj = heatingObjs.size() > 0 ? heatingObjs.get(0):null;
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
