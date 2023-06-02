package co.unicauca.openmarket.commons.domain;

import java.util.Date;

/**
 * Objeto orden
 */
public class Order {
    private Long id;
    private User customer;
    private Product product;
    private StatusOrder status;
    private Double price;
    private Date date;
    private Double qualification;

    /**
     * Constructor de order
     * @param id Identificador
     * @param customer Usuario cliente
     * @param product Usuario producto
     * @param status Objeto estado
     * @param price precio
     * @param date fecha cuando se efectua
     * @param qualification calificacion
     */
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

    /**
     * Constructor sin parametros de order
     */
    public Order() {
    }

    /**
     * Obtiene el identificador
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
     * Obtiene usuario cliente
     * @return objeto usuario actual
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Establece un nuevo cliente
     * @param customer nuevo cliente
     */
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Obtiene el producto
     * @return objeto producto actual
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
     * Obtiene el estado actual
     * @return objeto estado actual
     */
    public StatusOrder getStatus() {
        return status;
    }

    /**
     * Establece un nuevo estado
     * @param status Nuevo estado
     */
    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    /**
     * Obtiene precio actual
     * @return double con el precio
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Establece un nuevo precio
     * @param price nuevo precio
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Obtiene la fecha de la orden
     * @return Date con la fecha
     */
    public Date getDate() {
        return date;
    }

    /**
     * Establece una nueva fecha
     * @param date nueva fecha
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Obtiene la calificacion actual
     * @return double con la calificacion
     */
    public Double getQualification() {
        return qualification;
    }

    /**
     * Establece una nueva calificacion
     * @param qualification nueva calificacion
     */
    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

}
