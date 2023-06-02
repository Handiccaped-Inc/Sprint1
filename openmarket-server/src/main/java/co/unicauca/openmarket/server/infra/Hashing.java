package co.unicauca.openmarket.server.infra;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Clase Hashing
 */
public class Hashing {

    /** 
     * Constructor por defecto 
    */
    Hashing(){}

    /**
     * Genera un hash SHA-256 a partir de un string
     * @param input String a hashear
     * @return String con el hash
     */
    public static String getSHA256Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejo de error si no se encuentra el algoritmo de hash
            e.printStackTrace();
            return null;
        }
    }
}