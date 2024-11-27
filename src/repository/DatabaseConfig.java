package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/final_pbo";
    private static final String USER = "root";  // Ganti dengan username MySQL Anda
    private static final String PASSWORD = "";  // Ganti dengan password MySQL Anda

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
