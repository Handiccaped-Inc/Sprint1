package co.unicauca.payment.domain.service;

import co.unicauca.payment.access.IAccountRepository;
import co.unicauca.payment.domain.Account;

public class AccountService {
    private IAccountRepository accountRepository;

    public AccountService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean update(Account account) {
        if (account.getCard().isEmpty() || account.getAvailableMoney() <= 0) {
            return false;
        }
        return accountRepository.update(account);
    }

    public Account findByCard(String cardNumber) {
        if (cardNumber.isEmpty()) {
            return null;
        }
        return accountRepository.findByCard(cardNumber);
    }
}
