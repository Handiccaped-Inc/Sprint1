package co.unicauca.openmarket.commons.domain;

public class ShoppingCart {
    private Long id;
    private User owner;
    Product product;// Productos y su cantidad

    public ShoppingCart(Long id, User owner, Product product) {
        this.id = id;
        this.owner = owner;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
