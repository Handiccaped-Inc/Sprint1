package co.unicauca.openmarket.server.acces;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.server.access.IStateProductRepository;
import co.unicauca.openmarket.server.access.StateProductRepository;

/**
 * Pruebas unitarias de la clase StateProductRepositoryTest
 */
public class StateProductRepositoryTest {
    IStateProductRepository repository = new StateProductRepository();

    /**
     * Prueba para encontrar un estado del producto por su id
     */
    @Test
    public void testFindByIdSuccess(){
        StateProduct stateProductTest = repository.findById(1L);
        assertEquals(1, stateProductTest.getId()); // se espera que el id del estado del producto encontrado sea 1
        assertEquals("disponible", stateProductTest.getName()); // se espera que el nombre del estado del producto encontrado sea disponible
    }

    /**
     * Prueba para encontrar un estado del producto por su id fallada
     */
    @Test
    public void testFindByIdFailed(){
        StateProduct stateProductTest = repository.findById(50L);
        assertNull(stateProductTest); //No se encuentra el estado del producto
    }
}
