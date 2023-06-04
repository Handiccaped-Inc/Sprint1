package co.unicauca.openmarket.server.infra;

import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;
import co.unicauca.openmarket.server.domain.service.OpenMarketFacade;
import co.unicauca.strategyserver.infra.ServerHandler;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Braian Rey / Arturo Restrepo Ruiz
 */
public class OpenMarketHandler extends ServerHandler {
    private Map<String, Function<Protocol, String>> actionMap;
    private OpenMarketFacade facade;
    User requester;
    Gson gson;

    /**
     * Constructor por defecto
     */
    public OpenMarketHandler() {
        gson = new Gson();
        actionMap = new HashMap<>();
        // Crear un HashMap para mapear las acciones según el recurso
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

    /**
     * Constructor parametrizado
     * 
     * @param facade OpenMarketFacade
     */
    public OpenMarketHandler(OpenMarketFacade facade) {
        this.facade = facade;
    }

    public void SetOpenMarketFacade(OpenMarketFacade facade) {
        this.facade = facade;
    }

    @Override
    public String processRequest(String requestJson) {

        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String userJson = protocolRequest.getParameters().get(0).getValue();
        User requester = gson.fromJson(userJson, User.class);
        facade.setRequester(requester);

        // Obtener la clave para el HashMap
        String key = protocolRequest.getAction();
        // Obtener el Consumer correspondiente y ejecutarlo
        Function<Protocol, String> action = actionMap.get(key);
        if (action != null) {
            return action.apply(protocolRequest);
        }
        String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
        return errorJson;
    }

    public String processFindAvailableProducts(Protocol protocolRequest) {
        List<Product> products = facade.findAvailableProducts();

        if (products == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(products);
        }
    }

    public String processFindProductByNameAndDescription(Protocol protocolRequest) {

        String name = protocolRequest.getParameters().get(1).getValue();
        String description = protocolRequest.getParameters().get(2).getValue();

        List<Product> products = facade.findProductByNameAndDescription(name, description);

        if (products == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(products);
        }
    }

    public String processBuyProduct(Protocol protocolRequest) {
        Product product = new Product();
        Long id = Long.parseLong(protocolRequest.getParameters().get(1).getValue());
        product.setId(id);

        String reply = facade.buyProduct(product);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }

    }

    public String processAddProductToShoppingCart(Protocol protocolRequest) {
        Product product = new Product();
        Long id = Long.parseLong(protocolRequest.getParameters().get(1).getValue());
        product.setId(id);
        Long quantity = Long.parseLong(protocolRequest.getParameters().get(2).getValue());

        String reply = facade.addProductToTheShoppingCart(product, quantity);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processGetShoppingCart(Protocol protocolRequest) {
        List<ShoppingCart> products = facade.getShoppingCart();

        if (products == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(products);
        }
    }

    public String processBuyShoppingCart(Protocol protocolRequest) {
        String reply = facade.buyShoppingCart();

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processDeleteShoppingCart(Protocol protocolRequest) {
        String reply = facade.deleteShoppingCart();

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processSaveProduct(Protocol protocolRequest) {
        String productJson = protocolRequest.getParameters().get(1).getValue();
        Product product = gson.fromJson(productJson, Product.class);

        String reply = facade.saveProduct(product);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processUpdateProduct(Protocol protocolRequest) {
        String productJson = protocolRequest.getParameters().get(1).getValue();
        Product product = gson.fromJson(productJson, Product.class);

        String reply = facade.updateProduct(product);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processGetOwnProducts(Protocol protocolRequest) {
        List<Product> products = facade.getOwnProducts();

        if (products == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(products);
        }
    }

    public String processGetOrders(Protocol protocolRequest) {
        List<Order> orders = facade.getOrders();
        if (orders == null) {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return objectToJSON(orders);
        }
    }

    public String processConfirmOrder(Protocol protocolRequest) {
        String orderJson = protocolRequest.getParameters().get(1).getValue();
        Order order = gson.fromJson(orderJson, Order.class);

        String reply = facade.confirmOrder(order);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processQualificateOrder(Protocol protocolRequest) {
        String orderJson = protocolRequest.getParameters().get(1).getValue();
        Order order = gson.fromJson(orderJson, Order.class);
        Long qualification = Long.parseLong(protocolRequest.getParameters().get(2).getValue());
        String reply = facade.qualificateOrder(order, qualification);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
        }
    }

    public String processRegisterDelivery(Protocol protocolRequest) {
        String deliveryJson = protocolRequest.getParameters().get(1).getValue();
        Delivery delivery = gson.fromJson(deliveryJson, Delivery.class);

        String reply = facade.registerDelivery(delivery);

        if (reply == "!error") {
            String errorJson = generateNotFoundErrorJson(protocolRequest.getResource());
            return errorJson;
        } else {
            return reply;
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
}
