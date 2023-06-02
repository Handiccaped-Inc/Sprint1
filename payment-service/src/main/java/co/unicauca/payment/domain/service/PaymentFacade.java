package co.unicauca.payment.domain.service;

import java.util.Calendar;

import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.access.ITransactionRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.Transaction;

/**
 * Clae PaymentFacade
 */
public class PaymentFacade {
    AccountService accountService;
    TransactionService transactionService;

    /**
     * Constructor parametrizado
     * 
     * @param accountRepository     El repositorio a ser usado
     * @param transactionRepository El repositorio a ser usado
     */
    public PaymentFacade(IAccountRepository accountRepository, ITransactionRepository transactionRepository) {
        accountService = new AccountService(accountRepository);
        transactionService = new TransactionService(transactionRepository);
    }

    /**
     * Procesa un pago
     * 
     * @param sender   La cuenta que envia el dinero
     * @param receiver La cuenta que recibe el dinero
     * @param amount   El monto a ser enviado
     * @return True si el pago fue exitoso, false de lo contrario
     */
    public synchronized boolean processPayment(Account sender, Account receiver, Long amount) {
        // Verificar que los datos sean validos
        if (sender.getCard().isEmpty() || receiver.getCard().isEmpty() || amount <= 0) {
            return false;
        }
        // Verificar que las cuentas existan
        Account senderAccount = accountService.findByCard(sender.getCard());
        Account receiverAccount = accountService.findByCard(receiver.getCard());
        if (senderAccount == null) {
            return false;
        }
        if (receiverAccount == null) {
            return false;
        }
        // Verificar que el monto a enviar no supere el disponible
        if (senderAccount.getAvailableMoney() < amount) {
            return false;
        }
        // Crear la transaccion
        Transaction transaction = new Transaction();
        transaction.setAmmount(amount);
        transaction.setSender(senderAccount);
        transaction.setReceiver(receiverAccount);
        transaction.setDate(Calendar.getInstance().getTime());

        // Guardar la transaccion
        boolean resultTransaction = transactionService.save(transaction);
        if (!resultTransaction) {
            return false;
        }
        // Actualizar la cuenta del emisor
        sender.setAvailableMoney(sender.getAvailableMoney() - amount);
        boolean resultSender = accountService.update(sender);
        // Actualizar la cuenta del receptor
        receiver.setAvailableMoney(receiver.getAvailableMoney() + amount);
        boolean resultReceiver = accountService.update(receiver);
        // Verificar que ambas cuentas se actualizaron correctamente
        if (resultSender && resultReceiver) {
            return true;
        }
        return false;
    }
}
