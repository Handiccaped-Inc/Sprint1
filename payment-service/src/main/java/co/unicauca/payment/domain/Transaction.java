package co.unicauca.payment.domain;

import java.util.Date;

public class Transaction {
    private Long id;
    private Date date;
    private Long ammount;
    private Account sender;
    private Account receiver;

    public Transaction(Long id, Date date, Long ammount, Account sender, Account receiver) {
        this.id = id;
        this.date = date;
        this.ammount = ammount;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAmmount() {
        return ammount;
    }

    public void setAmmount(Long ammount) {
        this.ammount = ammount;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

}
