package co.unicauca.openmarket.commons.domain;

/**
 * Objeto categoria
 */
public class Category {
    private Long id;
    private String name;

    /**
     * Conostructor de categoria.
     * @param id Id de la categoria.
     * @param name Nombre o descripcion de la categoria.
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Constructor sin parametros de category
     */
    public Category() {
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
     * @param id Nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre actual
     * @return string con nombre
     */
    public String getName() {
        return name;
    }

    /**
     * Establece un nuevo nombre
     * @param name Nuevo nombre
     */
    public void setName(String name) {
        this.name = name;
    }

}
