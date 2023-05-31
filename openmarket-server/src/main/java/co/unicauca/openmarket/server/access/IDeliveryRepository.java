package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Delivery;

public interface IDeliveryRespository {

    /**
     * Metodo que maneja el guardar un nuevo pedido
     * @param newDelivery objeto que contiene los datos de un pedido
     * @return boolean dependiendo del exito de la operación 
     */
    public boolean save(Delivery newDelivery);

    /**
     * Metodo que maneja el actualizar un pedido
     * @param newDelivery objeto que cotiene los datos de un pedido
     * @return boolean dependiendo del exito de la operación
     */

    public boolean update(Delivery newDelivery);
}
