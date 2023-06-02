package co.unicauca.openmarket.server.domain.service;

import co.unicauca.openmarket.commons.domain.User;

/**
 * Interfaz que maneja los metodos que deben
 * contener todos los Service de que desean manejar un usuario
 */
public interface IUserService {
    /**
     * Busca un usuario por email y clave
     * 
     * @param email    email del usuario
     * @param password clave del usuario
     * @return retorna el usuario
     */
    public User findByEmailAndPassword(String email, String password);

    /**
     * Busca el Email y la clave de un usuario
     * 
     * @param userId Id del usuario
     * @return retorna el usuario
     */
    public User findById(Long userId);
}