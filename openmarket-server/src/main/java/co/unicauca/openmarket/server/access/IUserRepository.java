package co.unicauca.openmarket.server.access;


import co.unicauca.openmarket.commons.domain.User;

/**
 * Interface del respositorio de usuarios
 * @author Arturo Restrepo Ruiz
 */
public interface IUserRepository {

    /**
     *  Busca un usuario por email y clave
     * @param email    email del usuario
     * @param password clave del usuario
     * @return retorna el usuario
     */
    public User findByEmailAndPassword(String email, String password);

    /**
     *  Busca el Email y la clave de un usuario
     * @param userId Id del usuario
     * @return retorna el usuario
     */
    public User findById(Long userId);
}
