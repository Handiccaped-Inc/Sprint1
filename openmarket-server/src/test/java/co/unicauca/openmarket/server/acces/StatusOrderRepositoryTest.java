package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.server.access.IStatusOrderRepository;
import co.unicauca.openmarket.server.access.StatusOrderRepository;

/**
 * pruebas unitarias de la clase StatusOrderRepository
 */
public class StatusOrderRepositoryTest {

    IStatusOrderRepository repository = new StatusOrderRepository();

    /**
     * Prueba para encontrar un estado del producto por su id
     */
    @Test
    public void testFindByIdSuccess(){
        StatusOrder statusTest = repository.findById(1L);
        assertEquals(1, statusTest.getId()); // se espera que el id del estado del producto encontrado sea 1
        assertEquals("entregado", statusTest.getName()); // se espera que el nombre del estado del producto encontrado sea entregado
    }

    /**
     * Prueba para encontrar un estado del producto por su id fallada
     */
    @Test
    public void testFindByIdFailed(){
        StatusOrder statusTest = repository.findById(50L);
        assertNull(statusTest); //No se encuentra el estado del producto
    }
    
}
