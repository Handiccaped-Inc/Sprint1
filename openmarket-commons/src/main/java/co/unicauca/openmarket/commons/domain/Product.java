package co.unicauca.openmarket.commons.domain;

/**
 * Objeto producto
 */
public class Product {
    private Long id;
    private User owner;
    private Category category;
    private StateProduct state;
    private String name;
    private String description;
    private double price;
    private Long stock;
    private double latitude;
    private double longitude;

    /**
     * Constructor de producto
     * @param id Identificador
     * @param owner Usuario que lo vende
     * @param category Categoria
     * @param state Estado
     * @param name Nombre
     * @param description Descripcion
     * @param price Precio
     * @param stock Cantidad de existencias 
     * @param latitude Latitud
     * @param longitude Longitud
     */
    public Product(Long id, User owner, Category category, StateProduct state, String name, String description,
            double price, Long stock, double latitude, double longitude) {
        this.id = id;
        this.owner = owner;
        this.category = category;
        this.state = state;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Constructor sin parametros de producto
     */
    public Product() {
    }

    /**
     * Obtiene el id actual
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
     * Obtiene el dueño actual
     * @return objeto usuario dueño del producto
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
     * Obtiene categoria actual
     * @return objeto categoria del producto
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Establece una nueva categoria 
     * @param category nueva categoria
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Obtiene el estado del producto actual
     * @return objeto estado del producto
     */
    public StateProduct getState() {
        return state;
    }

    /**
     * Establece un nuevo estado del producto
     * @param state nuevo estado
     */
    public void setState(StateProduct state) {
        this.state = state;
    }

    /**
     * Obtiene el nombre actual
     * @return string con el nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre
     * @param name nuevo nombre
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripcion actual
     * @return string con descripcion
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece una nueva descripcion
     * @param description nueva descripcion
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene precio actual
     * @return double con precio
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establece un nuevo precio
     * @param price nuevo precio
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtiene cantidad de existencias actual
     * @return long con cantidad existencias
     */
    public Long getStock() {
        return stock;
    }

    /**
     * Establece una nueva cantidad de existencias
     * @param stock nueva cantidad de existencias
     */
    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * Obtiene la latitud actual
     * @return double con la latitud
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Establece una nueva latitud
     * @param latitude nueva latitud
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Obtiene la longitud actual
     * @return double con la longitud
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Establece una nueva longitud
     * @param longitude nueva longitud
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
