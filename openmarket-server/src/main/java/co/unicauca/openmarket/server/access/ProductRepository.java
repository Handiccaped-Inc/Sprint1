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
import co.unicauca.openmarket.commons.domain.User;

/**
 * Clase ProductRepository
 */
public class ProductRepository implements IProductRepository {

    /** Conexion */
    protected Connection conn;

    /**
     * Constructor default
     */
    public ProductRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Product newProduct) {
        conn = DatabaseConnection.getInstance().getConnection();

        try {
            // Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            // this.connect();

            String sql = "INSERT INTO product (user_id, state_product_id, product_name, product_description, product_price, product_stock, product_latitude, product_longitude) VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newProduct.getOwner().getId());
            pstmt.setLong(2, newProduct.getState().getId());
            pstmt.setString(3, newProduct.getName());
            pstmt.setString(4, newProduct.getDescription());
            pstmt.setDouble(5, newProduct.getPrice());
            pstmt.setLong(6, newProduct.getStock());
            pstmt.setDouble(7, newProduct.getLatitude());
            pstmt.setDouble(8, newProduct.getLongitude());

            // this.disconnect();
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Product newProduct) {
        conn = DatabaseConnection.getInstance().getConnection();

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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Product> findByStatus(StateProduct status) {
        conn = DatabaseConnection.getInstance().getConnection();

        List<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT * FROM product WHERE product.state_product_id in (select state_product_id from state_product where state_product_name = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status.getName());
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        conn = DatabaseConnection.getInstance().getConnection();

        List<Product> products = new ArrayList<>();

        try {
            String sql = "SELECT * FROM product WHERE product.state_product_id in (select state_product_id from state_product where state_product_name = ?) AND (product_name like ? OR product_description like ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "disponible");
            pstmt.setString(2, "%" + name + "%");
            pstmt.setString(3, "%" + description + "%");
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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        conn = DatabaseConnection.getInstance().getConnection();

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
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean delete(Product newProduct) {
        conn = DatabaseConnection.getInstance().getConnection();

        try {
            // Validate product
            if (newProduct == null || newProduct.getName().isEmpty()) {
                return false;
            }
            // this.connect();

            String sql = "DELETE FROM product WHERE product_id=?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, newProduct.getId());

            // this.disconnect();
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Metodo de encontar productos por su dueño
     * 
     * @param User usurio que es dueño de los productos
     * @return lista de los elementos encontrados
     */
    @Override
    public List<Product> findByOwner(User user) {
        List<Product> productsFindbyOwner = new ArrayList<>();
        conn = DatabaseConnection.getInstance().getConnection();

        try {
            if (user.getId() == 0 || user == null) {
                return productsFindbyOwner;
            }
            String sql = "SELECT * FROM product WHERE user_id = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, user.getId());
            ResultSet result = pstm.executeQuery();
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
                productsFindbyOwner.add(newProduct);
            }

            return productsFindbyOwner;

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return productsFindbyOwner;
    }

}
