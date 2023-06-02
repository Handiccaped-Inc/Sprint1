package co.unicauca.payment.domain.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.access.ITransactionRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.Transaction;
import co.unicauca.payment.domain.service.AccountService;
import co.unicauca.payment.domain.service.TransactionService;

/**
 * Pruebas unitarias para la clase TransactionService
 */
public class TransactionServiceTest {

    private ITransactionRepository repo;
    private TransactionService service;

    public TransactionServiceTest(){
        repo = new mockTransactionRepository();
        service = new TransactionService(repo);
    }

    /**
     * Prueba para guardar una transaccion
     */
    @Test
    public void saveTransactionSucces(){
        Transaction transaction = new Transaction(1L, new Date(0), 20000L,new Account(1L, "12345", 100000L), new Account(2L, "12345", 100000L));
        boolean result = service.save(transaction);
        assertTrue(result); // se espera que la transaccion se guarde
    }

    /**
     * prueba para guardar una transaccion fallida
     */
    @Test
    public void saveTransactionFailed(){
        Transaction transaction = null;
        boolean result = service.save(transaction);
        assertFalse(result); // se espera que la transaccion no se guarde
    }

     /**
     * prueba para guardar una transaccion fallida
     */
    @Test
    public void saveTransactionFailed2(){
        Transaction transaction = new Transaction(1L, new Date(0), 20000L,null, new Account(2L, "12345", 100000L));
        boolean result = service.save(transaction);
        assertFalse(result); // se espera que la transaccion no se guarde
    }

     /**
     * prueba para guardar una transaccion fallida
     */
    @Test
    public void saveTransactionFailed3(){
        Transaction transaction = new Transaction(1L, new Date(0), 0L,new Account(1L, "12345", 100000L), new Account(2L, "12345", 100000L));
        boolean result = service.save(transaction);
        assertFalse(result);
    }

     /**
     * prueba para guardar una transaccion fallida
     */
    @Test
    public void saveTransactionFailed4(){
        Transaction transaction = new Transaction(1L, null, 20000L,new Account(1L, "12345", 100000L), new Account(2L, "12345", 100000L));
        boolean result = service.save(transaction);
        assertFalse(result); // se espera que la transaccion no se guarde
    }

    private class mockTransactionRepository implements ITransactionRepository{

        private List<Transaction> transactions;

        public mockTransactionRepository(){
            transactions = new ArrayList<>();
        }

        @Override
        public boolean save(Transaction newTransaction) {
            return transactions.add(newTransaction);
        }
        
    }
    
}
