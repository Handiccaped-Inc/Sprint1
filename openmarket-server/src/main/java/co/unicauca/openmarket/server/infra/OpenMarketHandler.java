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

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class OpenMarketHandler extends ServerHandler {

    private Map<String, Consumer<Protocol>> actionMap;
    private OpenMarketFacade facade;
    IDeliveryService deliveryService;
    IOrderService orderService;
    IProductService productService;
    IShoppingCartService shoppingCartService;
    IUserService userService;
    User requester;

    public OpenMarketHandler(IDeliveryService deliveryService, IOrderService orderService,
    IProductService productService, IShoppingCartService shoppingCartService, IUserService userService) {
        actionMap = new HashMap<>();
        facade = new OpenMarketFacade(deliveryService, orderService, productService, shoppingCartService, userService, requester);
        this.deliveryService = deliveryService;
        this.orderService = orderService;
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @Override
    public String processRequest(String requestJson) {
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response = "";

        // Crear un HashMap para mapear las acciones según el recurso
        

        actionMap.put("findAvailableProducts", this::processFindAvailableProducts);
        actionMap.put("buyProduct", this::processBuyProduct);
        actionMap.put("addProductToTheShoppingCart", this::processAddProductToTheShoppingCart); 
        actionMap.put("getShoppingCart", this::processGetShoppingCart);
        actionMap.put("buyShoppingCart", this::processBuyShoppingCart);
        actionMap.put("deleteShoppingCart", this::processDeleteShoppingCart); 
        actionMap.put("saveProduct", this::processSaveProduct);
        actionMap.put("getOwnProducts", this::processGetOwnProducts);
        actionMap.put("getOrders", this::processGetOrders);
        actionMap.put("confirmOrder", this::processConfirmOrder);
        actionMap.put("qualificateOrder", this::processQualificateOrder);
        actionMap.put("registerDelivery", this::processRegisterDelivery);


        // Obtener la clave para el HashMap
        String key = protocolRequest.getResource() + "." + protocolRequest.getAction();

        // Obtener el Consumer correspondiente y ejecutarlo
        Consumer<Protocol> action = actionMap.get(key);
        if (action != null) {
            action.accept(protocolRequest);
        }

        return response;
    }

    /**
     * Proceso para encontrar el usuario con correo y contraseña
     * @param protocolRequest Pedido
     * @return String de la respuesta
     */
    private String processFindAvailableProducts(Protocol protocolRequest) {
        List<Product> procuts = facade.findAvailableProducts();
        if (procuts == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(procuts);
        }
    }

    private String processBuyProduct(Protocol protocolRequest) {
        Product product = new Product();
        product = productService.findById(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        
        if (product == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return facade.buyProduct(product);
        }
    }

    private String processAddProductToTheShoppingCart(Protocol protocolRequest){
        Product product = new Product();
        product = productService.findById(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        long quantity = Long.parseLong(protocolRequest.getParameters().get(1).getValue());

        if (product == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return facade.addProductToTheShoppingCart(product, quantity);
        }
    }

    private String processGetShoppingCart(Protocol protocolRequest){
        List<ShoppingCart> Carritos = facade.getShoppingCart();

        if (Carritos == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(Carritos);
        }
    }

    private String processBuyShoppingCart(Protocol protocolRequest){
        String result = facade.buyShoppingCart();
        if (result.equals("!error")) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return result;
        }
    }

    private String processDeleteShoppingCart(Protocol protocolRequest){
        String result = facade.deleteShoppingCart();
        if (result.equals("!error")) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return result;
        }
    }

    private String processSaveProduct(Protocol protocolRequest){
        Product product = productService.findById(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        if (product == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return facade.saveProduct(product);
        }
    }

    private String processGetOwnProducts(Protocol protocolRequest){
        List<Product> products = facade.getOwnProducts();
        if (products == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(products);
        }
    }

    private String processGetOrders(Protocol protocolRequest){
        List<Order> orders = facade.getOrders();
        if (orders == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(orders);
        }
    }


    //!!!//

    public String processConfirmOrder(Protocol protocolRequest){
        if (orders == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(orders);
        }
    }

    public String processQualificateOrder(Protocol protocolRequest){
        if (orders == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(orders);
        }
    }
    
    public String processRegisterDelivery(Protocol protocolRequest) {
        Delivery delivery = new Delivery();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        delivery.setId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        delivery.setOrder(orderService.findById(Long.parseLong(protocolRequest.getParameters().get(1).getValue())));
        delivery.setDeliveryMan(userService.findById(Long.parseLong(protocolRequest.getParameters().get(2).getValue())));
        delivery.setReceiver(userService.findById(Long.parseLong(protocolRequest.getParameters().get(3).getValue())));
        delivery.setDate(dateFormat.parse(protocolRequest.getParameters().get(4).getValue())); //Puede cambiarse a fecha actual
    
        if (delivery.getOrder() == null || delivery.getDeliveryMan() == null || delivery.getReceiver() == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return facade.registerDelivery(delivery);
        }
    }

    private String generateNotFoundErrorJson(String object) {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("El " + object + " No Existe");
        errors.add(error);

        Gson gson = new Gson();
        return gson.toJson(errors);
    }

    public void setFacade(OpenMarketFacade facade) {
        this.facade = facade;
    }

    public void setDeliveryService(IDeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void setOrderService(IOrderService orderService) {
        this.orderService = orderService;
    }

    public void setProductService(IProductService productService) {
        this.productService = productService;
    }

    public void setShoppingCartService(IShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }
    
}
