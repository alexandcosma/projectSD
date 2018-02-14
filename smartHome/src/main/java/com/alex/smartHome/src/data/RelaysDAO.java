package com.alex.smartHome.src.data;

import com.alex.smartHome.src.physicalEq.Relay;
import org.hibernate.*;

import java.util.List;

/**
 * Created by cosma on 16.05.2017.
 */
public class RelaysDAO {
    private SessionFactory factory;

    public RelaysDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public Relay find(int pin) {
        Relay relay = null;
        Session session = factory.openSession();
        Transaction tx = null;
        List<Relay> relayList;
        try {
            Query query = session.createQuery("FROM Relay WHERE pinId = :pinId");
            query.setParameter("pinId", pin);
            relayList = query.list();
            relay = relayList.size() > 0 ? relayList.get(0) : null;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return relay;
    }

    public Relay findProtection() {
        Relay relay = null;
        Session session = factory.openSession();
        Transaction tx = null;
        List<Relay> relayList;
        try {
            Query query = session.createQuery("FROM Relay WHERE protection = 1");
            relayList = query.list();
            relay = relayList.size() > 0 ? relayList.get(0) : null;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return relay;
    }

    public void insert(int relay, boolean protection) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("INSERT into Relay VALUES (:pin , :protection)");
            query.setParameter("pin", relay);
            query.setParameter("protection", protection ? 1 : 0);
            query.executeUpdate();
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

    public void deleteProtection() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Relay WHERE protection = 1");
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
