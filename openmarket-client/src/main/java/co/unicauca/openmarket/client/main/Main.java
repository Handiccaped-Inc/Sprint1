package co.unicauca.openmarket.client.main;

import co.unicauca.openmarket.client.access.Factory;
import co.unicauca.openmarket.client.access.IOpenMarketFacadeDeserialized;
import co.unicauca.openmarket.client.domain.service.OpenMarketFacadeService;
import co.unicauca.openmarket.client.presentation.MainGUI;

/**
 * Clase main
 */
public class Main {

    /**
     * Punto de inicio de la app
     * 
     * @param args Linea de comandos
     */
    public static void main(String[] args) {

        IOpenMarketFacadeDeserialized repository = Factory.getInstance().getRepository("default");
        OpenMarketFacadeService openMarketFacadeService = new OpenMarketFacadeService(repository);

        MainGUI instance = new MainGUI(openMarketFacadeService);
        instance.setVisible(true);
    }
}
