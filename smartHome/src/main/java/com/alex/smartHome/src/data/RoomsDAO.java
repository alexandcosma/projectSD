package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.HeatedObj;
import com.alex.smartHome.src.model.Room;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosma on 16.05.2017.
 */
public class RoomsDAO {
    private SessionFactory factory;

    public RoomsDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public HeatedObj find(String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<HeatedObj> heatedObjects = null;
        HeatedObj heatedObj = null;
        try {
            Query query = session.createQuery("FROM Room WHERE name = :name");
            query.setParameter("name", name);
            heatedObjects = query.list();

            heatedObj = new Room(heatedObjects.get(0));
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
            Query query = session.createQuery("FROM Room");
            heatedObjects = query.list();

            for (int i = 0; i < heatedObjects.size(); i++) {
                heatedObjectsFinal.add(new Room(heatedObjects.get(i)));
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return heatedObjectsFinal;
    }

    public void insert(HeatedObj room) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(room);
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
            HeatedObj room = find(name);
            room.setReqTemp(newTemp);
            session.update(room);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
