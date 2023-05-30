package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Order;

public interface IOrderRepository {
    public boolean save(Order newOrder);

    public boolean update(Order newOrder);
}
