package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.Boiler;
import com.alex.smartHome.src.model.HeatedObj;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosma on 16.05.2017.
 */
public class BoilersDAO {
    private SessionFactory factory;

    public BoilersDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public HeatedObj find(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<HeatedObj> heatedObjects = null;
        HeatedObj heatedObj = null;
        try {
            Query query = session.createQuery("FROM boilers WHERE name = :name");
            query.setParameter("name", name);
            heatedObjects = query.list();

            heatedObj = new Boiler(heatedObjects.get(0));
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return heatedObj;
    }

    public List<HeatedObj> findAll() {
        List<HeatedObj> heatedObjectsFinal = new ArrayList<>();
        List<HeatedObj> heatedObjects;
        Session session = factory.openSession();

        try {
            Query query = session.createQuery("FROM boilers");
            heatedObjects = query.list();

            for (int i = 0; i < heatedObjects.size(); i++) {
                heatedObjectsFinal.add(new Boiler(heatedObjects.get(i)));
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return heatedObjectsFinal;
    }

    public void insert(HeatedObj boiler) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(boiler);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(String name) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(find(name));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void modifyTemp(String name, float newTemp) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            HeatedObj boiler = find(name);
            boiler.setReqTemp(newTemp);
            session.update(boiler);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
