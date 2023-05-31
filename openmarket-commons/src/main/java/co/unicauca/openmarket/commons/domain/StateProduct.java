package co.unicauca.openmarket.commons.domain;

public class StateProduct {
    private Long id;
    private String name;

    public StateProduct(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StateProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
