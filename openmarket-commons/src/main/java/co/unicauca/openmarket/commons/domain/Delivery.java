package co.unicauca.openmarket.commons.domain;

import java.util.Date;

public class Delivery {
    private Long id;
    private Order order;
    private User deliveryMan;
    private User receiver;
    private Date date;

    public Delivery(Long id, Order order, User deliveryMan, User receiver, Date date) {
        this.id = id;
        this.order = order;
        this.deliveryMan = deliveryMan;
        this.receiver = receiver;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(User deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
