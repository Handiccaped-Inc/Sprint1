package co.unicauca.openmarket.server.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.server.access.IDeliveryRepository;

public class DeliveryService implements IDeliveryService {

    private IDeliveryRepository myDeliverRepository;

    DeliveryService(IDeliveryRepository myDeliveryRespository) {
        this.myDeliverRepository = myDeliveryRespository;
    }

    @Override
    public String save(Delivery newDelivery) {
        List<JsonError> errors = new ArrayList<>();
        if (newDelivery.getOrder() == null || newDelivery.getDeliveryMan() == null || newDelivery.getReceiver() == null
                || newDelivery.getDate() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST",
                    "La orden, El repartidor, el recibidor y la fecha son campos obligatorios"));

        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;

        }

        return myDeliverRepository.save(newDelivery) ? "ok" : "!error";
    }

    @Override
    public String update(Delivery newDelivery) {
        List<JsonError> errors = new ArrayList<>();
        if (newDelivery.getOrder() == null || newDelivery.getDeliveryMan() == null || newDelivery.getReceiver() == null
                || newDelivery.getDate() == null || newDelivery.getId() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST",
                    "La orden, El id, El repartidor, el recibidor y la fecha son campos obligatorios"));

        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;

        }

        return myDeliverRepository.update(newDelivery) ? "ok" : "!error";

    }

}
