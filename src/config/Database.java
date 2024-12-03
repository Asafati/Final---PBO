package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    // Variabel untuk menyimpan informasi koneksi
    private final String dbName;
    private final String userName;
    private final String password;
    private final String host;
    private final String port;
    private Connection connection;

    // Konstruktor untuk inisialisasi informasi koneksi
    public Database(final String dbName, final String userName, final String password, final String host, final String port) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    // Getter untuk mendapatkan objek Connection
    public Connection getConnection() {
        return connection;
    }

    // Menyambungkan ke database
    public void setup() {
        // URL koneksi ke database MySQL
        String mysqlConnUrlTemplate = "jdbc:mysql://%s:%s/%s";
        try {
            // Memuat driver JDBC MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Membuat koneksi ke database
            connection = DriverManager.getConnection(
                    String.format(mysqlConnUrlTemplate, host, port, dbName),
                    userName,
                    password
            );
            System.out.println("Database connected!");
        } catch (SQLException | ClassNotFoundException e) {
            // Menangani kesalahan jika gagal terkoneksi
            throw new RuntimeException("Database connection failed: " + e.getMessage(), e);
        }
    }

    // Menutup koneksi ke database
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close connection: " + e.getMessage(), e);
            }
        }
    }
}
