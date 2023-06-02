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
 * Clase de testeo para AccountRepository
 */
public class AccountRepositoryTest {

    IAccountRepository repository = new AccountRepository();

    /**
     * Test para encontrar la cuenta
     */
    @Test
    public void TestFindAccountbyCard() {
        
        Account result = repository.findByCard("1234567890");
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testfindAccountByCardFaild() {
        
        Account result = repository.findByCard("12469");
        assertNull(result);
    }

    @Test
    public void testUpdateAccount() {
        Account account = new Account(1L, "123456789", 200L);
        Boolean result = repository.update(account);
        assertTrue(result);
    }

    @Test
    public void testUpdateAccountFaild() {
        Account account = new Account(4L, "123456789", 200L);
        Boolean result = repository.update(account);
        assertFalse(result);
    }

    @Test
    public void testUpdateAccountFaild2() {
        Account account = new Account(4L, "", 200L);
        Boolean result = repository.update(account);
        assertFalse(result);
    }
    
    
}