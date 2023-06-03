package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Rol;

/**
 * Clase RolRepository
 * @author Arturo
 */
public class RolRepository implements IRolRepository {

    /** Conexion */
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
    public Rol findById(long id) {
        try {
            String sql = "SELECT * FROM rol WHERE rol_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                long rol_id = res.getLong("rol_id");
                String rol_name = res.getString("rol_name");
                Rol rol = new Rol(rol_id, rol_name);
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
