package co.unicauca.openmarket.server.domain.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.server.access.IShoppingCartRepository;


public class ShoppingCartService implements IShoppingCartService {

    private IShoppingCartRepository repository;

    public ShoppingCartService(IShoppingCartRepository repository) {
        this.repository = repository;
    }

    @Override
    public String save(ShoppingCart newCart) {
        List<JsonError> errors = new ArrayList<>();
        if (newCart.getOwner() == null || newCart.getProduct() == null || newCart.getQuantity() == 0L) {
            errors.add(
                    new JsonError("400", "BAD_REQUEST", "El dueño, el producto y la cantidad son campos obligatorios"));

        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;

        }

        return repository.save(newCart) ? "ok" : "!error";
    }

    @Override

    public List<Product> findByOwner(User owner) {
        if (owner == null){
            return new ArrayList<>();
        }
        List<Product> products = new ArrayList<>();
        products = repository.findByOwner(owner);
        return products;
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> carts = new ArrayList<>();
        carts = repository.findAll();
        return carts;
    }

    @Override
    public List<ShoppingCart> findRepoByOwner(User owner){
        if (owner == null){
            return new ArrayList<>();
        }
        List<ShoppingCart> carts = new ArrayList<>();
        carts = repository.findRepoByOwner(owner);
        return carts;
    }

    @Override
    public String delete(User owner) {
        List<JsonError> errors = new ArrayList<>();
        if (owner == null) {
            errors.add(new JsonError("400", "BAD_REQUEST", "El dueño es un campo obligatorio"));
        }

        if (!errors.isEmpty()) {
            Gson gson = new Gson();
            String errosJson = gson.toJson(errors);
            return errosJson;
        }

        return repository.delete(owner) ? "ok" : "!error";
    }
}
