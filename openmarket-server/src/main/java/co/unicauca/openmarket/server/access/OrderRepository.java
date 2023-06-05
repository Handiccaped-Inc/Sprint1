package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.StatusOrder;

/**
 * Clase OrderRepository
 */
public class OrderRepository implements IOrderRepository {

    /** Conexion */
    protected Connection connection;

    /**
     * Constructor default
     */
    public OrderRepository() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Order newOrder) {
        try {
            // Validate order
            if (newOrder == null || newOrder.getPrice().equals(null)) {
                return false;
            }

            String sql = "INSERT INTO orders (user_id, product_id, order_status_id, orders_price, orders_qualification, orders_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, newOrder.getCustomer().getId());
            pstmt.setLong(2, newOrder.getProduct().getId());
            pstmt.setLong(3, newOrder.getStatus().getId());
            pstmt.setDouble(4, newOrder.getPrice());
            pstmt.setDouble(5, newOrder.getQualification());
            String date = newOrder.getDate().toString();
            pstmt.setString(6, date);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update(Order newOrder) {
        try {
            // Validate order
            if (newOrder == null || newOrder.getPrice().equals(null)) {
                return false;
            }

            String sql = "UPDATE orders SET user_id = ?, product_id = ?, order_status_id = ?, orders_price = ?, "
                    + "orders_qualification = ?, orders_date = ? WHERE orders_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, newOrder.getCustomer().getId());
            pstmt.setLong(2, newOrder.getProduct().getId());
            pstmt.setLong(3, newOrder.getStatus().getId());
            pstmt.setDouble(4, newOrder.getPrice());
            pstmt.setDouble(5, newOrder.getQualification());
            String date = newOrder.getDate().toString();
            pstmt.setString(6, date);
            pstmt.setLong(7, newOrder.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Order> findByState(StatusOrder status) {
        List<Order> orders = new ArrayList<>();
        try {

            String sql = "SELECT * FROM orders WHERE order_status_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, status.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("orders_id"));
                order.setCustomer(new UserRepository().findById(rs.getLong("user_id")));
                order.setProduct(new ProductRepository().findById(rs.getLong("product_id")));
                order.setStatus(new StatusOrderRepository().findById(rs.getLong("order_status_id")));
                order.setPrice(rs.getDouble("orders_price"));
                order.setQualification(rs.getDouble("orders_qualification"));
                String date = rs.getString("orders_date");
                order.setDate(Date.valueOf(date));

                orders.add(order);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @Override
    public List<Order> findByUser(Long userId) {
        List<Order> orders = new ArrayList<>();
        try {

            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("orders_id"));
                order.setCustomer(new UserRepository().findById(rs.getLong("user_id")));
                order.setProduct(new ProductRepository().findById(rs.getLong("product_id")));
                order.setStatus(new StatusOrderRepository().findById(rs.getLong("order_status_id")));
                order.setPrice(rs.getDouble("orders_price"));
                order.setQualification(rs.getDouble("orders_qualification"));
                String strDate = rs.getString("orders_date");
                order.setDate(Date.valueOf(strDate));
                orders.add(order);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

}
