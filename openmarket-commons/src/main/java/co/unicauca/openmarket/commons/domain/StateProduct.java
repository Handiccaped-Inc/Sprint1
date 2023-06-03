package co.unicauca.openmarket.commons.domain;

/**
 * Objeto estado del producto
 */
public class StateProduct {
    private Long id;
    private String name;

    /**
     * Constructor del estado del producto
     * @param id Identificador
     * @param name Nombre del estado
     */
    public StateProduct(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor sin parametros del estado del producto
     */
    public StateProduct() {
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
     * Obtiene nombre actual del estado
     * @return string con nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre de estado
     * @param name nuevo nombre de estado
     */
    public void setName(String name) {
        this.name = name;
    }

}
