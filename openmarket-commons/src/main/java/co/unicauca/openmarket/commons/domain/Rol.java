package co.unicauca.openmarket.commons.domain;

/**
 * Objeto rol
 */
public class Rol {
    private Long id;
    private String name;

    /**
     * Constructor del rol
     * @param id identificador
     * @param name nombre
     */
    public Rol(Long id, String name) {
        this.id = id;
        this.name = name;
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
     * Obtiene el nombre actual
     * @return string con nombre
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
