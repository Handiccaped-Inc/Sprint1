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
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;

public class OrderRepository implements IOrderRepository {

    @Override
    public boolean save(Order newOrder) {
        try {
            // Validate order
            if (newOrder == null || newOrder.getPrice().equals(null)) {
                return false;
            }

            DatabaseConnection dbConnection = DatabaseConnection.getInstance();

            Connection connection = dbConnection.getConnection();

            String sql = "INSERT INTO orders (user_id, product_id, order_status_id, orders_price, orders_qualification, orders_date) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, newOrder.getCustomer().getId());
            pstmt.setLong(2, newOrder.getProduct().getId());
            pstmt.setLong(3, newOrder.getStatus().getId());
            pstmt.setDouble(4, newOrder.getPrice());
            pstmt.setDouble(5, newOrder.getQualification());
            pstmt.setDate(6, (Date) newOrder.getDate());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
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

            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String sql = "UPDATE orders SET user_id = ?, product_id = ?, order_status_id = ?, orders_price = ?, "
                    + "orders_qualification = ?, orders_date = ? WHERE order_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, newOrder.getCustomer().getId());
            pstmt.setLong(2, newOrder.getProduct().getId());
            pstmt.setLong(3, newOrder.getStatus().getId());
            pstmt.setDouble(4, newOrder.getPrice());
            pstmt.setDouble(5, newOrder.getQualification());
            pstmt.setDate(6, (Date) newOrder.getDate());
            pstmt.setLong(7, newOrder.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Order> findByState(StatusOrder status) {
        List<Order> orders = new ArrayList<>();
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String sql = "SELECT * FROM orders WHERE order_status_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, status.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("order_id");
                Double price = rs.getDouble("orders_price");
                Double qualification = rs.getDouble("orders_qualification");
                Date date = rs.getDate("orders_date");

                // Set customer, product, and status for the order
                User customer = new User();
                customer.setId(rs.getInt("user_id"));
                // Set other customer properties

                Product product = new Product();
                product.setId(rs.getLong("product_id"));
                // Set other product properties

                StatusOrder orderStatus = new StatusOrder();
                orderStatus.setId(rs.getLong("order_status_id"));
                // Set other status properties

                Order order = new Order(id, customer, product, orderStatus, price, date, qualification);
                orders.add(order);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    @Override
    public List<Order> findByUser(Integer userId) {
        List<Order> orders = new ArrayList<>();
        try {
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong("order_id"));
                order.setPrice(rs.getDouble("orders_price"));
                order.setQualification(rs.getDouble("orders_qualification"));
                order.setDate(rs.getDate("orders_date"));

                // Set user, product, and status for the order
                User user = new User();
                user.setId(rs.getInt("user_id"));
                // Set other user properties
                order.setCustomer(user);

                Product product = new Product();
                product.setId(rs.getLong("product_id"));
                // Set other product properties
                order.setProduct(product);

                StatusOrder orderStatus = new StatusOrder();
                orderStatus.setId(rs.getLong("order_status_id"));
                // Set other status properties
                order.setStatus(orderStatus);

                orders.add(order);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

}
