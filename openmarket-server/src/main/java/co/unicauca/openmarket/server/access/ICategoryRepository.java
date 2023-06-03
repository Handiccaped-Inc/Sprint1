package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Category;

/**
 * Interfaz de CategoryRepository
 */
public interface ICategoryRepository {
    /**
     * Metodo para guardar una categoria
     * @param id identificador para buscar
     * @return Categoria encontrada
     */
    public Category findById(long id);

}
