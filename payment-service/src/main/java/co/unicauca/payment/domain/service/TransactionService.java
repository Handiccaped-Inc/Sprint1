package co.unicauca.payment.domain.service;

import co.unicauca.payment.access.ITransactionRepository;
import co.unicauca.payment.domain.Transaction;

public class TransactionService {
    private ITransactionRepository transactionRepository;

    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public boolean save(Transaction newTransaction) {
        if (newTransaction == null) {
            return false;
        } else if (newTransaction.getDate() == null || newTransaction.getAmmount() <= 0
                || newTransaction.getSender() == null || newTransaction.getReceiver() == null) {
            return false;
        }
        return transactionRepository.save(newTransaction);
    }

}
