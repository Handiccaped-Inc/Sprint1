package co.unicauca.payment.domain;

/**
 * Objeto cuenta
 */
public class Account {

    private Long id;
    private String card;
    private Long availableMoney;

    /**
     * Constructor de la cuenta sin parametros
     */
    public Account() {
    }

    /**
     * Constructor de la cuenta
     * @param id Identificador
     * @param card Numero de tarjeta
     * @param availableMoney Dinero disponible
     */
    public Account(Long id, String card, Long availableMoney) {
        this.id = id;
        this.card = card;
        this.availableMoney = availableMoney;
    }

    /**
     * Obtiene el id actual
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
     * Obtiene el numero de la tarjeta actual
     * @return tarjeta actual
     */
    public String getCard() {
        return card;
    }

    /**
     * Establece un nuevo numero de  tarjeta 
     * @param card nueva tarjeta
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * Obtiene el dinero disponible
     * @return long con dinero disponible
     */
    public Long getAvailableMoney() {
        return availableMoney;
    }

    /**
     * Establece un nuevo valor al dinero disponible
     * @param availableMoney nuevo valor
     */
    public void setAvailableMoney(Long availableMoney) {
        this.availableMoney = availableMoney;
    }

}
