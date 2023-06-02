package co.unicauca.payment.access;

import co.unicauca.payment.domain.Transaction;

public interface ITransactionRepository {
    /**
     * Crea una nueva transacción
     * 
     * @param newTransaction transacción a crear
     * @return true si la transacción se creó correctamente, false de lo contrario
     */
    public boolean save(Transaction newTransaction);
}
