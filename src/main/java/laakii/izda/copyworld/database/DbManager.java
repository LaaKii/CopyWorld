package laakii.izda.copyworld.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static Connection conn = null;

    private DbManager() {

    }

    public static Connection getConnection() {

        if (conn==null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                // Standard credentials due to local db. Never use this when going live. Always set/change username and password.
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/copyworld?serverTimezone=UTC", "root",null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }

        return conn;

    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
