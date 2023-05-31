package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.StateProduct;

public interface IStateProductRepository {
    /**
     * @brief Metodo para guardar una categoria
     * @param id id de la categoria
     * @return objeto de esquema de una categoria
     */
    public StateProduct findById(long id);
}
