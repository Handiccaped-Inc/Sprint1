package co.unicauca.openmarket.client.infra;

import co.unicauca.openmarket.commons.domain.User;

/**
 * Clase que maneja la sesion del usuario
 */
public class SessionManager {
    private User user;
    private static SessionManager instance;

    /**
     * Constructor privado para implementar el patrón Singleton
     */
    private SessionManager() {
    }

    /**
     * Implementación del patrón Singleton
     * 
     * @return instancia
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }

    /**
     * Obtiene el usuario
     * 
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * Asigna el usuario
     * 
     * @param user Usuario
     */
    public void setUser(User user) {
        this.user = user;
    }
}
