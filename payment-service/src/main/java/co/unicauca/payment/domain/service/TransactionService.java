package co.unicauca.payment.domain.service;

import co.unicauca.payment.access.ITransactionRepository;
import co.unicauca.payment.domain.Transaction;

public class TransactionService {
    private ITransactionRepository transactionRepository;

    /**
     * Constructor parametrizado
     * 
     * @param transactionRepository El repositorio a ser usado
     */
    public TransactionService(ITransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Guarda una transaccion
     * 
     * @param newTransaction Transaccion a ser guardada
     * @return True si la transaccion fue guardada, false de lo contrario
     */
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
