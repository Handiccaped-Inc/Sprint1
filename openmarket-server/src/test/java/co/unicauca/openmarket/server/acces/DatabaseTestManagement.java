package co.unicauca.openmarket.server.acces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



import co.unicauca.openmarket.server.access.DatabaseConnection;

/**
 * Clase para la gestion de la base de datos en las pruebas unitarias
 */
public class DatabaseTestManagement {

    private static DatabaseTestManagement instance; // Instancia de la clase 
    private Connection connection; // Conexion a la base de datos

    /**
     * Constructor de la clase
     */
    private DatabaseTestManagement(){
        if (connection == null) {
            this.connection = DatabaseConnection.getInstance().getConnection();
        }
    
    }

    /**
     * Metodo para obtener la instancia de la clase
     * @return instancia de la clase DatabaseTestManagement
     */
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

    /**
     * Metodo para truncar una tabla de la base de datos
     */
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
                
    
/**
 * Metodo para truncar una tabla de la base de datos
 * @param table_name nombre de la tablal que se desea truncar
 */
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
                 "(3, '1990-02-02', 'usuario2@example.com', '987654321', '5678901234', 'Usuario 2', 'usuario2', 'password2', 'Dirección 2'), " +
                 "(1, '1990-03-03', 'usuario3@example.com', '555555555', '1234567890', 'Usuario 3', 'usuario3', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'Dirección 3'),"+
                 "(2, '1990-03-03', 'usuario10@example.com', '555555555', '9876543210', 'Usuario 4', 'usuario4', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'Dirección 4')";
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
                     "('Categoria 1'), " +
                     "('Categoria 2'), " +
                     "('Categoria 3')";
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
                    "(2, 2, 2, 'Producto 2', 'Descripción del producto 2', 19.99, 20, 345.678, 901.234), " +
                    "(3, 3, 1, 'Lambo Rey', 'En un carro Deportivo', 5.99, 10, 567.890, 123.456),"+
                    "(3, 3, 1, 'Twingo', 'En un carro normal', 5.99, 10, 567.890, 123.456)";
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
                         "(4, 3, 3),"+
                         "(4, 4, 4)";
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
                   "(4, 3, 3, 3, 5.99, NULL, '2023-05-29'),"+
                   "(4, 4, 4, 3, 5.99, NULL, '2023-05-29')";
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
                    "(1, 2, 'Receptor 1', '2023-05-29'), " +
                    "(2, 2, 'Receptor 2', '2023-05-29'), " +
                    "(3, 2, 'Receptor 3', '2023-05-29')";
            PreparedStatement pstm;
            pstm = connection.prepareStatement(sqlDelivery);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
