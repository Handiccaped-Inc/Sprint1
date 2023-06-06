package co.unicauca.openmarket.commons.domain.service;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

/**
 * Interfaz de openmarketfacade
 */
public interface IOpenMarketFacade {

    /**
     * Busca los productos disponibles
     * 
     * @return lista de productos disponibles
     */
    List<Product> findAvailableProducts();

    /**
     * Busca los productos por nombre y descripcion
     * 
     * @param name        nombre del producto
     * @param description descripcion del producto
     * @return lista de productos
     */
    List<Product> findProductByNameAndDescription(String name, String description);

    /**
     * Realiza la compra de un producto
     * 
     * @param product producto a comprar
     * @return mensaje de confirmacion
     */
    String buyProduct(Product product);

    /**
     * Agrega un producto al carrito de compras
     * 
     * @param product  Producto a agregar
     * @param quantity Cantidad del producto
     * @return mensaje de confirmacion
     */
    String addProductToTheShoppingCart(Product product, Long quantity);

    /**
     * Obtien los productos del carrito de compras del solicitante
     * 
     * @return lista de productos del carrito de compras
     */
    List<ShoppingCart> getShoppingCart();

    /**
     * Compra los productos del carrito de compras
     * 
     * @return mensaje de confirmacion
     */
    String buyShoppingCart();

    /**
     * Elimina los productos del carrito de compras
     * 
     * @return mensaje de confirmacion
     */
    String deleteShoppingCart();

    /**
     * Guarda un producto
     * 
     * @param product producto a guardar
     * @return mensaje de confirmacion
     */
    String saveProduct(Product product);

    /**
     * Actualiza un producto
     * 
     * @param product producto a actualizar con el estado a actualizar
     * @return mensaje de confirmacion
     */
    String updateProduct(Product product);

    /**
     * Obtiene los productos del solicitante
     * 
     * @return lista de productos
     */
    List<Product> getOwnProducts();

    /**
     * Obtiene las ordenes del solicitante en caso de ser usuario, si es vendedor
     * obtiene todas las ordenes de productos
     * 
     * @return lista de ordenes
     */
    List<Order> getOrders();

    /**
     * Confirma la recepcion de una orden, realiza el pago al vendedor y se queda
     * con la comision
     * 
     * @param order orden a confirmar
     * @return mensaje de confirmacion
     */
    String confirmOrder(Order order);

    /**
     * Califica una orden
     * 
     * @param order         orden a calificar
     * @param qualification calificacion
     * @return mensaje de confirmacion
     */
    String qualificateOrder(Order order, Long qualification);

    /**
     * registra una entrega
     * 
     * @param delivery entrega a registrar
     * @return mensaje de confirmacion
     */
    String registerDelivery(Delivery delivery);

    /**
     * Busca un usuario por email y password
     * 
     * @param email    El email del usuario
     * @param password La contraseña del usuario
     * @return El usuario encontrado
     */
    User findUserByEmailAndPassword(String email, String password);

}