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

/*
 * Pruebas unitarias para la clase AccountService
 */
public class AccountServiceTest {

    private IAccountRepository repo;
    private AccountService service;


    AccountServiceTest(){
        repo = new mockAccountRepository();
        service = new AccountService(repo);
    }

    /* 
     * Prueba para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void TestFindAccountByCardSucces(){
        Account account = service.findByCard("12345");
        assertNotNull(account);
        assertEquals(1L,account.getId());
    }


    /*
     * Prueba de erorro para encontrar una cuenta por su numero de tarjeta
     */
    @Test
    public void TestFindAccountByCardFail(){
        Account account = service.findByCard("1233");
        assertNull(account);
    }

    /*
     * Prueba para actualizar una cuenta p
     */

    @Test
    public void UpdateAcountSucces(){
        Account account = new Account(1L, "12345", 100000L);
        boolean result = service.update(account);
        assertTrue(result);
    }

    /*
     * prueba de error para actualizar una cuenta
     */
    @Test
    public void UpdateAcountFail(){
        Account account = null;
        boolean result = service.update(account);
        assertFalse(result);
    }


    /*
     * prueba de error para actualizar una cuenta
     */
    @Test
    public void UpdateAcountFail2(){
        Account account = new Account(1L, "", 0000L);
        boolean result = service.update(account);
        assertFalse(result);
    }

    
    /*
     * prueba de error para actualizar una cuenta
     */
    @Test
    public void UpdateAcountFail3(){
        Account account = new Account(5L, "", 0000L);
        boolean result = service.update(account);
        assertFalse(result);
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
