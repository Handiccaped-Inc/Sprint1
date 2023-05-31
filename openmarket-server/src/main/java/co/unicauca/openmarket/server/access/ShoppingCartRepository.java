package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShoppingCartRepository implements IShoppingCartRepository {
    
    private Connection conn;

    public ShoppingCartRepository(){
    }

    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        //String url = "jdbc:sqlite:C:/sqlite/db/myDatabase.db"; //Para Windows
        String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean save(ShoppingCart newCart){
        try {
            //Validate cart
            if (newCart == null || newCart.getId() == 0L) {
                return false;
            }
            //this.connect();

            String sql = "INSERT INTO shopping_cart ( user_id, product_id, shopping_cart_quantity ) VALUES ( ?, ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newCart.getOwner().getId());
            pstmt.setLong(2, newCart.getProduct().getId());
            pstmt.setLong(3, newCart.getQuantity());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> carts = new ArrayList<>();
        try {

            String sql = "SELECT * FROM shopping_cart";
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShoppingCart newCart = new ShoppingCart(
                    UserService.findUserById(rs.getLong("user_id")),
                    ProductService.findProductbyId(rs.getLong("product_id")),
                    rs.getLong("shopping_cart_id"),
                    rs.getLong("shopping_cart_quantity")
                );
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

    @Override
    public List<ShoppingCart> findRepoByOwner(User owner) {
        List<ShoppingCart> carts = new ArrayList<>();
        try {

            String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getId());
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShoppingCart newCart = new ShoppingCart(
                    UserService.findUserById(rs.getLong("user_id")),
                    ProductService.findProductbyId(rs.getLong("product_id")),
                    rs.getLong("shopping_cart_id"),
                    rs.getLong("shopping_cart_quantity")
                );
                carts.add(newCart);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

    @Override
    public List<Product> findByOwner(User owner) {
        List<Product> Products = new ArrayList<>();
        try {

            String sql = "SELECT * FROM shopping_cart JOIN product ON shopping_cart.product_id = product.product_id WHERE shopping_cart.user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getId());
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product(
                rs.getLong("Product_id"), 
                UserService.findUserById(rs.getLong("user_id")), 
                CategoryService.findCategoryById(rs.getLong("category_id")), 
                StateService.findStateById(rs.getString("product_state")), 
                rs.getString("product_name"), 
                rs.getString("product_description"), 
                rs.getDouble("product_price"), 
                rs.getLong("product_stock"), 
                rs.getDouble("product_latitude"), 
                rs.getDouble("product_longitude")
                );
                Products.add(newProduct);
            }

            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Products;
    }


    @Override
    public boolean delete(User owner) {
        try {
            //Validate product
            if (owner != null) {
                return false;
            }
            //this.connect();

            String sql = "DELETE FROM shopping_cart WHERE user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getId());
            pstmt.executeUpdate();
            //this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}