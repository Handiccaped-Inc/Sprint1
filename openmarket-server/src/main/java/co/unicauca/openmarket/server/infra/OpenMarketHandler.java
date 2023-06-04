package co.unicauca.openmarket.server.infra;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import co.unicauca.openmarket.server.access.IProductRepository;
import co.unicauca.openmarket.server.domain.service.IDeliveryService;
import co.unicauca.openmarket.server.domain.service.IOrderService;
import co.unicauca.openmarket.server.domain.service.IProductService;
import co.unicauca.openmarket.server.domain.service.IShoppingCartService;
import co.unicauca.openmarket.server.domain.service.IUserService;
import co.unicauca.openmarket.server.domain.service.OpenMarketFacade;
import co.unicauca.openmarket.server.domain.service.ProductService;
import co.unicauca.strategyserver.infra.ServerHandler;
import co.unicauca.openmarket.server.domain.service.*;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
/**
 *
 * @author Braian Rey / Arturo Restrepo Ruiz
 */
public class OpenMarketHandler {
    private Map<String, Consumer<Protocol>> actionMap;
    private OpenMarketFacade facade;
    User requester; 
    //facade = new OpenMarketFacade(deliveryService, orderService, productService, shoppingCartService, userService, requester);

    public OpenMarketHandler(OpenMarketFacade facade) {
        actionMap = new HashMap<>();
        this.facade = facade;
        actionMap.put("findAvailableProducts", this::processFindAvailableProducts);
        actionMap.put("findProductByNameAndDescription", this::processFindProductByNameAndDescription);
        actionMap.put("buyProduct", this::processBuyProduct);
        actionMap.put("addProductToShoppingCart", this::processAddProductToShoppingCart);
        actionMap.put("getShoppingCart", this::processGetShoppingCart);
        actionMap.put("buyShoppingCart", this::processBuyShoppingCart);
        actionMap.put("deleteShoppingCart", this::processDeleteShoppingCart);
        actionMap.put("saveProduct", this::processSaveProduct);
        actionMap.put("updateProduct", this::processUpdateProduct);
        actionMap.put("getOwnProductst", this::processGetOwnProducts);
        actionMap.put("getOrders", this::processGetOrders);
        actionMap.put("confirmOrder", this::processConfirmOrder);
        actionMap.put("qualificateOrder", this::processQualificateOrder);
        actionMap.put("registerDelivery", this::processRegisterDelivery);
    }

    public List<Product> processFindAvailableProducts() {
        return facade.findAvailableProducts();
    }

    public List<Product> processFindProductByNameAndDescription(String name, String description) {
        return facade.findProductByNameAndDescription(name, description);
    }

    public String processBuyProduct(Product product) {
        return facade.buyProduct(product);
    }

    
    public String processAddProductToShoppingCart(Product product, Long quantity) {
        return facade.addProductToTheShoppingCart(product, quantity);
    }

    public List<ShoppingCart> processGetShoppingCart() {
        return facade.getShoppingCart();
    }
    
    public String processBuyShoppingCart() {
        return facade.buyShoppingCart();
    }
    
    public String processDeleteShoppingCart() {
        return facade.deleteShoppingCart();
    }
    
    public String processSaveProduct(Product product) {
        return facade.saveProduct(product);
    }
    
    public String processUpdateProduct(Product product) {
        return facade.updateProduct(product);
    }
    
    public List<Product> processGetOwnProducts() {
        return facade.getOwnProducts();
    }
    
    public List<Order> processGetOrders() {
        return facade.getOrders();
    }
    
    public String processConfirmOrder(Order order) {
        return facade.confirmOrder(order);
    }
    
    public String processQualificateOrder(Order order, Long qualification) {
        return facade.qualificateOrder(order, qualification);
    }
    
    public String processRegisterDelivery(Delivery delivery) {
        return facade.registerDelivery(delivery);
    }

}
