package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Delivery;

public interface IDeliveryRespository {

    public boolean save(Delivery newDelivery);

    public boolean update(Delivery newDelivery);
}
