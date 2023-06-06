package co.unicauca.openmarket.server.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Date;

import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.infra.Hashing;
import co.unicauca.openmarket.commons.domain.Rol;

/**
 * Clase UserRepository
 * 
 * @author Arturo
 */

public class UserRepository implements IUserRepository {

    /**
     * Conección con Mysql
     */
    protected Connection conn;

    /**
     * Constructor default
     */
    public UserRepository() {
        conn = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Implementacion del metodo "findByEmailAndPassword"
     */
    @Override
    public User findByEmailAndPassword(String email, String password) {

        User user = null;
        Rol user_rol = null;
        String hashedPassword = Hashing.getSHA256Hash(password);
        String sql = "SELECT * FROM user WHERE user_email = ? AND user_password = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, hashedPassword);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Long user_Id = res.getLong("user_id");
                Long rol_Id = res.getLong("rol_id");
                user_rol = new RolRepository().findById(rol_Id);
                String birth_DateStr = res.getString("user_birth_date");
                String user_Email = res.getString("user_email");
                String user_Phone = res.getString("user_phone");
                String user_Card = res.getString("user_card");
                String real_Name = res.getString("user_realname");
                String user_name = res.getString("user_username");
                // String user_password = res.getString("user_password");
                String user_Address = res.getString("user_address");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date birth_Date = dateFormat.parse(birth_DateStr);
                user = new User(user_Id, user_rol, birth_Date, user_Email, user_Phone, user_Card, real_Name, user_name,
                        password, user_Address);
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE,
                    "Error al consultar Customer de la base de datos", e);
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null; // No se encontró el usuario
    }

    /**
     * Implementacion del metodo "findById"
     */
    @Override
    public User findById(Long userId) {

        User user = null;
        Rol user_rol = null;
        String sql = "SELECT * FROM user WHERE user_id = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet res = statement.executeQuery();
            if (res.next()) {
                Long user_Id = res.getLong("user_id");
                long rol_Id = res.getLong("rol_id");
                user_rol = new RolRepository().findById(rol_Id);
                String birth_DateStr = res.getString("user_birth_date");
                String user_Email = res.getString("user_email");
                String user_Phone = res.getString("user_phone");
                String user_Card = res.getString("user_card");
                String real_Name = res.getString("user_realname");
                String user_name = res.getString("user_username");
                String user_password = "oculta";
                String user_Address = res.getString("user_address");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date birth_Date = dateFormat.parse(birth_DateStr);
                user = new User(user_Id, user_rol, birth_Date, user_Email, user_Phone, user_Card, real_Name, user_name,
                        user_password, user_Address);
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE,
                    "Error al consultar user de la base de datos", e);
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null; // No se encontró el usuario
    }

}
