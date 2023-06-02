package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.unicauca.openmarket.commons.domain.Category;

/**
 * Clase CategoryRepository
 */
public class CategoryRepository implements ICategoryRepository {

    /** Conexion */
    protected Connection conn;

    /**
     * Constructor por defecto
     */
    public CategoryRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public Category findById(long id) {
        try {
            String sql = "SELECT * FROM category WHERE category_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                Category category = new Category();
                category.setId(res.getLong("category_id"));
                category.setName(res.getString("category_name"));
                return category;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
