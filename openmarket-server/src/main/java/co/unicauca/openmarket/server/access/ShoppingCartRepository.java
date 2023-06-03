package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase ShoppingCartRepository
 */
public class ShoppingCartRepository implements IShoppingCartRepository {

    /** Conexion */
    protected Connection conn;

    /**
     * Constructor por defecto
     */
    public ShoppingCartRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(ShoppingCart newCart) {
        try {
            // Validate cart
            if (newCart == null || newCart.getId() == 0L) {
                return false;
            }
            // this.connect();

            String sql = "INSERT INTO shopping_cart ( user_id, product_id, shopping_cart_quantity ) VALUES ( ?, ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newCart.getOwner().getId());
            pstmt.setLong(2, newCart.getProduct().getId());
            pstmt.setLong(3, newCart.getQuantity());
            return pstmt.executeUpdate() > 0;
            // this.disconnect();
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
            // this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShoppingCart newCart = new ShoppingCart(
                        rs.getLong("shopping_cart_id"),
                        new UserRepository().findById(rs.getLong("user_id")),
                        new ProductRepository().findById(rs.getLong("product_id")),
                        rs.getLong("shopping_cart_quantity"));
                carts.add(newCart);
            }
            // this.disconnect();

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
            // this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ShoppingCart newCart = new ShoppingCart(
                        rs.getLong("shopping_cart_id"),
                        new UserRepository().findById(rs.getLong("user_id")),
                        new ProductRepository().findById(rs.getLong("product_id")),
                        rs.getLong("shopping_cart_quantity"));
                carts.add(newCart);
            }
            // this.disconnect();

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
            // this.connect();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product newProduct = new Product(
                        rs.getLong("product_id"),
                        new UserRepository().findById(rs.getLong("user_id")),
                        new CategoryRepository().findById(rs.getLong("category_id")),
                        new StateProductRepository().findById(rs.getLong("state_product_id")),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getDouble("product_price"),
                        rs.getLong("product_stock"),
                        rs.getDouble("product_latitude"),
                        rs.getDouble("product_longitude"));
                Products.add(newProduct);
            }

            // this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Products;
    }

    @Override
    public boolean delete(User owner) {
        try {
            // this.connect();

            String sql = "DELETE FROM shopping_cart WHERE user_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, owner.getId());
            return pstmt.executeUpdate() > 0;
            // this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}