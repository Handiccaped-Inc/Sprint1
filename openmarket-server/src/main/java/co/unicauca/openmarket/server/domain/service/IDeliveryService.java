package co.unicauca.openmarket.server.domain.service;

import co.unicauca.openmarket.commons.domain.Delivery;

/**
 * Interface que maneja los metodos que deben
 * contener todos los Service de que desean manejar un pedido
 */
public interface IDeliveryService {

    /**
     * Metodo que maneja el guardar un nuevo pedido
     * 
     * @param newDelivery objeto que contiene los datos de un pedido
     * @return boolean dependiendo del exito de la operación
     */
    public String save(Delivery newDelivery);

    /**
     * Metodo que maneja el actualizar un pedido
     * 
     * @param newDelivery objeto que cotiene los datos de un pedido
     * @return boolean dependiendo del exito de la operación
     */

    public String update(Delivery newDelivery);

}
