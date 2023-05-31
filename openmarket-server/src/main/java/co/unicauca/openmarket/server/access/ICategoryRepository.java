package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Category;

public interface ICategoryRepository {

    public Category findById(long id);

}
