package co.unicauca.openmarket.server.domain.service;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.StatusOrder;

/**
 * Interfaz que maneja los metodos que deben
 * contener todos los Service de que desean manejar una orden
 */
public interface IOrderService {
    /**
     *  Metodo para guardar una orden
     * @param newOrder objeto de esquema de una orden
     * @return boolean deacuerdo a resulado
     */
    public String save(Order newOrder);

    /**
     *  Metodo para actualizar una orden
     * @param newOrder objeto de esquema de una orden
     * @return boolean deacuerdo a resulado
     */
    public String update(Order newOrder);

    /**
     *  Metodo que encuentra la orden por el estado de esta
     * @param status objet que contiene el estaod de una orden
     * @return lista de objetos entcontrados
     */
    public List<Order> findByState(StatusOrder status);

    /**
     *  Metodo que encuentra la orden el id del usuario
     * @param UserId id del usuario
     * @return lista de objetos entcontrados
     */
    public List<Order> findByUser(Long UserId);
}
