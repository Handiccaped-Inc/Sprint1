package co.unicauca.payment.access;

import co.unicauca.payment.domain.Transaction;

/**
 * Interfaz que maneja los metodos que deben
 * contener todos los Service de que desean manejar el repositorio de la transaccion
 */
public interface ITransactionRepository {
    /**
     * Crea una nueva transacción
     * 
     * @param newTransaction transacción a crear
     * @return true si la transacción se creó correctamente, false de lo contrario
     */
    public boolean save(Transaction newTransaction);
}
