package co.unicauca.payment.acces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import co.unicauca.payment.access.AccountRepository;
import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.domain.Account;

/**
 * Pruebas unitarias para la clase AccountRepository
 */
public class AccountRepositoryTest {

    IAccountRepository repository = new AccountRepository();

    /**
     * Prueba para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void testFindAccountbyCard() {
        
        Account result = repository.findByCard("1234567890");
        assertNotNull(result); // se espera que el usuario encontrado no sea nulo
        assertEquals(1, result.getId()); // se espera que el id del usuario encontrado sea 1
    }

    /**
     * Prueba de erorr para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void testfindAccountByCardFailed() {
        
        Account result = repository.findByCard("12469");
        assertNull(result); // se espera que el usuario encontrado no sea nulo
    }

    /**
     * Prueba para actualizar una cuenta 
     */
    @Test
    public void testUpdateAccount() {
        Account account = new Account(1L, "123456789", 200L);
        Boolean result = repository.update(account);
        assertTrue(result);  //se espera que el usuario se actualice
    }

    /**
     * Prueba para actualizar una cuenta fallida
     */
    @Test
    public void testUpdateAccountFailed() {
        Account account = new Account(4L, "123456789", 200L);
        Boolean result = repository.update(account);
        assertFalse(result); //se espera que el usuario no se actualice
    }

    /**
     * Prueba para actualizar una cuenta fallida
     */
    @Test
    public void testUpdateAccountFailed2() {
        Account account = new Account(4L, "", 200L);
        Boolean result = repository.update(account);
        assertFalse(result); //se espera que el usuario no se actualice
    }
    
    
}