package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Delivery;

/**
 * Clase que maneja los pedidos a nivel de la base de datos
 */
public class DeliveryRepository implements IDeliveryRepository {

    protected Connection conn;

    public DeliveryRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean save(Delivery newDelivery) {
        try {

            if (newDelivery == null) {
                return false;
            }

            String sql = "INSERT INTO delivery(orders_id,deliveryman_id,delivery_receiver,delivery_date)"
                    + "VALUES(?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, newDelivery.getOrder().getId());
            pstm.setLong(2, newDelivery.getDeliveryMan().getId());
            pstm.setString(3, newDelivery.getReceiver().getRealName());
            pstm.setDate(4, (Date) newDelivery.getDate());

            return pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Delivery newDelivery) {
        try {

            if (newDelivery == null) {
                return false;
            }

            String sql = "UPDATE delivery set orders_id = ?, deliveryman_id = ?, delivery_id = ?, delivery_reciver = ?, delivery_date = ? WHERE delivery_id = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, newDelivery.getOrder().getId());
            pstm.setLong(2, newDelivery.getDeliveryMan().getId());
            pstm.setString(3, newDelivery.getReceiver().getRealName());
            pstm.setDate(4, (Date) newDelivery.getDate());
            pstm.setLong(5, newDelivery.getId());
            return pstm.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(DeliveryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

}
