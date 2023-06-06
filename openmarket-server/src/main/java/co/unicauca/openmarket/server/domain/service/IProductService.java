package co.unicauca.openmarket.server.domain.service;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.User;

/**
 * Interfaz que maneja los metodos que deben
 * contener todos los Service de que desean manejar un producto
 */
public interface IProductService {
    /**
     *  Metodo para guardar un producto
     * @param newProduct objeto de esquema de un producto
     * @return boolean deacuerdo a resulado
     */
    public String save(Product newProduct);

    /**
     *  Metodo para actualizar un producto
     * @param newProduct objeto de esquema de un producto
     * @return boolean deacuerdo a resulado
     */
    public String update(Product newProduct);

        /**
     *  Metodo para actualizar un producto
     * @param newProduct objeto de esquema de un producto
     * @return boolean deacuerdo a resulado
     */
    public String delete(Product newProduct);

    /**
     *  Metodo que encuentra un producto por su estado
     * @param status objeto que contiene el estado de un producto
     * @return lista de objetos entcontrados
     */
    public List<Product> findByStatus(StateProduct status);

    /**
     *  Metodo que encuentra un producto por el texto en su nombre y su
     *        descripcion
     * @param name        nombre del producto
     * @param description descripcion del producto
     * @return lista de objetos entcontrados
     */
    public List<Product> findByNameAndDescription(String name, String description);

    /**
     *  Metodo que encuentra un producto por su id
     * @param id identificador del producto
     * @return objeto entcontrado
     */
    public Product findById(Long id);

     /**
     * Metodo que encuentra los productos de un vendedor
     * @param user usuario propietario
     * @return lista de objetos Encontrados
     */
    public List<Product> findByOwner(User user);
}
