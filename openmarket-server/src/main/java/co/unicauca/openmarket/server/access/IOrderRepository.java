package co.unicauca.openmarket.server.access;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.StatusOrder;

public interface IOrderRepository {
    /**
     * @brief Metodo para guardar una orden
     * @param NewOrder objeto de esquema de una orden
     * @return boolean deacuerdo a resulado
     */
    public boolean save(Order newOrder);

    /**
     * @brief Metodo para actualizar una orden
     * @param NewOrder objeto de esquema de una orden
     * @return boolean deacuerdo a resulado
     */

    public boolean update(Order newOrder);

    /**
     * @brief Metodo que encuentra la orden por el estado de esta
     * @param status objet que contiene el estaod de una orden
     * @return lista de objetos entcontrados
     */

    public List<Order> findByState(StatusOrder status);

    /**
     * @brief Metodo que encuentra la orden el id del usuario
     * @param UserId id del usuario
     * @return lista de objetos entcontrados
     */
    public List<Order> findByUser(Long UserId);

}
