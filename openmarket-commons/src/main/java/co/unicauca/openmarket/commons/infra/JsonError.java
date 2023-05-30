package co.unicauca.openmarket.commons.infra;

/**
 * Representa un error
 *
 * @author Libardo, Julio
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
     * 
     * @param code
     * @param error
     * @param message
     */
    public JsonError(String code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    /**
     * The getCode function returns the code of a given JsonError.
     * 
     *
     *
     * @return The code of the JsonError
     *
     */
    public String getCode() {
        return code;
    }

    /**
     * The setCode function sets the code of a given object to the value of its
     * parameter.
     * 
     *
     * @param String code Set the code of the object
     *
     * @return Nothing, so it's return type is void
     *
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * The getError function returns the error message that is stored in the error
     * object.
     * 
     *
     *
     * @return The error message
     *
     */
    public String getError() {
        return error;
    }

    /**
     * The setError function sets the error message to be displayed in the event of
     * an error.
     * 
     *
     * @param String error Set the error message
     *
     * @return Void
     *
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * The getMessage function returns the message of the json.
     * 
     *
     *
     * @return The message variable
     *
     */
    public String getMessage() {
        return message;
    }

    /**
     * The setMessage function sets the message variable to a new value.
     * 
     *
     * @param String message Set the message instance variable
     *
     * @return Nothing, so it should be void
     *
     */
    public void setMessage(String message) {
        this.message = message;
    }

}