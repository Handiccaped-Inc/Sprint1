package co.unicauca.openmarket.server.domain.service;

import java.util.Date;
import java.util.List;

import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.payment.access.AccountRepository;
import co.unicauca.payment.access.TransactionRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.service.PaymentFacade;

public class OpenMarketFacade {
    IDeliveryService deliveryService;
    IOrderService orderService;
    IProductService productService;
    IShoppingCartService shoppingCartService;
    IUserService userService;
    PaymentFacade paymentFacade;
    User requester;

    public OpenMarketFacade(IDeliveryService deliveryService, IOrderService orderService,
            IProductService productService, IShoppingCartService shoppingCartService, IUserService userService,
            User requester) {
        this.deliveryService = deliveryService;
        this.orderService = orderService;
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.requester = requester;
        this.paymentFacade = new PaymentFacade(new AccountRepository(), new TransactionRepository());
    }

    public List<Product> findAvailableProducts() {
        return productService.findByStatus(new StateProduct(1L, "disponible"));
    }

    public List<Product> findProductByNameAndDescription(String name, String description) {
        return productService.findByNameAndDescription(name, description);
    }

    public String buyProduct(Product product) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            // encontrar el producto
            Product dbProduct = productService.findById(product.getId());
            if (dbProduct == null) {
                return "!error";
            }

            if (dbProduct.getStock() == 0) {
                return "!error";
            }
            // crear la orden
            Order order = new Order(0L, requester, dbProduct, new StatusOrder(3L, "en espera"),
                    dbProduct.getPrice(),
                    new Date(), (double) 0);
            // guardar la orden
            if (orderService.save(order).contains("error")) {
                return "!error";
            }
            // realizar el pago a la cuenta de openmarket
            if (paymentFacade.processPayment(
                    new Account(0L, requester.getCard(), 0l),
                    new Account(0L, "openmarket", 0l),
                    order.getPrice().longValue())) {
                return "ok";
            }

            // Reducir la cantidad de productos disponibles
            dbProduct.setStock(dbProduct.getStock() - 1);
            productService.update(dbProduct);

        }
        return "!error";
    }

    public String addProductToTheShoppingCart(Product product, Long quantity) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            // encontrar el producto
            Product dbProduct = productService.findById(product.getId());

            return shoppingCartService.save(new ShoppingCart(0l, requester, dbProduct, quantity));

        }
        return "!error";
    }

    public String buyShoppingCart() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            // encontrar los productos del carrito
            List<Product> products = shoppingCartService.findByOwner(requester);
            if (products.isEmpty()) {
                return "!error";
            }
            for (Product product : products) {
                String result = buyProduct(product);
                if (result.contains("error")) {
                    return "!error";
                }
            }
            return "ok";
        }
        return "!error";
    }

    public String deleteShoppingCart() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            // encontrar los productos del carrito
            List<Product> products = shoppingCartService.findByOwner(requester);
            if (products.isEmpty()) {
                return "!error";
            }
            return shoppingCartService.delete(requester);
        }
        return "!error";
    }

    public String confirmOrder(Order order) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            order.setStatus(new StatusOrder(1l, "entregado"));
            return orderService.update(order);

        }
        return "!error";
    }

    public String qualificateOrder(Order order, Long qualification) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (order.getCustomer() == requester) {

                order.setQualification(qualification.doubleValue());
                return orderService.update(order);
            }
        }
        return "!error";
    }
}
