package co.unicauca.openmarket.server.domain.service;

import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.server.access.IUserRepository;

public class UserService implements IUserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        if (email == null || password == null){
            return null;
        }
        return repository.findByEmailAndPassword(email, password);
    }

    @Override
    public User findById(Long userId) {
        if (userId == null){
            return null;
        }
        return repository.findById(userId);
    }

}
