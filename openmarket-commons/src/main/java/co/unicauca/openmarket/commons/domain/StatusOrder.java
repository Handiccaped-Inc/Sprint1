package co.unicauca.openmarket.commons.domain;

/**
 * Objeto estado de la orden
 */
public class StatusOrder {
    private Long id;
    private String name;

    /**
     * Constructor
     * @param id Identificador
     * @param name Nombre del estado de la orden
     */
    public StatusOrder(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor sin parametros del estado de la orden
     */
    public StatusOrder() {
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
     * Obtiene el nombre actual del estado
     * @return string con el nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre
     * @param name nuevo nombre
     */
    public void setName(String name) {
        this.name = name;
    }

}
