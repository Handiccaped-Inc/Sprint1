package co.unicauca.payment.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private static final String url = "jdbc:sqlite:C:/sqlite/db/payment-service.db";

    /**
     * Constructor privado para implementar el patrón Singleton
     * 
     */
    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
        }
    }

    /**
     * Implementación del patrón Singleton
     * 
     * @return
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * @return La conexion
     * @throws java.sql.SQLException
     */
    public Connection getConnection() {
        return connection;
    }

}
