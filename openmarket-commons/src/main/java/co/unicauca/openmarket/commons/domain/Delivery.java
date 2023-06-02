package co.unicauca.openmarket.commons.domain;

import java.util.Date;

/**
 * Objeto entrega
 */
public class Delivery {
    private Long id;
    private Order order;
    private User deliveryMan;
    private User receiver;
    private Date date;

    /**
     * Constructor de delivery
     * @param id Identificador de delivery
     * @param order Objeto de order
     * @param deliveryMan Usuario que entrega
     * @param receiver Usuario que recibe
     * @param date Fecha cuando se efectua
     */
    public Delivery(Long id, Order order, User deliveryMan, User receiver, Date date) {
        this.id = id;
        this.order = order;
        this.deliveryMan = deliveryMan;
        this.receiver = receiver;
        this.date = date;
    }

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
     * Obtiene la orden actual
     * @return Objeto orden actual
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Establece una nueva orden
     * @param order Nueva orden
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Obtiene usuario repartidor actual
     * @return objeto usuario actual
     */
    public User getDeliveryMan() {
        return deliveryMan;
    }

    /**
     * Establece un nuevo usuario repartidor
     * @param deliveryMan nuevo repartidor
     */
    public void setDeliveryMan(User deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    /**
     * Obtiene usuario receptor actual
     * @return objeto usuario actual
     */
    public User getReceiver() {
        return receiver;
    }

    /**
     * Establece un nuevo usuario receptor
     * @param receiver nuevo usuario receptor
     */
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    /**
     * Obtiene la fecha de la entrega
     * @return Date con la fecha
     */
    public Date getDate() {
        return date;
    }

    /**
     * Establece una nueva fecha
     * @param date Nueva fecha
     */
    public void setDate(Date date) {
        this.date = date;
    }

}
