package co.unicauca.payment.domain;

public class Account {

    private Long id;
    private String card;
    private Long availableMoney;

    public Account() {
    }

    public Account(Long id, String card, Long availableMoney) {
        this.id = id;
        this.card = card;
        this.availableMoney = availableMoney;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Long getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(Long availableMoney) {
        this.availableMoney = availableMoney;
    }

}
