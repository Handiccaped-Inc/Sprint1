package co.unicauca.openmarket.commons.domain;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public StateProduct getState() {
        return state;
    }

    public void setState(StateProduct state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
