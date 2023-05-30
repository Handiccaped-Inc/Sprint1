package co.unicauca.openmarket.commons.domain;

import java.util.HashMap;

public class ShoppingCart {
    private Long id;
    private User owner;
    HashMap<Product, Integer> products;// Productos y su cantidad

    public ShoppingCart(Long id, User owner, HashMap<Product, Integer> products) {
        this.id = id;
        this.owner = owner;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Product, Integer> products) {
        this.products = products;
    }

}
