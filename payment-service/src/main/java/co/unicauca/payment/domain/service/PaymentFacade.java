package co.unicauca.payment.domain.service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import co.unicauca.payment.access.AccountRepository;
import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.access.ITransactionRepository;
import co.unicauca.payment.access.TransactionRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.Transaction;

public class PaymentFacade {
    AccountService accountService;
    TransactionService transactionService;

    /**
     * Constructor parametrizado
     * 
     * @param accountRepository     The repository to be used
     * @param transactionRepository The repository to be used
     */
    public PaymentFacade(IAccountRepository accountRepository, ITransactionRepository transactionRepository) {
        accountService = new AccountService(accountRepository);
        transactionService = new TransactionService(transactionRepository);
    }

    /**
     * Default constructor
     */
    public PaymentFacade() {
        accountService = new AccountService(new AccountRepository());
        transactionService = new TransactionService(new TransactionRepository());
    }

    /**
     * Process a payment
     * 
     * @param sender   The account that will send the money
     * @param receiver The account that will receive the money
     * @param amount   The amount of money to be sent
     * @return true if the payment was successful, false otherwise
     */
    public boolean processPayment(Account sender, Account receiver, Long amount) {
        // Check if the accounts are valid
        if (sender.getCard().isEmpty() || receiver.getCard().isEmpty() || amount <= 0) {
            return false;
        }
        // Check if the accounts exist
        Account senderAccount = accountService.findByCard(sender.getCard());
        Account receiverAccount = accountService.findByCard(receiver.getCard());
        if (senderAccount == null) {
            return false;
        }
        if (receiverAccount == null) {
            return false;
        }
        // Check if the sender has enough money
        if (senderAccount.getAvailableMoney() < amount) {
            return false;
        }
        // Create the transaction
        Transaction transaction = new Transaction();
        transaction.setAmmount(amount);
        transaction.setSender(senderAccount);
        transaction.setReceiver(receiverAccount);
        transaction.setDate(Calendar.getInstance().getTime());

        // Save the transaction
        boolean resultTransaction = transactionService.save(transaction);
        if (!resultTransaction) {
            return false;
        }
        // Update the sender account
        sender.setAvailableMoney(sender.getAvailableMoney() - amount);
        boolean resultSender = accountService.update(sender);
        // Update the receiver account
        receiver.setAvailableMoney(receiver.getAvailableMoney() + amount);
        boolean resultReceiver = accountService.update(receiver);
        // Check if the update was successful
        if (resultSender && resultReceiver) {
            return true;
        }
        return false;
    }
}
