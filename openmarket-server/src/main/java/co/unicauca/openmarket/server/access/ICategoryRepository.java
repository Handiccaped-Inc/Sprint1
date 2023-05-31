package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Category;

public interface ICategoryRepository {
    /**
     * @brief Metodo para guardar una categoria
     * @param id
     * @return
     */
    public Category findById(long id);

}
