package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.StatusOrder;

/**
 * Interfaz de StatusOrderRepository
 */
public interface IStatusOrderRepository {
    /**
     * Metodo que encuentra un estado de orden por su id
     * @param id identificador del estado de orden
     * @return objeto entcontrado
     */
    public StatusOrder findById(Long id);
}
