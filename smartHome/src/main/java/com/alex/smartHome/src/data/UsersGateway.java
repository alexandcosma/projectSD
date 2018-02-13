package data;

import model.AdminUser;
import model.RegularUser;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by cosma on 16.05.2017.
 */
public class UsersGateway {

    public User find(int id){
        User user = null;
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM users WHERE id IS " + id + " ;" );
            if ( rs.next() )
            {
                if(rs.getBoolean("admin"))
                    user = new AdminUser(rs.getInt("id"), rs.getString("password"));
                else
                    user = new RegularUser(rs.getInt("id"), rs.getString("password"));
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

        return user;
    }

    public void insert(User user)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            if(user.getClass().getName().equals("model.AdminUser"))
                stmt.executeUpdate( "INSERT INTO users VALUES (" + user.getId() + ", '" + user.getPassword() + "', " + 1 + " );");
            else
                stmt.executeUpdate( "INSERT INTO users VALUES (" + user.getId() + ", '" + user.getPassword() + "', " + 0 + " );");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void delete(int id)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            stmt.executeUpdate( "DELETE FROM users WHERE id is " + id + " ;");
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public boolean adminCheck(){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM users WHERE admin = 1;" );
            if ( rs.next() )
            {
                rs.close();
                stmt.close();
                c.close();
                return true;
            }
            else
            {
                rs.close();
                stmt.close();
                c.close();
                return false;
            }
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return false;
    }
}
