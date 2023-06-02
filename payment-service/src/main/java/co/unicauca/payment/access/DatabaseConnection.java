package co.unicauca.payment.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase DatabaseConnection
 */
public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private static final String url = "jdbc:sqlite:C:/sqlite/db/payment-service.db";

    /**
     * Constructor privado para implementar el patrón Singleton
     */
    private DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    connection.close();
                    System.out.println("Database Connection closed.");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Implementación del patrón Singleton
     * @return instancia
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
     * Obtiene la conexion
     * @return La conexion
     */
    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(url);
                } catch (SQLException e) {
                    System.out.println("Error establishing database connection: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
