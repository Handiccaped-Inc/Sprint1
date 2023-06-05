package co.unicauca.openmarket.server.domain.service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.StateProduct;
import co.unicauca.openmarket.commons.domain.StatusOrder;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.domain.service.IOpenMarketFacade;
import co.unicauca.payment.access.AccountRepository;
import co.unicauca.payment.access.TransactionRepository;
import co.unicauca.payment.domain.Account;
import co.unicauca.payment.domain.service.PaymentFacade;

/**
 * Fachada o fachada de servicios en inglés, es un patrón de diseño que provee
 */
public class OpenMarketFacade implements IOpenMarketFacade {
    IDeliveryService deliveryService;
    IOrderService orderService;
    IProductService productService;
    IShoppingCartService shoppingCartService;
    IUserService userService;
    PaymentFacade paymentFacade;
    User requester;
    Long quantity = 1L;

    /**
     * Constructor del facade. Inicializa las fachadas que se encargan de cada una
     * de las entidades
     * 
     * @param deliveryService     servicio de delivery
     * @param orderService        servicio de orden
     * @param productService      servicio de producto
     * @param shoppingCartService servicio de carrito de compras
     * @param userService         servicio de usuario
     * @param requester           usuario que realiza la solicitud
     */
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

    /**
     * Obtener el usuario que realiza la solicitud
     * 
     * @return usuario que realiza la solicitud
     */
    public User getRequester() {
        return requester;
    }

    /**
     * Asignar el usuario que realiza la solicitud
     * 
     * @param requester usuario que realiza la solicitud
     */
    public void setRequester(User requester) {
        this.requester = requester;
    }

    /**
     * Busca los productos disponibles
     * 
     * @return lista de productos disponibles
     */
    @Override
    public List<Product> findAvailableProducts() {
        return productService.findByStatus(new StateProduct(1L, "disponible"));
    }

    /**
     * Busca los productos por nombre y descripcion
     * 
     * @param name        nombre del producto
     * @param description descripcion del producto
     * @return lista de productos
     */
    @Override
    public List<Product> findProductByNameAndDescription(String name, String description) {
        return productService.findByNameAndDescription(name, description);
    }

    /**
     * Realiza la compra de un producto
     * 
     * @param product producto a comprar
     * @return mensaje de confirmacion
     */
    @Override
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

            LocalDate currentDate = LocalDate.now();
            Date date = Date.valueOf(currentDate);
            // crear la orden
            Order order = new Order(0L, requester, dbProduct, new StatusOrder(3L, "en espera"),
                    dbProduct.getPrice(),
                    date, (double) 0);
            // guardar la orden
            if (orderService.save(order).contains("error")) {
                return "!error";
            }
            // realizar el pago a la cuenta de openmarket
            if (!paymentFacade.processPayment(
                    new Account(0L, requester.getCard(), 0l),
                    new Account(0L, "openmarket", 0l),
                    order.getPrice().longValue() * quantity)) {
                // return "!error"; // Mirar bien lo de los pagos
            }
            if ((dbProduct.getStock() - quantity) < 0) {
                return "!error";
            }
            // Reducir la cantidad de productos disponibles
            dbProduct.setStock(dbProduct.getStock() - quantity);
            if (dbProduct.getStock() == 0) {
                dbProduct.setState(new StateProduct(2L, "no disponible"));
            }
            productService.update(dbProduct);

            return "ok";
        }
        return "!error";
    }

    /**
     * Agrega un producto al carrito de compras
     * 
     * @param product  Producto a agregar
     * @param quantity Cantidad del producto
     * @return mensaje de confirmacion
     */
    @Override
    public String addProductToTheShoppingCart(Product product, Long quantity) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            // encontrar el producto
            Product dbProduct = productService.findById(product.getId());

            return shoppingCartService.save(new ShoppingCart(0l, requester, dbProduct, quantity));

        }
        return "!error";
    }

    /**
     * Obtien los productos del carrito de compras del solicitante
     * 
     * @return lista de productos del carrito de compras
     */
    @Override
    public List<ShoppingCart> getShoppingCart() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            return shoppingCartService.findRepoByOwner(requester);
        }
        return null;
    }

    /**
     * Compra los productos del carrito de compras
     * 
     * @return mensaje de confirmacion
     */
    @Override
    public String buyShoppingCart() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            // encontrar los productos del carrito

            List<ShoppingCart> entries = shoppingCartService.findRepoByOwner(requester);

            if (entries.isEmpty()) {
                return "!error";
            }
            for (ShoppingCart entry : entries) {
                this.quantity = entry.getQuantity();
                String result = buyProduct(entry.getProduct());
                if (result.contains("error")) {
                    this.quantity = 1L;
                    return "!error";
                }
            }
            this.quantity = 1L;
            return "ok";
        }
        this.quantity = 1L;
        return "!error";
    }

    /**
     * Elimina los productos del carrito de compras
     * 
     * @return mensaje de confirmacion
     */
    @Override
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

    /**
     * Guarda un producto
     * 
     * @param product producto a guardar
     * @return mensaje de confirmacion
     */
    @Override
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

    /**
     * Actualiza un producto
     * 
     * @param product producto a actualizar con el estado a actualizar
     * @return mensaje de confirmacion
     */
    @Override
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

    /**
     * Obtiene los productos del solicitante
     * 
     * @return lista de productos
     */
    @Override
    public List<Product> getOwnProducts() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (requester.getRol().getName().equals("vendedor")) {
                // TODO crear metodo en el servicio para obtener los productos por el vendedor
                return productService.findByOwner(requester);
            }
        }
        return null;
    }

    /**
     * Obtiene las ordenes del solicitante en caso de ser usuario, si es vendedor
     * obtiene todas las ordenes de productos
     * 
     * @return lista de ordenes
     */
    @Override
    public List<Order> getOrders() {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            if (requester.getRol().getName().equals("repartidor")) {
                return orderService.findByState(new StatusOrder(3L, "en espera"));
            } else {
                return orderService.findByUser(requester.getId());
            }
        }
        return null;
    }

    /**
     * Confirma la recepcion de una orden, realiza el pago al vendedor y se queda
     * con la comision
     * 
     * @param order orden a confirmar
     * @return mensaje de confirmacion
     */
    @Override
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

    /**
     * Califica una orden
     * 
     * @param order         orden a calificar
     * @param qualification calificacion
     * @return mensaje de confirmacion
     */
    @Override
    public String qualificateOrder(Order order, Long qualification) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {

            order.setQualification(qualification.doubleValue());
            return orderService.update(order);

        }
        return "!error";
    }

    /**
     * registra una entrega
     * 
     * @param delivery entrega a registrar
     * @return mensaje de confirmacion
     */
    @Override
    public String registerDelivery(Delivery delivery) {
        if ((this.requester = userService.findByEmailAndPassword(requester.getEmail(),
                requester.getPassword())) != null) {
            LocalDate currentDate = LocalDate.now();
            Date date = Date.valueOf(currentDate);
            delivery.setDate(date);
            return deliveryService.save(delivery);
        }
        return "!error";
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return userService.findByEmailAndPassword(email, password);
    }
}
