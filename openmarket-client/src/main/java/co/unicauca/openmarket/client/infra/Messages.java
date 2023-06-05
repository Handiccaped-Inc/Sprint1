package co.unicauca.openmarket.client.infra;

import javax.swing.JOptionPane;

/**
 * Clase Messages
 * @author Libardo Pantoja, Julio A. Hurtado
 */
public class Messages {

    /**
     * Muestra un mensaje
     * @param message mensaje
     * @param title titulo
     */
    public static void showMessageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje de confirmacion y retorna la respuesta
     * @param message mensaje
     * @param title titulo
     * @return int con respuesta
     */
    public static int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
