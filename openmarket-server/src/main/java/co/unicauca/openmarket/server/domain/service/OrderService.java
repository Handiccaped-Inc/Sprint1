package co.unicauca.openmarket.server.domain.service;

import co.unicauca.openmarket.server.access.IOrderRepository;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.infra.JsonError;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase OrderService
 */
public class OrderService implements IOrderService {
    private IOrderRepository orderRepository;

    /**
     * Constructor del servicio
     * @param orderRepository Repositorio de la orden
     */
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String save(Order newOrder) {
        List<JsonError> errors = new ArrayList<>();

        if (newOrder == null || newOrder.getCustomer() == null|| newOrder.getStatus() == null || newOrder.getProduct() == null ||newOrder.getPrice() == null || newOrder.getDate() == null
                || newOrder.getCustomer().getId() == null ||
                newOrder.getProduct().getId() == null || newOrder.getStatus().getId() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "La orden es inválida. El precio es obligatorio."));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }

        return orderRepository.save(newOrder) ? "ok" : "!error";
    }

    @Override
    public String update(Order updatedOrder) {
        List<JsonError> errors = new ArrayList<>();

        if (updatedOrder == null ||  updatedOrder.getCustomer() == null|| updatedOrder.getStatus() == null || updatedOrder.getProduct() == null || updatedOrder.getPrice() == null || updatedOrder.getDate() == null
                || updatedOrder.getCustomer().getId() == null ||
                updatedOrder.getProduct().getId() == null || updatedOrder.getStatus().getId() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "La orden es inválida. El precio es obligatorio."));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }

        return orderRepository.update(updatedOrder) ? "ok" : "!error";
    }

    @Override
    public synchronized List<Order> findByState(StatusOrder status) {
        return orderRepository.findByState(status);
    }

    @Override
    public synchronized List<Order> findByUser(Long userId) {
        return orderRepository.findByUser(userId);
    }
}
