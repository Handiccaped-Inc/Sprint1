package co.unicauca.payment.acces;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import co.unicauca.payment.access.ITransactionRepository;
import co.unicauca.payment.access.TransactionRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.Transaction;

public class TransactionRepositoryTest {

    ITransactionRepository repository = new TransactionRepository();

    @Test
    public void saveTransaction() {
        Account accountSender = new Account(1L, "1234567890", 1000L);
        Account accountReceiver = new Account(2L, "9876543210", 1000L);

        Transaction transaccion = new Transaction(5L, new Date(0), 120L, accountSender, accountReceiver);
        Boolean response = repository.save(transaccion);
        assertTrue(response);
        //TODO implementar
    }
    
}
