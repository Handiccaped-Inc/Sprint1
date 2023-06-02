package co.unicauca.openmarket.commons.domain;

/**
 * Objeto carrito de compra
 */
public class ShoppingCart {
    private Long id;
    private User owner;
    Product product;
    private Long quantity;

    /**
     * Constructor carrito de compra
     * @param id Identificador
     * @param owner Dueño del carrito
     * @param product Producto a comprar
     * @param quantity Cantidad de producto a comprar
     */
    public ShoppingCart(Long id, User owner, Product product, Long quantity) {
        this.id = id;
        this.owner = owner;
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Constructor del carrito sin parametros
     */
    public ShoppingCart(){};

    /**
     * Obtiene identificador actual
     * @return long con id
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece un nuevo id
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el dueño del carrito
     * @return objeto usuario dueño del carrito
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Establece un nuevo dueño
     * @param owner nuevo dueño
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Obtiene el producto actual
     * @return objeto producto a comprar
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Establece un nuevo producto
     * @param product nuevo producto
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Obtiene la cantidad de producto a comprar
     * @return long con la cantidad
     */
    public Long getQuantity(){
        return this.quantity;
    }

    /**
     * Establece una nueva cantidad del producto
     * @param quantity nueva cantidad
     */
    public void setQuantity(Long quantity){
        this.quantity = quantity;
    }
}
