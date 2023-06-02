package co.unicauca.openmarket.server.domain.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import co.unicauca.openmarket.commons.domain.Rol;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IUserRepository;

/**
/**
 * Pruebas unitarias del servicio de usuarios
 *
 */
public class UserServiceTest {

    private UserService service;
    private IUserRepository repository;

    public UserServiceTest() {
        repository = new MockUserServiceRepository();
        service = new UserService(repository);
    }

    /**
     * Prueba para encontrar un usuario por su email and password
     *     
     */

    @Test
    public void testFindbyemailandpasswordSucces() {
        User user = service.findByEmailAndPassword("example@example.com", "password123");
        assertNotNull(user); // se espera que el usuario encontrado no sea nulo
        assertEquals(1l, user.getId()); // se espera que el id del usuario encontrado sea 1
    }

    /**
     * Prueba de fallo para encontrar un usuario por su email and password
     */

    @Test
    public void testFindbyemailandpasswordFailedEmail() {
        User user = service.findByEmailAndPassword("", "password123");
        assertNull(user); // se espera que el usuario encontrado sea nulo
    }

    /**
     * Prueba de fallo para encontrar un usuario por su email and password
     */
    @Test
    public void testFindbyemailandpasswordFailedPassword() {
        User user = service.findByEmailAndPassword("example2@example2.com", "");
        assertNull(user); // se espera que el usuario encontrado sea nulo
    }

    /**
     * Mock usado para realizar las pruebas
     */
    private class MockUserServiceRepository implements IUserRepository {
        List<User> usaurios;

        public MockUserServiceRepository() {
            this.usaurios = new ArrayList<>();
            User user = new User(1L, new Rol(1L, "Vendedor"), new Date(0), "example@example.com", "1234567890",
                    "1234 5678 9012 3456",
                    "Jojan Esteban", "jojanE", "password123", "123 Street, City");
            User user2 = new User(2L, new Rol(2l, "Compradro"), new Date(0), "example2@example2.com", "1234567890",
                    "1234 5678 9012 3456",
                    "pablo ruiz", "pabloR", "password456", "134 Street, City");
            usaurios.add(user2);
            usaurios.add(user);
        }

        public User findByEmailAndPassword(String email, String password) {
            for (User user : usaurios) {
                if (email == user.getEmail() && password == user.getPassword()) {
                    return user;
                }
            }

            return null;

        }

        public User findById(Long userId) {
            for (User user : usaurios) {
                if (userId == user.getId()) {
                    return user;
                }
            }
            return null;
        }

    }

}
