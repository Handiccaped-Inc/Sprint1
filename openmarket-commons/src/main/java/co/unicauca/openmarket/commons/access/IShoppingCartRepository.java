package co.unicauca.openmarket.commons.access;

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
     * @brief Edita el carrito del usuario
     * @owner Usuario dueño del carrito
     * @return true si la operacion fue exitosa
     */
    //public boolean edit(User owner, ShoppingCart cart);

    /**
     * @brief Elimina el carrito del usuario
     * @owner Usuario dueño del carrito
     * @return true si la operacion fue exitosa
     */
    public boolean delete(User owner);

    /**
     * @brief Busca el carrito del usuario
     * @owner Usuario dueño del carrito
     * @return Carrito de compra segun el id del usuairo
     */
    public List<ShoppingCart> findByOwner(User owner);
}
