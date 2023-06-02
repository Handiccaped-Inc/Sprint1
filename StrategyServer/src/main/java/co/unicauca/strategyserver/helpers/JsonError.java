/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.strategyserver.helpers;

/**
 * Clase JsonError
 * @author ahurtado
 */
public class JsonError {
     /**
     * Ej. 404
     */
    private String code;
    /**
     * Ej. Not_found
     */
    private String error;
    /**
     * Ej. La cédula del cliente no existe
     */
    private String message;
    /**
     * Constructor por defecto
     */
    public JsonError() {
    }
    /**
     * Constructor parametrizado
     * @param code codigo
     * @param error error
     * @param message mensaje
     */
    public JsonError(String code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    /**
     * Obtiene el codigo actual
     * @return string con codigo
     */
    public String getCode() {
        return code;
    }

    /**
     * Establece un nuevo codigo
     * @param code nuevo codigo
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene el error actual
     * @return string con error
     */
    public String getError() {
        return error;
    }

    /**
     * Establece un nuevo mensaje error
     * @param error nuevo error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Obtiene el mensaje actual
     * @return string con el mensaje
     */
    public String getMessage() {
        return message;
    }

    /**
     * Establece un nuevo mensaje
     * @param message nuevo mensaje
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
