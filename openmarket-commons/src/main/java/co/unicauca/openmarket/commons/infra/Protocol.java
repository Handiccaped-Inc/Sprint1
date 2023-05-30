package co.unicauca.openmarket.commons.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * Protocolo de comunicación entre la aplicación cliente y el servidor
 *
 * @author Libardo, Julio
 */
public class Protocol {

    private String resource;
    private String action;
    private List<Parameter> parameters;

    public Protocol() {
        parameters = new ArrayList<>();
    }

    /**
     * Returns the resource to use for this resource.
     * 
     * @return the resource to use for this resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the resource
     * 
     * @param resource - the resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Returns the action
     * 
     * 
     * @return the action to be performed on the data source or null if there is no
     *         action to be performed on
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action to be performed when the button is pressed. Default is " save
     * ". Note that this is a string that will be passed to the button's onAction
     * method as the value of the button
     * 
     * @param action - the action to be
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Returns the list of parameters. This is a copy of the list returned by
     * #getParameters ().
     * 
     * 
     * @return the list of parameters for this method or null if there are no
     *         parameters for this method or if the list is
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters
     * 
     * @param parameters - a list of parameters
     */
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    /**
     * Adds a parameter to the request.
     * 
     * @param name  - The name of the parameter.
     * @param value - The value of the parameter.
     */
    public void addParameter(String name, String value) {
        parameters.add(new Parameter(name, value));
    }

}