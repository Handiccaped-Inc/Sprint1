package co.unicauca.openmarket.commons.domain;

public class ShoppingCart {
    private Long id;
    private User owner;
    Product product;// Productos y su cantidad
    private Long quantity;

    public ShoppingCart(){};

    public ShoppingCart(Long id, User owner, Product product, Long quantity) {
        this.id = id;
        this.owner = owner;
        this.product = product;
        this.quantity = quantity;
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

    public Long getQuantity(){
        return this.quantity;
    }

    public void setQuantity(Long quantity){
        this.quantity = quantity;
    }
}
