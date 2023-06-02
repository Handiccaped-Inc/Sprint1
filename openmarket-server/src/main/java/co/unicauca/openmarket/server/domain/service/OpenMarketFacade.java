package co.unicauca.openmarket.server.domain.service;

import java.util.Date;
import java.util.List;

import javax.sound.midi.Receiver;

import co.unicauca.openmarket.commons.domain.Delivery;
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
            if (product.getStock() == 0) {
                product.setState(new StateProduct(2L, "no disponible"));
            }
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

    public List<ShoppingCart> getShoppingCart() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            return shoppingCartService.findRepoByOwner(requester);
        }
        return null;
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

    public String saveProduct(Product product) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (requester.getRol().getName().equals("vendedor")) {
                product.setOwner(requester);
                if (product.getStock() <= 0) {
                    return "!error";
                }
                product.setState(new StateProduct(1L, "disponible"));
                return productService.save(product);
            }
        }
        return "!error";
    }

    public String updateProduct(Product product) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (requester.getRol().getName().equals("vendedor")) {
                product.setOwner(requester);
                if (product.getStock() <= 0) {
                    return "!error";
                }
                return productService.update(product);
            }
        }
        return "!error";
    }

    public List<Product> getOwnProducts() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (requester.getRol().getName().equals("vendedor")) {
                return productService.findByOwner(requester);
            }
        }
        return null;
    }

    public List<Order> getOrders() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (requester.getRol().getName().equals("repartidor")) {
                orderService.findByState(new StatusOrder(3L, "en espera"));
            } else {
                return orderService.findByUser(requester.getId());
            }
        }
        return null;
    }

    public String confirmOrder(Order order) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            order.setStatus(new StatusOrder(1l, "entregado"));

            // Actualizar el estado de la orden
            if (orderService.update(order).contains("error")) {
                return "!error";
            }

            User seller = productService.findById(order.getProduct().getId()).getOwner();
            Long price = order.getPrice().longValue();

            // Calcula la comision de openmarket es del 10%
            Long openMarketComission = (long) (price * 0.1);

            // realizar el pago a la cuenta del vendedor desde openmarket se paga desde
            // openmarket a el vendedor
            // se resta la comision
            if (!paymentFacade.processPayment(
                    new Account(0L, "openmarket", 0l),
                    new Account(0L, seller.getCard(), 0l),
                    price - openMarketComission)) {
                return "!error";
            }
            return "ok";

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

    public String registerDelivery(Delivery delivery) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            delivery.setDate(new Date());
            return deliveryService.save(delivery);
        }
        return "!error";
    }
}
