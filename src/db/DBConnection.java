package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/food_db",
                "root",
                ""
            );

            System.out.println("Database Connected Successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return con;
    }

    // ✅ MAIN METHOD ADDED
    public static void main(String[] args) {
        getConnection();
    }
}