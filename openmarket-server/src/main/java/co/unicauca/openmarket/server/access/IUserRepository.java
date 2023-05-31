package co.unicauca.openmarket.server.access;

/**
 * Interface del respositorio de usuarios
 * @author Arturo Restrepo Ruiz
 */

import co.unicauca.openmarket.commons.domain.User;

public interface IUserRepository {
    
    /**
     * Busca un usuario por email y contraseña
     * @param email email del usuario
     * @param password contraseña del usuario
     * @return retorna el usuario
     */
    public User findByEmailAndPassword(String email, String password);
    
    /**
     * Busca el Email y la Contraseña de un usuario
     * @param userId Id del usuario
     * @return retorna el usuario
     */
    public User findById(int userId);
}
