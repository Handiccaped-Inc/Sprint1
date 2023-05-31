package co.unicauca.openmarket.server.domain.service;

import co.unicauca.openmarket.server.access.IOrderRepository;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.infra.JsonError;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private IOrderRepository orderRepository;

    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String save(Order newOrder) {
        List<JsonError> errors = new ArrayList<>();
        String response;

        if (newOrder == null || newOrder.getPrice() == null || newOrder.getDate() == null
                || newOrder.getCustomer().getId() == null ||
                newOrder.getProduct().getId() == null || newOrder.getStatus().getId() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "La orden es inválida. El precio es obligatorio."));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }

        if (orderRepository.save(newOrder)) {
            response = "OK";
        } else {
            response = "Error";
        }

        return response;
    }

    public String update(Order updatedOrder) {
        List<JsonError> errors = new ArrayList<>();
        String response;

        if (updatedOrder == null || updatedOrder.getPrice() == null || updatedOrder.getDate() == null
                || updatedOrder.getCustomer().getId() == null ||
                updatedOrder.getProduct().getId() == null || updatedOrder.getStatus().getId() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "La orden es inválida. El precio es obligatorio."));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errorsJson = gson.toJson(errors);
            return errorsJson;
        }

        if (orderRepository.update(updatedOrder)) {
            response = "OK";
        } else {
            response = "Error";
        }

        return response;
    }

    public synchronized List<Order> findByState(StatusOrder status) {
        return orderRepository.findByState(status);
    }

    public synchronized List<Order> findByUser(Integer userId) {
        return orderRepository.findByUser(userId);
    }
}
