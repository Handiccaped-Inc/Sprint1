package co.unicauca.openmarket.client.access;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import co.unicauca.openmarket.client.infra.SessionManager;
import co.unicauca.openmarket.commons.domain.Delivery;
import co.unicauca.openmarket.commons.domain.Order;
import co.unicauca.openmarket.commons.domain.Product;
import co.unicauca.openmarket.commons.domain.ShoppingCart;
import co.unicauca.openmarket.commons.domain.User;
import co.unicauca.openmarket.commons.infra.JsonError;
import co.unicauca.openmarket.commons.infra.Protocol;

public class OpenMarketFacadeAccesImplSockets implements IOpenMarketFacadeDeserialized {

    private OpenMarketSocket mySocket;
    private Gson gson;

    public OpenMarketFacadeAccesImplSockets() {
        mySocket = new OpenMarketSocket();
        gson = new Gson();
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        String jsonResponse = null;
        String requestJson = dofindUserByEmailAndPasswordRequestJson(email, password);
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }

        if (jsonResponse == null) {
            try {
                throw new Exception(
                        "No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Encontró el producto
                User user = jsonToUser(jsonResponse);
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAvailableProducts() {
        String jsonResponse = null;
        String requestJson = doFindAvailableProductsRequestJson();
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }

        if (jsonResponse == null) {
            try {
                throw new Exception(
                        "No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Encontró el producto
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
        return null;
    }

    @Override
    public List<Product> findProductByNameAndDescription(String name, String description) {
        String jsonResponse = null;
        String requestJson = doFindProductByNameAndDescriptionRequestJson(name, description);
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }

        if (jsonResponse == null) {
            try {
                throw new Exception(
                        "No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Encontró el producto
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
        return null;
    }

    @Override
    public boolean buyProduct(Product product) {
        String requestJson = doBuyProductRequestJson(product);
        return validjsonResponse(requestJson);
    }

    @Override
    public boolean addProductToTheShoppingCart(Product product, Long quantity) {
        String requestJson = doAddProductToTheShoppingCartRequestJson(product, quantity);
        return validjsonResponse(requestJson);
    }

    @Override
    public List<ShoppingCart> getShoppingCart() {
        String jsonResponse = null;
        String requestJson = doGetShoppingCartRequestJson();
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }

        if (jsonResponse == null) {
            try {
                throw new Exception(
                        "No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Encontró el producto
                List<ShoppingCart> shoppingcarts = jsonToShoppingCartList(jsonResponse);
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return shoppingcarts;
            }
        }
        return null;
    }

    @Override
    public boolean buyShoppingCart() {
        String requestJson = doBuyShoppingCartRequestJson();
        return validjsonResponse(requestJson);
    }

    @Override
    public boolean deleteShoppingCart() {
        String requestJson = doDeleteShoppingCartRequestJson();
        return validjsonResponse(requestJson);
    }

    @Override
    public boolean saveProduct(Product product) {
        String requestJson = doSaveProductRequestJson(product);
        return validjsonResponse(requestJson);
    }

    @Override
    public boolean updateProduct(Product product) {
        String requestJson = doUpdateProductRequestJson(product);
        return validjsonResponse(requestJson);
    }

    @Override
    public List<Product> getOwnProducts() {
        String jsonResponse = null;
        String requestJson = doGetOwnProductsRequestJson();
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }

        if (jsonResponse == null) {
            try {
                throw new Exception(
                        "No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Encontró el producto
                List<Product> products = jsonToProductList(jsonResponse);
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return products;
            }
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        String jsonResponse = null;
        String requestJson = doGetOrdersRequestJson();
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }

        if (jsonResponse == null) {
            try {
                throw new Exception(
                        "No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Encontró el producto
                List<Order> orders = jsonToOrderList(jsonResponse);
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return orders;
            }
        }
        return null;
    }

    @Override
    public boolean confirmOrder(Order order) {
        String requestJson = doConfirmOrderRequestJson(order);
        return validjsonResponse(requestJson);
    }

    @Override
    public boolean qualificateOrder(Order order, Long qualification) {
        String requestJson = doQualificateOrderRequestJson(order, qualification);
        return validjsonResponse(requestJson);
    }

    @Override
    public boolean registerDelivery(Delivery delivery) {
        String requestJson = doRegisterDeliveryRequestJson(delivery);
        return validjsonResponse(requestJson);
    }

    private boolean validjsonResponse(String requestJson) {
        String jsonResponse = null;
        System.out.println(requestJson);

        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();

        } catch (IOException ex) {
            Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE,
                    "No hubo conexión con el servidor", ex);
        }
        if (jsonResponse == null) {
            try {
                throw new Exception("No se pudo conectar con el servidor");
            } catch (Exception ex) {
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            if (jsonResponse.contains("error")) {
                // Devolvió algún error
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                try {
                    throw new Exception(extractMessages(jsonResponse));
                } catch (Exception ex) {
                    Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // Agregó correctamente, devuelve true
                Logger.getLogger(OpenMarketFacadeAccesImplSockets.class.getName()).log(Level.INFO,
                        "Lo que va en el JSon: (" + jsonResponse.toString() + ")");
                return true;
            }
        }
        return false;
    }

    /**
     * Extra los mensajes de la lista de errores
     *
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }

    private String dofindUserByEmailAndPasswordRequestJson(String email, String password) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonEmail = gson.toJson(email);
        String jsonPassword = gson.toJson(password);

        Protocol protocol = new Protocol();
        protocol.setAction("findUserByEmailAndPassword");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("email", jsonEmail);
        protocol.addParameter("password", jsonPassword);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doBuyProductRequestJson(Product product) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonProduct = gson.toJson(product);

        Protocol protocol = new Protocol();
        protocol.setAction("buyProduct");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("product", jsonProduct);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doAddProductToTheShoppingCartRequestJson(Product product, Long quantity) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonProduct = gson.toJson(product);
        String jsonQuantity = gson.toJson(quantity);

        Protocol protocol = new Protocol();
        protocol.setAction("addProductToShoppingCart");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("product", jsonProduct);
        protocol.addParameter("quantity", jsonQuantity);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doBuyShoppingCartRequestJson() {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);

        Protocol protocol = new Protocol();
        protocol.setAction("buyShoppingCart");
        protocol.addParameter("user", jsonUser);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doDeleteShoppingCartRequestJson() {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);

        Protocol protocol = new Protocol();
        protocol.setAction("deleteShoppingCart");
        protocol.addParameter("user", jsonUser);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    /**
     * Crea la solicitud json de creación del producto para ser enviado por el
     * socket
     *
     * @param Product objeto customer
     * @return devulve algo como:
     *         {"resource":"product","action":"get","parameters":[{"productId":"1","name":"xxx"}],
     *         [{"productId":"1","name":"xxx"}] etc...}
     */
    private String doSaveProductRequestJson(Product product) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonProduct = gson.toJson(product);

        Protocol protocol = new Protocol();
        protocol.setAction("saveProduct");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("product", jsonProduct);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doUpdateProductRequestJson(Product product) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonProduct = gson.toJson(product);

        Protocol protocol = new Protocol();
        protocol.setAction("updateProduct");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("product", jsonProduct);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doConfirmOrderRequestJson(Order order) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonOrder = gson.toJson(order);

        Protocol protocol = new Protocol();
        protocol.setAction("confirmOrder");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("order", jsonOrder);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doQualificateOrderRequestJson(Order order, Long qualification) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonOrder = gson.toJson(order);
        String jsonQualification = gson.toJson(qualification);

        Protocol protocol = new Protocol();
        protocol.setAction("qualificateOrder");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("order", jsonOrder);
        protocol.addParameter("qualification", jsonQualification);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doRegisterDeliveryRequestJson(Delivery delivery) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonDelivery = gson.toJson(delivery);

        Protocol protocol = new Protocol();
        protocol.setAction("registerDelivery");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("delivery", jsonDelivery);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doFindAvailableProductsRequestJson() {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);

        Protocol protocol = new Protocol();
        protocol.setAction("findAvailableProducts");
        protocol.addParameter("user", jsonUser);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doFindProductByNameAndDescriptionRequestJson(String name, String description) {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);
        String jsonName = gson.toJson(name);
        String jsonDescription = gson.toJson(description);

        Protocol protocol = new Protocol();
        protocol.setAction("findProductByNameAndDescription");
        protocol.addParameter("user", jsonUser);
        protocol.addParameter("name", jsonName);
        protocol.addParameter("description", jsonDescription);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doGetShoppingCartRequestJson() {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);

        Protocol protocol = new Protocol();
        protocol.setAction("getShoppingCart");
        protocol.addParameter("user", jsonUser);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doGetOwnProductsRequestJson() {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);

        Protocol protocol = new Protocol();
        protocol.setAction("getOwnProductst");
        protocol.addParameter("user", jsonUser);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private String doGetOrdersRequestJson() {

        User usuario = SessionManager.getInstance().getUser();
        String jsonUser = gson.toJson(usuario);

        Protocol protocol = new Protocol();
        protocol.setAction("getOrders");
        protocol.addParameter("user", jsonUser);

        String requestJson = gson.toJson(protocol);
        return requestJson;
    }

    private User jsonToUser(String jsonUser) {
        Gson gson = new Gson();
        User user = gson.fromJson(jsonUser, User.class);
        return user;
    }

    private List<Product> jsonToProductList(String jsonProductList) {
        Gson gson = new Gson();
        java.lang.reflect.Type productListType = new TypeToken<List<Product>>() {
        }.getType();
        List<Product> productList = gson.fromJson(jsonProductList, productListType);
        return productList;
    }

    private List<ShoppingCart> jsonToShoppingCartList(String jsonShoppingCarList) {
        Gson gson = new Gson();
        java.lang.reflect.Type ShoppingCartListType = new TypeToken<List<ShoppingCart>>() {
        }.getType();
        List<ShoppingCart> ShoppingCartListTypeList = gson.fromJson(jsonShoppingCarList, ShoppingCartListType);
        return ShoppingCartListTypeList;
    }

    private List<Order> jsonToOrderList(String jsonOrderList) {
        Gson gson = new Gson();
        java.lang.reflect.Type OrderTypeListType = new TypeToken<List<Order>>() {
        }.getType();
        List<Order> OrderListTypeList = gson.fromJson(jsonOrderList, OrderTypeListType);
        return OrderListTypeList;
    }
}
