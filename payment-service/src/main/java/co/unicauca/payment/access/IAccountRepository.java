package co.unicauca.payment.access;

import co.unicauca.payment.domain.Account;

/**
 * Interfaz que maneja los metodos que deben
 * contener todos los Service de que desean manejar el repositorio de la cuenta
 */
public interface IAccountRepository {
    /**
     * Busca una cuenta en la base de datos
     * 
     * @param cardNumber número de la tarjeta
     * @return objeto de tipo Account
     */
    public Account findByCard(String cardNumber);

    /**
     * Actualiza una cuenta en la base de datos
     * 
     * @param account objeto de tipo Account
     * @return true si se actualizó correctamente, false de lo contrario
     */
    public boolean update(Account account);
}
