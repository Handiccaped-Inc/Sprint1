package co.unicauca.openmarket.server.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import co.unicauca.openmarket.server.access.IUserRepository;
import co.unicauca.openmarket.server.access.UserRepository;
import co.unicauca.openmarket.commons.domain.User;

/**
 * Pruebas unitarias para la clase UserRepository
 */
public class UserRepositoryTest {

    IUserRepository repository = new UserRepository();

    /**
     *@displayName  Prueba para encontrar un usuario por su email y contraseña
     */
    @Test
    public void TestFindByEmailAndPasswordSucces() {
        User userTest = repository.findByEmailAndPassword("usuario10@example.com","1");
        assertEquals(4, userTest.getId());
        assertNotNull(userTest);
    }

    /**
     * @displayName Prueba de error para encontrar un usuario por su email y contraseña
     */
    @Test 
    public void TestFindByEmailAnddPasswordFailed() {
        User userTest = repository.findByEmailAndPassword("","");
        assertEquals(null, userTest); //No se encuentra el usuario
    }

    /**
     * @displayName Prueba para encontrar un usuario por su id
     */
    @Test
    public void TestFindByIdSucces(){
        User userTest = repository.findById(1L);
        assertEquals(1, userTest.getId());

    }

    /*
     * @displayName Prueba de error para encontrar un usuario por su id
     */
    @Test
    public void TestFindByIdFailed(){
        User userTest = repository.findById(50L);
        assertEquals(null, userTest); //No se encuentra el usuario
    }


    
}
