package co.unicauca.openmarket.client.main;

import co.unicauca.openmarket.client.presentation.MainGUI;

/**
 * Clase main
 */
public class Main {

    /**
     * Punto de inicio de la app
     * @param args Linea de comandos
     */
    public static void main(String[] args) {
        MainGUI instance = new MainGUI();
        instance.setVisible(true);
    }
}
