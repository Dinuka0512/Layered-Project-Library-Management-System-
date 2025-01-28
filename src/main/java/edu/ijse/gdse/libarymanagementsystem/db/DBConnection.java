package edu.ijse.gdse.libarymanagementsystem.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;
    private static DBConnection dbConnection;

    private final String url = "jdbc:mysql://localhost:3306/library_At_Bandaragama";
    private final String user = "root";
    private final String password = "0512";

    private DBConnection() throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection(url,user,password);
    }

    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }
}
