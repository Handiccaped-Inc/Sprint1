package co.unicauca.payment.domain.service;

import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.domain.Account;

public class AccountService {
    private IAccountRepository accountRepository;

    /**
     * Constructor parametrizado
     * 
     * @param accountRepository El repositorio a ser usado
     */
    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Actualiza una cuenta
     * 
     * @param account Cuenta a ser actualizada
     * @return True si la cuenta fue actualizada, false de lo contrario
     */
    public boolean update(Account account) {
        if(account == null){
            return false;
        }else if (account.getCard().isEmpty() || account.getAvailableMoney() <= 0) {
            return false;
        }
        return accountRepository.update(account);
    }

    /**
     * Busca una cuenta por su numero de tarjeta
     * 
     * @param cardNumber Numero de tarjeta de la cuenta a buscar
     * @return La cuenta encontrada, null de lo contrario
     */
    public Account findByCard(String cardNumber) {
        if (cardNumber.isEmpty()) {
            return null;
        }
        return accountRepository.findByCard(cardNumber);
    }
}
