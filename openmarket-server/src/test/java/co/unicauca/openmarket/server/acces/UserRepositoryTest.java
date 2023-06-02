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
    public void TestFindByEmailAndPasswordSuccess() {
        User userTest = repository.findByEmailAndPassword("usuario10@example.com","1");
        assertEquals(4, userTest.getId()); // se espera que el id del usuario encontrado sea 4
        assertNotNull(userTest); // se espera que el usuario encontrado no sea nulo
    }

    /**
     * @displayName Prueba de error para encontrar un usuario por su email y contraseña
     */
    @Test 
    public void TestFindByEmailAnddPasswordFailed() {
        User userTest = repository.findByEmailAndPassword("","");
        assertEquals(null, userTest);  //se espera que el usuario no se encuentre
    }

    /**
     * @displayName Prueba para encontrar un usuario por su id
     */
    @Test
    public void TestFindByIdSuccess(){
        User userTest = repository.findById(1L);
        assertEquals(1, userTest.getId()); // se espera que el id del usuario encontrado sea 1

    }

    /**
     * @displayName Prueba de error para encontrar un usuario por su id
     */
    @Test
    public void TestFindByIdFailed(){
        User userTest = repository.findById(50L);
        assertEquals(null, userTest); //No se encuentra el usuario
    }


    
}
