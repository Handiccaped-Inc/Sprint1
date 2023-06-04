package co.unicauca.openmarket.client.access;

import java.util.List;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;

public class OpenMarketFacadeAccesImplSockets implements IOpenMarketFacadeDeserialized {

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserByEmailAndPassword'");
    }

    @Override
    public List<Product> findAvailableProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAvailableProducts'");
    }

    @Override
    public List<Product> findProductByNameAndDescription(String name, String description) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findProductByNameAndDescription'");
    }

    @Override
    public boolean buyProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyProduct'");
    }

    @Override
    public boolean addProductToTheShoppingCart(Product product, Long quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addProductToTheShoppingCart'");
    }

    @Override
    public List<ShoppingCart> getShoppingCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getShoppingCart'");
    }

    @Override
    public boolean buyShoppingCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyShoppingCart'");
    }

    @Override
    public boolean deleteShoppingCart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteShoppingCart'");
    }

    @Override
    public boolean saveProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveProduct'");
    }

    @Override
    public boolean updateProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public List<Product> getOwnProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOwnProducts'");
    }

    @Override
    public List<Order> getOrders() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrders'");
    }

    @Override
    public boolean confirmOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'confirmOrder'");
    }

    @Override
    public boolean qualificateOrder(Order order, Long qualification) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'qualificateOrder'");
    }

    @Override
    public boolean registerDelivery(Delivery delivery) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerDelivery'");
    }

}
