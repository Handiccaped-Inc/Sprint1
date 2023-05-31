package co.unicauca.openmarket;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.server.access.DatabaseConnection;
import co.unicauca.openmarket.server.access.IDeliveryRespository;

/**
 * Clase que maneja los pedidos a nivel de la base de datos
 */
public class DeliveryRepository implements IDeliveryRespository{

    DatabaseConnection myConnection = DatabaseConnection.getInstance();

      /**
     * Metodo que maneja el guardar un nuevo pedido
     * @param newDelivery objeto que contiene los datos de un pedido
     * @return boolean dependiendo del exito de la operación 
     */
    @Override
    public boolean save(Delivery newDelivery) {
        try {

            if(newDelivery == null ){
                return false;
            }

            String sql = "INSERT INTO delivery(orders_id,deliveryman_id,delivery_receiver,delivery_date)"
                                                +"VALUES(?,?,?,?)";
            PreparedStatement pstm = myConnection.getConnection().prepareStatement(sql);
            pstm.setLong(1,newDelivery.getOrder().getId());
            pstm.setLong(2,newDelivery.getDeliveryMan().getId());
            pstm.setString(3,Integer.toString(newDelivery.getReceiver().getId()));
            pstm.setDate(4,(Date) newDelivery.getDate());
            pstm.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Metodo que maneja el actualizar un pedido
     * @param newDelivery objeto que cotiene los datos de un pedido
     * @return boolean dependiendo del exito de la operación
     */
    @Override
    public boolean update(Delivery newDelivery) {
        try {

            if(newDelivery == null ){
                return false;
            }

            String sql = "UPDATE delivery set orders_id = ?, deliveryman_id = ?, delivery_id = ?, delivery_reciver = ?, delivery_date = ? WHERE delivery_id = ?";

            PreparedStatement pstm = myConnection.getConnection().prepareStatement(sql);
            pstm.setLong(1,newDelivery.getOrder().getId());
            pstm.setLong(2,newDelivery.getDeliveryMan().getId());
            pstm.setString(3,Integer.toString(newDelivery.getReceiver().getId()));
            pstm.setDate(4,(Date) newDelivery.getDate());
            pstm.setLong(5, newDelivery.getId());
            pstm.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DeliveryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
}
