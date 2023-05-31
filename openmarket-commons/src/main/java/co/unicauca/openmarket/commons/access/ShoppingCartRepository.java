package co.unicauca.openmarket.commons.access;

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
                ShoppingCart newCart = new ShoppingCart();
                newCart.setOwner(UserService.findUserById(rs.getLong("user_id")));
                newCart.setProduct(ProductService.findProductbyId(rs.getLong("product_id")));
                newCart.setId(rs.getLong("shopping_cart_id"));
                newCart.setQuantity(rs.getLong("shopping_cart_quantity"));
                carts.add(newCart);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

    /*@Override
    public ShoppingCart findById(Long id) {
        try {

            String sql = "SELECT * FROM shopping_cart WHERE shopping_cart_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ShoppingCart newCart = new ShoppingCart();
                newCart.setOwner(UserService.findUserById(rs.getLong("user_id")));
                newCart.setProduct(ProductService.findProductbyId(rs.getLong("product_id")));
                newCart.setId(rs.getLong("shopping_cart_id"));
                newCart.setQuantity(rs.getLong("shopping_cart_quantity"));
                return newCart;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/

    @Override
    public List<ShoppingCart> findByOwner(User owner) {
        List<ShoppingCart> carts = new ArrayList<>();
        try {

            String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getId());
            //this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShoppingCart newCart = new ShoppingCart();
                newCart.setOwner(UserService.findUserById(rs.getLong("user_id")));
                newCart.setProduct(ProductService.findProductbyId(rs.getLong("product_id")));
                newCart.setId(rs.getLong("shopping_cart_id"));
                newCart.setQuantity(rs.getLong("shopping_cart_quantity"));
                carts.add(newCart);
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carts;
    }

    /*@Override
    public ShoppingCart findByOwner(User owner) {
        try {

            String sql = "SELECT * FROM shopping_cart WHERE user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getId());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                ShoppingCart newCart = new ShoppingCart();
                newCart.setOwner(UserService.findUserById(rs.getLong("user_id")));
                newCart.setProduct(ProductService.findProductbyId(rs.getLong("product_id")));
                newCart.setId(rs.getLong("shopping_cart_id"));
                newCart.setQuantity(rs.getLong("shopping_cart_quantity"));
                return newCart;
            } else {
                return null;
            }
            //this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCartRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }*/

    /*@Override
    public boolean edit(User owner, ShoppingCart cart) {
    try {
        // Validate product
        if (owner == null || cart == null) {
            return false;
        }
        // this.connect();

        String sql = "UPDATE shopping_cart SET product_id=?, shopping_cart=? WHERE user_id=?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setLong(1, cart.getProduct().getId());
        pstmt.setLong(2, cart.getQuantity());
        pstmt.setLong(3, owner.getId());
        pstmt.executeUpdate();
        // this.disconnect();
        return true;
    } catch (SQLException ex) {
        Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
    }*/

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