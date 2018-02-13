package data;

import physicalEq.Sensor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by cosma on 16.05.2017.
 */
public class SensorsGateway {

    public Sensor find(int pin){
        Sensor sensor = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Sensors WHERE pinId IS " + pin + " ;" );
            if ( rs.next() )
            {
                sensor = new Sensor(rs.getInt("pinId"));
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

        return sensor;
    }

    public void insert(int sensor)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate( "INSERT INTO sensors VALUES (" + sensor + " );");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void delete(int pin)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM sensors WHERE pinId is " + pin + " ;");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}
