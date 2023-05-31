package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.StateProduct;

public class ProductRepository implements IProductRepository {
    protected Connection conn;

    public ProductRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Product newProduct) {
        try {
            // Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            // this.connect();

            String sql = "INSERT INTO product (user_id, category_id, state_product_id, product_name, product_description, product_price, product_stock, product_latitude, product_longitude) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newProduct.getOwner().getId());
            pstmt.setLong(2, newProduct.getCategory().getId());
            pstmt.setLong(3, newProduct.getState().getId());
            pstmt.setString(4, newProduct.getName());
            pstmt.setString(5, newProduct.getDescription());
            pstmt.setDouble(6, newProduct.getPrice());
            pstmt.setLong(7, newProduct.getStock());
            pstmt.setDouble(8, newProduct.getLatitude());
            pstmt.setDouble(9, newProduct.getLongitude());

            // this.disconnect();
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Product newProduct) {
        try {
            // Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            // this.connect();

            String sql = "UPDATE product SET user_id=?, category_id=?, state_product_id=?, product_name=?, product_description=?, product_price=?, product_stock=?, product_latitude=?, product_longitude=? WHERE product_id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newProduct.getOwner().getId());
            pstmt.setLong(2, newProduct.getCategory().getId());
            pstmt.setLong(3, newProduct.getState().getId());
            pstmt.setString(4, newProduct.getName());
            pstmt.setString(5, newProduct.getDescription());
            pstmt.setDouble(6, newProduct.getPrice());
            pstmt.setLong(7, newProduct.getStock());
            pstmt.setDouble(8, newProduct.getLatitude());
            pstmt.setDouble(9, newProduct.getLongitude());
            pstmt.setLong(10, newProduct.getId());

            // this.disconnect();
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Product> findByStatus(StateProduct status) {
        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM product WHERE state_product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, status.getId());
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                Product newProduct = new Product();
                newProduct.setId(result.getLong("product_id"));
                newProduct.setOwner(new UserRepository().findById(result.getLong("user_id")));
                newProduct.setCategory(new CategoryRepository().findById(result.getLong("category_id")));
                newProduct.setState(new StateProductRepository().findById(result.getLong("state_product_id")));
                newProduct.setName(result.getString("product_name"));
                newProduct.setDescription(result.getString("product_description"));
                newProduct.setPrice(result.getDouble("product_price"));
                newProduct.setStock(result.getLong("product_stock"));
                newProduct.setLatitude(result.getDouble("product_latitude"));
                newProduct.setLongitude(result.getDouble("product_longitude"));
                products.add(newProduct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        List<Product> products = new ArrayList<>();

        try {
            String sql = "SELECT * FROM product WHERE product_name like %?% OR product_description like %?%";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                Product newProduct = new Product();
                newProduct.setId(result.getLong("product_id"));
                newProduct.setOwner(new UserRepository().findById(result.getLong("user_id")));
                newProduct.setCategory(new CategoryRepository().findById(result.getLong("category_id")));
                newProduct.setState(new StateProductRepository().findById(result.getLong("state_product_id")));
                newProduct.setName(result.getString("product_name"));
                newProduct.setDescription(result.getString("product_description"));
                newProduct.setPrice(result.getDouble("product_price"));
                newProduct.setStock(result.getLong("product_stock"));
                newProduct.setLatitude(result.getDouble("product_latitude"));
                newProduct.setLongitude(result.getDouble("product_longitude"));
                products.add(newProduct);
            }
            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        try {
            String sql = "SELECT * FROM product WHERE product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                Product newProduct = new Product();
                newProduct.setId(result.getLong("product_id"));
                newProduct.setOwner(new UserRepository().findById(result.getLong("user_id")));
                newProduct.setCategory(new CategoryRepository().findById(result.getLong("category_id")));
                newProduct.setState(new StateProductRepository().findById(result.getLong("state_product_id")));
                newProduct.setName(result.getString("product_name"));
                newProduct.setDescription(result.getString("product_description"));
                newProduct.setPrice(result.getDouble("product_price"));
                newProduct.setStock(result.getLong("product_stock"));
                newProduct.setLatitude(result.getDouble("product_latitude"));
                newProduct.setLongitude(result.getDouble("product_longitude"));
                return newProduct;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
