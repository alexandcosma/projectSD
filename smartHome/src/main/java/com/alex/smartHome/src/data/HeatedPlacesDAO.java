package com.alex.smartHome.src.data;

import com.alex.smartHome.src.model.HeatedObj;
import com.alex.smartHome.src.model.Boiler;
import com.alex.smartHome.src.model.Room;

import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosma on 16.05.2017.
 */
public class HeatedPlacesDAO {
    private SessionFactory factory;

    public HeatedPlacesDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public HeatedObj find(String name){
        Session session = factory.openSession();
        Transaction tx = null;
        HeatedObj heatedObj = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM heatedPlaces WHERE name IS '" + name + "' ;" );
            if ( rs.next() )
            {
                if(rs.getBoolean("room")) {
                    heatedObj = new Room(rs.getString("name"), rs.getInt("sensor"), rs.getInt("relay"));
                    heatedObj.setReqTemp(rs.getFloat("reqTemp"));
                }
                else{
                    heatedObj = new Boiler(rs.getString("name"), rs.getInt("sensor"), rs.getInt("relay"));
                    heatedObj.setReqTemp(rs.getFloat("reqTemp"));
                }
            }
            else
            {
                rs.close();
                stmt.close();
                c.close();
                return null;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return heatedObj;
    }

    public List<HeatedObj> findAll(){
        List<HeatedObj> heatedObj = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM heatedPlaces ;" );
            while ( rs.next() )
            {
                if(rs.getBoolean("room")) {
                    heatedObj.add(new Room(rs.getString("name"), rs.getInt("sensor"), rs.getInt("relay")));
                    heatedObj.get(heatedObj.size()-1).setReqTemp(rs.getFloat("reqTemp"));
                }
                else{
                    heatedObj.add(new Boiler(rs.getString("name"), rs.getInt("sensor"), rs.getInt("relay")));
                    heatedObj.get(heatedObj.size()-1).setReqTemp(rs.getFloat("reqTemp"));
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return heatedObj;
    }

    public void insert(HeatedObj heatedObj)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();

            if(heatedObj.getClass().getName().equals("model.Room"))
                stmt.executeUpdate( "INSERT INTO heatedPlaces VALUES ('" + heatedObj.getName() + "', " + heatedObj.getReqTemp() + ", " + heatedObj.getRelayPin() + ", " + heatedObj.getSensorPin() + ", " + 1 + " );");
            else
                stmt.executeUpdate( "INSERT INTO heatedPlaces VALUES ('" + heatedObj.getName() + "', " + heatedObj.getReqTemp() + ", " + heatedObj.getRelayPin() + ", " + heatedObj.getSensorPin() + ", " + 0 + " );");

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void delete(String name){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM heatedPlaces WHERE name is '" + name + "' ;");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void modifyTemp(String name, float newTemp){
        Connection c = null;
        Statement stmt = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate("UPDATE heatedPlaces set reqTemp = " + newTemp + " where name is '" + name + "' ;");
            stmt.close();
            c.commit();
            c.close();
        }catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
