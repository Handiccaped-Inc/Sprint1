package co.unicauca.openmarket.server.access;

import java.util.List;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.StateProduct;

public interface IProductRepository {
    public boolean save(Product newProduct);

    public boolean update(Product newProduct);

    public List<Product> findByStatus(StateProduct status);

    public List<Product> findByNameAndDescription(String name, String description);
}
