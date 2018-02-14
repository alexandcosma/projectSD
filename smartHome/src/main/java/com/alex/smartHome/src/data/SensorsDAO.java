package com.alex.smartHome.src.data;

import com.alex.smartHome.src.physicalEq.Sensor;
import org.hibernate.*;

/**
 * Created by cosma on 16.05.2017.
 */
public class SensorsDAO {
    private SessionFactory factory;

    public SensorsDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Sensor find(int pin) {
        Session session = factory.openSession();
        Sensor sensor = null;
        try {
            Query query = session.createQuery("FROM sensors WHERE pinId = :pinId");
            query.setParameter("pinId", pin);
            sensor = (Sensor) query.list().get(0);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return sensor;
    }

    public void insert(int sensor) {
        Session session = factory.openSession();
        Transaction tx = null;
        Sensor sensor1 = new Sensor(sensor);
        try {
            tx = session.beginTransaction();
            session.save(sensor1);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(int pin) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(find(pin));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
