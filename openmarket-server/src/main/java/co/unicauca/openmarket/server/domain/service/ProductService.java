package co.unicauca.openmarket.server.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.server.access.IProductRepository;

public class ProductService implements IProductService {
    IProductRepository myProductRepository;

    public ProductService(IProductRepository myProductRepository) {
        this.myProductRepository = myProductRepository;
    }

    @Override
    public String save(Product newProduct) {
        List<JsonError> errors = new ArrayList<>();
        if (newProduct.getName().isEmpty()
                || newProduct.getDescription().isEmpty()
                || newProduct.getOwner() == null
                || newProduct.getCategory() == null
                || newProduct.getState() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "LA INFORMACION ESTA INCOMPLETA"));
        }
        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;
        }
        return myProductRepository.save(newProduct) ? "ok" : "!error";

    }

    @Override
    public String update(Product newProduct) {
        List<JsonError> errors = new ArrayList<>();
        if (newProduct.getName().isEmpty()
                || newProduct.getDescription().isEmpty()
                || newProduct.getOwner() == null
                || newProduct.getCategory() == null
                || newProduct.getState() == null
                || newProduct.getId() == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "LA INFORMACION ESTA INCOMPLETA"));
        }
        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;
        }
        return myProductRepository.update(newProduct) ? "ok" : "!error";
    }

    @Override
    public List<Product> findByStatus(StateProduct status) {
        if (status.getName().isEmpty()) {
            return new ArrayList<>();
        }
        return myProductRepository.findByStatus(status);
    }

    @Override
    public List<Product> findByNameAndDescription(String name, String description) {
        if (name.isEmpty() || description.isEmpty()) {
            return new ArrayList<>();
        }
        return myProductRepository.findByNameAndDescription(name, description);
    }

    @Override
    public Product findById(Long id) {
        if (id == null) {
            return null;
        }
        return myProductRepository.findById(id);
    }

}
