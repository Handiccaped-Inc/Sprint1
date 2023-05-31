package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.StatusOrder;

public interface IStatusOrderRepository {
    /**
     * @brief Metodo que encuentra un estado de orden por su id
     * @param id identificador del estado de orden
     * @return objeto entcontrado
     */
    public StatusOrder findById(Long id);
}
