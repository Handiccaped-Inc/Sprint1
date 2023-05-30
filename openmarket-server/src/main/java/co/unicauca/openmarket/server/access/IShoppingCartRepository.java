package co.unicauca.openmarket.server.access;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.User;

public interface IShoppingCartRepository {
    public boolean save(Long id, User owner, Product product);

    public List<Product> findByOwner(User owner);
}
