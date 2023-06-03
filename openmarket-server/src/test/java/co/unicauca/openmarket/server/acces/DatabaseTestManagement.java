package co.unicauca.openmarket.server.acces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.xml.namespace.QName;

import org.junit.jupiter.api.extension.ExtensionContext.Store;

import co.unicauca.openmarket.server.access.DatabaseConnection;

public class DatabaseTestManagement {

    private static DatabaseTestManagement instance;
    private Connection connection;

    private DatabaseTestManagement(){
        if (connection == null) {
            this.connection = DatabaseConnection.getInstance().getConnection();
        }
    
    }

    public static DatabaseTestManagement getInstance() {
        if (instance == null) {
            synchronized (DatabaseTestManagement.class) {
                if (instance == null) {
                    instance = new DatabaseTestManagement();
                }
            }
        }
        return instance;
    }

  public void truncateTableAll() {
    try {
        String[] tables = {"state_product", "orders", "category", "user", "rol", "product", "shopping_cart", "order_status", "delivery"};

        for (String table : tables) {
            String sql = "DELETE FROM " + table + "; ";
            if(table == "orders" || table == "delivery"){
                String sqlStatus = "UPDATE sqlite_sequence SET seq=0 WHERE name = '" + table + "';";
                PreparedStatement pstmStatus = connection.prepareStatement(sqlStatus);
                pstmStatus.executeUpdate();
            }
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.executeUpdate();
            System.out.println(pstm);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
                
    

    public void truncateTablebyName(String table_name){
        try{
            String sql = "DELETE FROM " + table_name;
            if(table_name == "orders" || table_name == "delivery"){
                String sqlStatus = "UPDATE sqlite_sequence SET seq=0 WHERE name = '" + table_name + "';";
                PreparedStatement pstmStatus = connection.prepareStatement(sqlStatus);
                pstmStatus.executeUpdate();
            }
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.executeUpdate();
            System.out.println(pstm);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertRols(){

        try {
        String sqlRol = "INSERT INTO rol (rol_name) VALUES " +
                "('vendedor'), " +
                "('comprador'), " +
                "('repartidor'), " +
                "('administrador')";
        PreparedStatement pstm;
        pstm = connection.prepareStatement(sqlRol);
        pstm.executeUpdate();
      
            pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertUser(){
        try{
            String sqlUser = "INSERT INTO user (rol_id, user_birth_date, user_email, user_phone, user_card, user_realname, user_username, user_password, user_address) VALUES " +
                 "(2, '1990-01-01', 'usuario1@example.com', '123456789', '1234567890', 'Usuario 1', 'usuario1', 'password1', 'Dirección 1'), " +
                 "(3, '1990-02-02', 'usuario2@example.com', '987654321', '0987654321', 'Usuario 2', 'usuario2', 'password2', 'Dirección 2'), " +
                 "(1, '1990-03-03', 'usuario3@example.com', '555555555', '1111111111', 'Usuario 3', 'usuario3', 'password3', 'Dirección 3')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlUser);
            pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertCategory(){
        try{
            String sqlCategory = "INSERT INTO category (category_name) VALUES " +
                     "('Categoría 1'), " +
                     "('Categoría 2'), " +
                     "('Categoría 3')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlCategory);
            pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertStateProduct(){
        try{
            String sqlStateProduct = "INSERT INTO state_product (state_product_name) VALUES " +
            "('disponible'), " +
            "('no disponible')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlStateProduct);
            pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertProduct(){
        try{
            String sqlProduct = "INSERT INTO product (user_id, category_id, state_product_id, product_name, product_description, product_price, product_stock, product_latitude, product_longitude) VALUES " +
                    "(1, 1, 1, 'Producto 1', 'Descripción del producto 1', 10.99, 50, 123.456, 789.012), " +
                    "(2, 2, 1, 'Producto 2', 'Descripción del producto 2', 19.99, 20, 345.678, 901.234), " +
                    "(3, 3, 2, 'Producto 3', 'Descripción del producto 3', 5.99, 10, 567.890, 123.456)";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlProduct);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertShoppingCart(){
        try{
            String sqlShoppingCart = "INSERT INTO shopping_cart (user_id, product_id, shopping_cart_quantity) VALUES " +
                         "(1, 1, 2), " +
                         "(2, 2, 1), " +
                         "(3, 3, 3)";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlShoppingCart);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertOrderStatus(){
        try{
            String sqlOrderStatus = "INSERT INTO order_status (order_status_name) VALUES " +
                        "('entregado'), " +
                        "('cancelado'), " +
                        "('en espera')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlOrderStatus);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insertOrders(){
        try{
            String sqlOrders = "INSERT INTO orders (user_id, product_id, orders_id, order_status_id, orders_price, orders_qualification, orders_date) VALUES " +
                   "(1, 1, 1, 1, 10.99, NULL, '2023-05-29'), " +
                   "(2, 2, 2, 2, 19.99, NULL, '2023-05-29'), " +
                   "(3, 3, 3, 3, 5.99, NULL, '2023-05-29')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlOrders);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void insertDelivery(){
        try{
            String sqlDelivery = "INSERT INTO delivery (orders_id, deliveryman_id, delivery_receiver, delivery_date) VALUES " +
                    "(1, 3, 'Receptor 1', '2023-05-29'), " +
                    "(2, 2, 'Receptor 2', '2023-05-29'), " +
                    "(3, 1, 'Receptor 3', '2023-05-29')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlDelivery);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
