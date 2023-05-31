package co.unicauca.openmarket.server.access;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

import java.util.List;

public interface IShoppingCartRepository {

    /**
     * Guarda un carrito en la base de datos
     * @param cart Carrito a guardar
     */
    public boolean save(ShoppingCart cart);

    /**
     * Busca los carritos en la base de datos
     * @return Lista de todos los carritos de compra
     */
    public List<ShoppingCart> findAll();

    /**
     * Elimina el carrito del usuario
     * @param owner Usuario dueño del carrito
     * @return true si la operacion fue exitosa
     */
    public boolean delete(User owner);

    /**
     * Busca los carritos del usuario
     * @owner Usuario dueño del carrito
     * @return Carritos de compra segun el id del usuairo
     */
    public List<ShoppingCart> findRepoByOwner(User owner);

    /**
     * Busca los productos del usuario
     * @owner Usuario dueño del carrito
     * @return Productos del carrito del usuario
     */
    public List<Product> findByOwner(User owner);
}
