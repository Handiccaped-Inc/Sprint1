package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Rol;

/**
 *
 * @author Arturo
 */
public class RolRepository implements IRolRepository{
    protected Connection conn;

    /**
     * Constructor por defecto
     */
    public RolRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }
    
    /**
    * Implementacion del metodo "findById"
    */
    @Override
    public Rol findById(long id){
        try {
            String sql = "SELECT * FROM rol WHERE rol_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                Rol rol = null;
                long rol_id = res.getLong("category_id");
                String rol_name = res.getString("category_name");
                return rol;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RolRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
