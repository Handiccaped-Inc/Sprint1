package co.unicauca.openmarket.server.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.server.access.IDeliveryRespository;

public class DeliveryService implements IDeliveryService{

    IDeliveryRespository myDeliverRepository;

    DeliveryService(IDeliveryRespository myDeliveryRespository){
        this.myDeliverRepository = myDeliveryRespository;
    }

    @Override
    public String save(Delivery newDelivery) {
        List<JsonError> errors = new ArrayList<>();
        if (newDelivery.getOrder() == null || newDelivery.getDeliveryMan() == null || newDelivery.getReceiver() == null || newDelivery.getDate() == null) {
            errors.add(new JsonError("400","BAD_REQUEST","La orden, El repartidor, el recibidor y la fecha son campos obligatorios"));
            
        }

        if(!errors.isEmpty()){
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;

        }

        boolean response = myDeliverRepository.save(newDelivery);
        return response ? "ok" : "!error";
    }

    @Override
    public String update(Delivery newDelivery) {
        List<JsonError> errors = new ArrayList<>();
        if (newDelivery.getOrder() == null || newDelivery.getDeliveryMan() == null || newDelivery.getReceiver() == null || newDelivery.getDate() == null || newDelivery.getId()  == null) {
            errors.add(new JsonError("400","BAD_REQUEST","La orden, El id, El repartidor, el recibidor y la fecha son campos obligatorios"));
            
        }

        if(!errors.isEmpty()){
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;

        }

        boolean response = myDeliverRepository.update(newDelivery);
        return response ? "ok" : "!error";
        
    }
    
    
}
