package co.unicauca.openmarket.server.domain.service;

import co.unicauca.openmarket.commons.domain.User;

public interface IUserService {
    /**
     * Busca un usuario por email y contraseña
     * 
     * @param email    email del usuario
     * @param password contraseña del usuario
     * @return retorna el usuario
     */
    public User findByEmailAndPassword(String email, String password);

    /**
     * Busca el Email y la Contraseña de un usuario
     * 
     * @param userId Id del usuario
     * @return retorna el usuario
     */
    public User findById(Long userId);
}