package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.Category;
import co.unicauca.openmarket.server.access.CategoryRepository;
import co.unicauca.openmarket.server.access.ICategoryRepository;

/**
 * Pruebas unitarias de la clase CategoryRepository
 */
public class CategoryRepositoryTest {
    
    ICategoryRepository repository = new CategoryRepository();

    /**
     * Prueba para encontrar una categoria por su id
     */
    @Test
    public void testFindByIdSuccess(){
        Category categoryTest = repository.findById(1L);
        assertEquals(1, categoryTest.getId()); // se espera que el id de la categoria encontrada sea 1
        assertEquals(categoryTest.getName(), "Categoría 1"); // se espera que el nombre de la categoria encontrada sea Categoría 1
    }

    /**
     * Prueba para encontrar una categoria por su id fallada
     */

    @Test
    public void testFindByIdFailed(){
        Category categoryTest = repository.findById(50L);
        assertNull(categoryTest); //No se encuentra la categoria
    }

}
