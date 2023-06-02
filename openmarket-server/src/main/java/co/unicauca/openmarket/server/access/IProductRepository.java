package co.unicauca.openmarket.server.access;

import java.util.List;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.StateProduct;

/**
 * Interfaz de ProductRepository
 */
public interface IProductRepository {
    /**
     *  Metodo para guardar un producto
     * @param newProduct objeto de esquema de un producto
     * @return boolean deacuerdo a resulado
     */
    public boolean save(Product newProduct);

    /**
     *  Metodo para actualizar un producto
     * @param newProduct objeto de esquema de un producto
     * @return boolean deacuerdo a resulado
     */
    public boolean update(Product newProduct);

     /**
     *  Metodo para eliminar un producto
     * @param newProduct objeto de esquema de un producto
     * @return boolean deacuerdo a resulado
     */
    public boolean delete(Product newProduct);

    /**
     *  Metodo que encuentra un producto por su estado
     * @param status objeto que contiene el estado de un producto
     * @return lista de objetos entcontrados
     */
    public List<Product> findByStatus(StateProduct status);

    /**
     *  Metodo que encuentra un producto por el texto en su nombre y su
     *        descripcion
     * @param name nombre del producto
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
}
