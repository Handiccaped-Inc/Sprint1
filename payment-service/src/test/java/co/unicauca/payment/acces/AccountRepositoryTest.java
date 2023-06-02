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

/*
 * Pruebas unitarias para la clase AccountRepository
 */
public class AccountRepositoryTest {

    IAccountRepository repository = new AccountRepository();

    /*
     * Prueba para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void TestFindAccountbyCard() {
        
        Account result = repository.findByCard("1234567890");
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    /*
     * Prueba de erorr para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void TestfindAccountByCardFailed() {
        
        Account result = repository.findByCard("12469");
        assertNull(result);
    }

    /*
     * Prueba para actualizar una cuenta 
     */
    @Test
    public void TestUpdateAccount() {
        Account account = new Account(1L, "123456789", 200L);
        Boolean result = repository.update(account);
        assertTrue(result);
    }

    /*
     * Prueba para actualizar una cuenta fallida
     */
    @Test
    public void TestUpdateAccountFailed() {
        Account account = new Account(4L, "123456789", 200L);
        Boolean result = repository.update(account);
        assertFalse(result);
    }

    /*
     * Prueba para actualizar una cuenta fallida
     */
    @Test
    public void TestUpdateAccountFailed2() {
        Account account = new Account(4L, "", 200L);
        Boolean result = repository.update(account);
        assertFalse(result);
    }
    
    
}