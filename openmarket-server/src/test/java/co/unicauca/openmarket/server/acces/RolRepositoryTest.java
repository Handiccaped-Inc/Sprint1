package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.server.access.IRolRepository;
import co.unicauca.openmarket.server.access.RolRepository;


/**
 * Pruebas unitarias de la clase RolRepositoryTest
 */
public class RolRepositoryTest {
    IRolRepository repo = new RolRepository();

    /**
     * Prueba para encontrar un rol por su id
     */
    @Test
    public void testFindByIdSuccess() {
        Rol rolTest = repo.findById(1);
        assertEquals(rolTest.getName(), "vendedor"); // Se espera que se retorne el rol con nombre vendedor 
    }

    /**
     * Prueba para encontrar un rol por su id fallada
     */
    @Test
    public void testFindByIdFailed() {
        Rol rolTest = repo.findById(100);
        assertNull(rolTest); // Se espera que no se encuentre el rol con id 100
    }

    
}
