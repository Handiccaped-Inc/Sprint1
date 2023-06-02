package co.unicauca.payment.domain;

import java.util.Date;

/**
 * Objeto transaccion
 */
public class Transaction {
    private Long id;
    private Date date;
    private Long ammount;
    private Account sender;
    private Account receiver;

    /**
     * Constructor de transaccion sin parametros
     */
    public Transaction() {
    }

    /**
     * Constructor de transaccion
     * @param id Identificador
     * @param date Fecha cuando se efectua
     * @param ammount Cantidad de dinero
     * @param sender Cuenta del que envia
     * @param receiver Cuenta del que recibe
     */
    public Transaction(Long id, Date date, Long ammount, Account sender, Account receiver) {
        this.id = id;
        this.date = date;
        this.ammount = ammount;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Obtiene el identificador actual
     * @return long con id
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece un nuevo identificador
     * @param id nuevo identificador
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de cuando se efectua
     * @return date con la fecha
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
     * Obtiene la cantidad de dinero actual de la transaccion
     * @return long con la cantidad
     */
    public Long getAmmount() {
        return ammount;
    }

    /**
     * Establece una nueva cantidad de dinero
     * @param ammount nueva cantidad
     */
    public void setAmmount(Long ammount) {
        this.ammount = ammount;
    }

    /**
     * Obtiene la cuenta que envia
     * @return Objeto cuenta proveedora
     */
    public Account getSender() {
        return sender;
    }

    /**
     * Establece una nueva cuenta proveedora
     * @param sender nueva cuenta
     */
    public void setSender(Account sender) {
        this.sender = sender;
    }

    /**
     * Obtiene la cuenta que recibe
     * @return Objeto cuenta receptora
     */
    public Account getReceiver() {
        return receiver;
    }

    /**
     * Establece una nueva cuenta receptora
     * @param receiver nueva cuenta receptora
     */
    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

}
