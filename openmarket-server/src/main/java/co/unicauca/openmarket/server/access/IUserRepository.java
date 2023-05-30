package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.User;

public interface IUserRepository {
    public User findByEmailAndPassword(String email, String password);
}
