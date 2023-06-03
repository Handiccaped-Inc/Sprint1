package co.unicauca.openmarket.server.domain.service;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

/**
 * Interfaz que maneja los metodos que deben
 * contener todos los Service de que desean manejar una carrito
 */
public interface IShoppingCartService {
    
    /**
     * Guarda un carrito de compra
     * @param cart Carrito a guardar
     * @return true si la operacion se realiza correctamente
     */
    public String save(ShoppingCart cart);

    /**
     * Encuentra los poductos del carrito de un usuario
     * @param owner propietario del carrito
     * @return Lista de productos del carrito
     */
    public List<Product> findByOwner(User owner);

    /**
     * Obtiene todos los carritos
     * @return Lista de todos los carritos
     */
    public List<ShoppingCart> findAll();

    /**
     * Obtiene los carritos de un usuario
     * @param owner propietario del carrito
     * @return Lista de carritos
     */
    public List<ShoppingCart> findRepoByOwner(User owner);

    /**
     * Elimina completamente el carrito del usuario
     * @param owner propietario del carrito
     * @return True si completa la operacion satisfactoriamente
     */
    public String delete(User owner);
}
