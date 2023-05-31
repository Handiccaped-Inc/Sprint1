package co.unicauca.openmarket.commons.domain;

public class StatusOrder {
    private Long id;
    private String name;

    public StatusOrder(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StatusOrder() {
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
