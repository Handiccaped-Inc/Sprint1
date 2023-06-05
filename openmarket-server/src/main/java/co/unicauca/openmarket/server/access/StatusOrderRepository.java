package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.StatusOrder;

/**
 * Clase StatusOrderRepository
 */
public class StatusOrderRepository implements IStatusOrderRepository {

    /** Conexion */
    private Connection connection;

    /**
     * Constructor default
     */
    public StatusOrderRepository() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public StatusOrder findById(Long id) {

        try {
            String sql = "SELECT * FROM order_status WHERE order_status_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                StatusOrder status = new StatusOrder();
                status.setId(res.getLong("order_status_id"));
                status.setName(res.getString("order_status_name"));
                return status;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusOrderRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
