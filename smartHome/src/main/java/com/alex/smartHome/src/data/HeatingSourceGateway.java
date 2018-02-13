package data;

import model.HeatingObj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by cosma on 16.05.2017.
 */
public class HeatingSourceGateway {

    public HeatingObj find(){
        HeatingObj heatingObj = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM HeatingSource ;" );
            if ( rs.next() )
            {
                heatingObj = new HeatingObj(rs.getString("name"), rs.getInt("pinId"));
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

        return heatingObj;
    }

    public void insert(HeatingObj heatingObj)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate( "INSERT INTO heatingSource VALUES ('" + heatingObj.getName() + "', " + heatingObj.getRelPin() + " );");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void delete()
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM heatingSource ;");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
