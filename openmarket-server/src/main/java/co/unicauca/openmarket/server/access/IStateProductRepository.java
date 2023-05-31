package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.StateProduct;

public interface IStateProductRepository {
    public StateProduct findById(long id);
}
