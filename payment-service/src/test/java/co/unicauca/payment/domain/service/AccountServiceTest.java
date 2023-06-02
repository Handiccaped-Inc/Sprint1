package co.unicauca.payment.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.service.AccountService;

/**
 * Pruebas unitarias para la clase AccountService
 */
public class AccountServiceTest {

    private IAccountRepository repo;
    private AccountService service;


    AccountServiceTest(){
        repo = new mockAccountRepository();
        service = new AccountService(repo);
    }

    /**
     * Prueba para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void testFindAccountByCardSucces(){
        Account account = service.findByCard("12345");
        assertNotNull(account); // se espera que el usuario encontrado no sea nulo
        assertEquals(1L,account.getId()); // se espera que el id del usuario encontrado sea 1
    }


    /**
     * Prueba de erorro para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void testFindAccountByCardFailed(){
        Account account = service.findByCard("1233");
        assertNull(account); // se espera que el usuario encontrado sea nulo
    }

    /**
     * Prueba para actualizar una cuenta 
     */

    @Test
    public void updateAcountSucces(){
        Account account = new Account(1L, "12345", 100000L);
        boolean result = service.update(account);
        assertTrue(result); // se espera que la cuenta se actualice
    }

    /**
     * prueba de error para actualizar una cuenta
     */
    @Test
    public void UpdateAcountFailed(){
        Account account = null;
        boolean result = service.update(account);
        assertFalse(result); // se espera que la cuenta no se actualice
    }


    /**
     * prueba de error para actualizar una cuenta
     */
    @Test
    public void updateAcountFailed2(){
        Account account = new Account(1L, "", 0000L);
        boolean result = service.update(account);
        assertFalse(result); // se espera que la cuenta no se actualice
    }

    
    /**
     * prueba de error para actualizar una cuenta
     */
    @Test
    public void updateAcountFailed3(){
        Account account = new Account(5L, "", 0000L);
        boolean result = service.update(account);
        assertFalse(result); // se espera que la cuenta no se actualice
    }


    
    private class mockAccountRepository implements IAccountRepository{
        List<Account> accounts;
        public mockAccountRepository(){
            accounts = new ArrayList<>();
            Account account1 = new Account(1L, "12345", 100000L);
            Account account2 = new Account(2L, "6789", 200000L);
            Account account3 = new Account(3L, "101112", 300000L);
            accounts.add(account1);
            accounts.add(account2);
            accounts.add(account3);
        }
            
           

            @Override
            public Account findByCard(String cardNumber) {
                for (Account account : accounts) {
                    if(account.getCard().equals(cardNumber)){
                        return account;
                    }
                }
                return null;
            }



            @Override
            public boolean update(Account account) {
                for (Account cuenta : accounts) {
                    if(cuenta.getId().equals(account.getId())){
                        cuenta = account;
                        return true;
                    }
                }

                return false;
            }
            
    } 
}
