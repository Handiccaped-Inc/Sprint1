package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.StateProduct;

/**
 * Clase StateProductRepository
 */
public class StateProductRepository implements IStateProductRepository {

    /** Conexion */
    protected Connection conn;

    /**
     * Constructor por defecto
     */
    public StateProductRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public StateProduct findById(long id) {
        try {
            String sql = "SELECT * FROM state_product WHERE state_product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                StateProduct stateProduct = new StateProduct();
                stateProduct.setId(res.getLong("state_product_id"));
                stateProduct.setName(res.getString("state_product_name"));
                return stateProduct;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
