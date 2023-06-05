package co.unicauca.openmarket.server.access;

public class Factory {

    private static Factory instance;

    private Factory() {
    }

    /**
     * Clase singleton
     *
     * @return
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia
     * ICategoryRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción ICategoryRepository
     */
    public ICategoryRepository getCategoryRepository(String type) {
        ICategoryRepository result = null;

        switch (type) {
            case "default":
                result = new CategoryRepository();
                break;
        }

        return result;

    }

    /**
     * Método que crea una instancia concreta de la jerarquía IDeliveryRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IDeliveryRepository
     */
    public IDeliveryRepository getDeliveryRepository(String type) {
        IDeliveryRepository result = null;

        switch (type) {
            case "default":
                result = new DeliveryRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía IOrderRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IOrderRepository
     */
    public IOrderRepository getOrderRepository(String type) {
        IOrderRepository result = null;

        switch (type) {
            case "default":
                result = new OrderRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public IProductRepository getProductRepository(String type) {
        IProductRepository result = null;

        switch (type) {
            case "default":
                result = new ProductRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía IRolRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IRolRepository
     */
    public IRolRepository getRolRepository(String type) {
        IRolRepository result = null;

        switch (type) {
            case "default":
                result = new RolRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía
     * IShoppingCartRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IShoppingCartRepository
     */
    public IShoppingCartRepository getShoppingCartRepository(String type) {
        IShoppingCartRepository result = null;

        switch (type) {
            case "default":
                result = new ShoppingCartRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía
     * IStateProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IStateProductRepository
     */
    public IStateProductRepository getStateProductRepository(String type) {
        IStateProductRepository result = null;

        switch (type) {
            case "default":
                result = new StateProductRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía IStatusOrderRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IStatusOrderRepository
     */
    public IStatusOrderRepository getStatusOrderRepository(String type) {
        IStatusOrderRepository result = null;

        switch (type) {
            case "default":
                result = new StatusOrderRepository();
                break;
        }

        return result;
    }

    /**
     * Método que crea una instancia concreta de la jerarquía IUserRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IUserRepository
     */
    public IUserRepository getUserRepository(String type) {
        IUserRepository result = null;

        switch (type) {
            case "default":
                result = new UserRepository();
                break;
        }

        return result;
    }
}
