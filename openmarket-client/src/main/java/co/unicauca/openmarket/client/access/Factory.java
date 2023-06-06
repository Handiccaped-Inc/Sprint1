package co.unicauca.openmarket.client.access;

/**
 * Clase factory
 */
public class Factory {

    private static Factory instance;

    /**
     * Constructor default
     */
    private Factory() {
    }

    /**
     * Clase singleton
     * @return Instancia actual de factory
     */
    public static Factory getInstance() {

        if (instance == null) {
            instance = new Factory();
        }
        return instance;

    }

    /**
     * Método que crea una instancia concreta de la jerarquia
     * IOpenMarketFacadeDeserialized
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IOpenMarketFacadeDeserialized
     */
    public IOpenMarketFacadeDeserialized getRepository(String type) {

        IOpenMarketFacadeDeserialized result = null;

        switch (type) {
            case "default":
                result = new OpenMarketFacadeAccesImplSockets();
                break;
        }

        return result;

    }
}
