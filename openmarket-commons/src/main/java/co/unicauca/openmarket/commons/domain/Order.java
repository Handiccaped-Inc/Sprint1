package co.unicauca.openmarket.commons.domain;

import java.util.Date;

public class Order {
    private Long id;
    private User customer;
    private Product product;
    private StatusOrder status;
    private Double price;
    private Date date;
    private Double qualification;

    public Order(Long id, User customer, Product product, StatusOrder status, Double price, Date date,
            Double qualification) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.status = status;
        this.price = price;
        this.date = date;
        this.qualification = qualification;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

}
