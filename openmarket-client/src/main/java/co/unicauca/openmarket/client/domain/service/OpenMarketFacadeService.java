package co.unicauca.openmarket.client.domain.service;

import java.util.List;

import co.unicauca.openmarket.client.access.IOpenMarketFacadeDeserialized;
import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

public class OpenMarketFacadeService {
    public IOpenMarketFacadeDeserialized openMarkedFacade;

    public OpenMarketFacadeService(IOpenMarketFacadeDeserialized openMarkedFacade) {
        this.openMarkedFacade = openMarkedFacade;
    }

    /**
     * Busca un usuario por email y password
     * 
     * @param email    El email del usuario
     * @param password La contraseña del usuario
     * @return El usuario encontrado
     */
    public User findUserByEmailAndPassword(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return null;
        }
        return openMarkedFacade.findUserByEmailAndPassword(email, password);
    }

    /**
     * Busca los productos disponibles
     * 
     * @return lista de productos disponibles
     */
    public List<Product> findAvailableProducts() {
        return openMarkedFacade.findAvailableProducts();
    }

    /**
     * Busca los productos por nombre y descripcion
     * 
     * @param name        nombre del producto
     * @param description descripcion del producto
     * @return lista de productos
     */
    public List<Product> findProductByNameAndDescription(String name, String description) {
        return openMarkedFacade.findProductByNameAndDescription(name, description);
    }

    /**
     * Realiza la compra de un producto
     * 
     * @param product producto a comprar
     * @return mensaje de confirmacion
     */
    public boolean buyProduct(Product product) {
        if (product.getId() == null || product.getId() <= 0) {
            return false;
        }
        return openMarkedFacade.buyProduct(product);
    }

    /**
     * Agrega un producto al carrito de compras
     * 
     * @param product  Producto a agregar
     * @param quantity Cantidad del producto
     * @return mensaje de confirmacion
     */
    public boolean addProductToTheShoppingCart(Product product, Long quantity) {
        if (product.getId() == null || product.getId() <= 0 || quantity == null || quantity <= 0) {
            return false;
        }
        return openMarkedFacade.addProductToTheShoppingCart(product, quantity);
    }

    /**
     * Obtien los productos del carrito de compras del solicitante
     * 
     * @return lista de productos del carrito de compras
     */
    public List<ShoppingCart> getShoppingCart() {
        return openMarkedFacade.getShoppingCart();
    }

    /**
     * Compra los productos del carrito de compras
     * 
     * @return mensaje de confirmacion
     */
    public boolean buyShoppingCart() {
        return openMarkedFacade.buyShoppingCart();
    }

    /**
     * Elimina los productos del carrito de compras
     * 
     * @return mensaje de confirmacion
     */
    public boolean deleteShoppingCart() {
        return openMarkedFacade.deleteShoppingCart();
    }

    /**
     * Guarda un producto
     * 
     * @param product producto a guardar
     * @return mensaje de confirmacion
     */
    public boolean saveProduct(Product product) {
        if (product.getName().isEmpty() || product.getDescription().isEmpty() || product.getStock() <= 0) {
            return false;
        }
        return openMarkedFacade.saveProduct(product);
    }

    /**
     * Actualiza un producto
     * 
     * @param product producto a actualizar con el estado a actualizar
     * @return mensaje de confirmacion
     */
    public boolean updateProduct(Product product) {
        if (product.getId() == null || product.getId() <= 0) {
            return false;
        }
        return openMarkedFacade.updateProduct(product);
    }

    /**
     * Obtiene los productos del solicitante
     * 
     * @return lista de productos
     */
    public List<Product> getOwnProducts() {
        return openMarkedFacade.getOwnProducts();
    }

    /**
     * Obtiene las ordenes del solicitante en caso de ser usuario, si es vendedor
     * obtiene todas las ordenes de productos
     * 
     * @return lista de ordenes
     */
    public List<Order> getOrders() {
        return openMarkedFacade.getOrders();
    }

    /**
     * Confirma la recepcion de una orden, realiza el pago al vendedor y se queda
     * con la comision
     * 
     * @param order orden a confirmar
     * @return mensaje de confirmacion
     */
    public boolean confirmOrder(Order order) {
        if (order.getId() <= 0 || order.getCustomer().getId() <= 0) {
            return false;
        }
        return openMarkedFacade.confirmOrder(order);
    }

    /**
     * Califica una orden
     * 
     * @param order         orden a calificar
     * @param qualification calificacion
     * @return mensaje de confirmacion
     */
    public boolean qualificateOrder(Order order, Long qualification) {
        if (order.getId() <= 0 || qualification <= 0) {
            return false;
        }
        return openMarkedFacade.qualificateOrder(order, qualification);
    }

    /**
     * registra una entrega
     * 
     * @param delivery entrega a registrar
     * @return mensaje de confirmacion
     */
    public boolean registerDelivery(Delivery delivery) {
        if (delivery.getOrder().getId() <= 0) {
            return false;
        }
        return openMarkedFacade.registerDelivery(delivery);
    }
}
