package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.Boiler;
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
            Query query = session.createQuery("FROM rooms WHERE name = :name");
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
            Query query = session.createQuery("SELECT room FROM heated_places");
            List<Boolean> rooms = query.list();

            Query query1 = session.createQuery("FROM heated_places");
            heatedObjects = query1.list();

            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i)) {
                    heatedObjectsFinal.add(new Room(heatedObjects.get(i)));
                } else {
                    heatedObjectsFinal.add(new Boiler(heatedObjects.get(i)));
                }
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }

        return heatedObjectsFinal;
    }

    public void insert(HeatedObj heatedObj) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (heatedObj.getClass().getName().equals("com.alex.smartHome.src.model.Room"))
                session.save(heatedObj);
            tx.commit();

            if (heatedObj.getClass().getName().equals("model.Room"))
                stmt.executeUpdate("INSERT INTO heatedPlaces VALUES ('" + heatedObj.getName() + "', " + heatedObj.getReqTemp() + ", " + heatedObj.getRelayPin() + ", " + heatedObj.getSensorPin() + ", " + 1 + " );");
            else
                stmt.executeUpdate("INSERT INTO heatedPlaces VALUES ('" + heatedObj.getName() + "', " + heatedObj.getReqTemp() + ", " + heatedObj.getRelayPin() + ", " + heatedObj.getSensorPin() + ", " + 0 + " );");

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void delete(String name) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate("DELETE FROM heatedPlaces WHERE name is '" + name + "' ;");
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void modifyTemp(String name, float newTemp) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE heatedPlaces set reqTemp = " + newTemp + " where name is '" + name + "' ;");
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
