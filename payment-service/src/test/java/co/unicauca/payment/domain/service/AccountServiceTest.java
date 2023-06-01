package co.unicauca.payment;

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

    @Test
    public void testfindAccountByCardSucces(){
        Account account = service.findByCard("12345");
        assertNotNull(account);
        assertEquals(1L,account.getId());
    }

    @Test
    public void testfindAccountByCardFaild(){
        Account account = service.findByCard("1233");
        assertNull(account);
    }

    @Test
    public void updateAcountSucces(){
        Account account = new Account(1L, "12345", 100000L);
        boolean result = service.update(account);
        assertTrue(result);
    }

    @Test
    public void updateAcountFaild(){
        Account account = null;
        boolean result = service.update(account);
        assertFalse(result);
    }

    @Test
    public void updateAcountFaild2(){
        Account account = new Account(1L, "", 0000L);
        boolean result = service.update(account);
        assertFalse(result);
    }

    @Test
    public void updateAcountFaild3(){
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
