package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

import java.util.List;

public interface IShoppingCartRepository {

    /**
     * @brief Guarda un carrito en la base de datos
     * @param newCart Carrito a guardar
     * @param quantity Cantidad que se esta comprando del producto
     */
    boolean save(ShoppingCart cart);

    /**
     * @brief Busca los carritos en la base de datos
     * @return Lista de todos los carritos de compra
     */
    List<ShoppingCart> findAll();

    /**
     * @brief Elimina el carrito del usuario
     * @owner Usuario dueño del carrito
     * @return true si la operacion fue exitosa
     */
    public boolean delete(User owner);

    /**
     * @brief Busca los carritos del usuario
     * @owner Usuario dueño del carrito
     * @return Carritos de compra segun el id del usuairo
     */
    public List<ShoppingCart> findRepoByOwner(User owner);

    /**
     * @brief  Busca los productos del usuario
     * @owner Usuario dueño del carrito
     * @return Productos del carrito del usuario
     */
    public List<Product> findByOwner(User owner);
}
