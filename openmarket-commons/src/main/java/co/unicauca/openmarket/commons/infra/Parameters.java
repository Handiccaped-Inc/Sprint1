/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.openmarket.commons.infra;

import java.util.ArrayList;
import java.util.List;

/**
 * clase parametros
 * @author ahurtado
 */
public class Parameters {

    private List<Parameter> parameters;

    /**
     * Constructor de parametros
     */
    public Parameters() {
        parameters = new ArrayList<>();
    }

    /**
     * Returns the list of parameters.
     * @return the list of parameters
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * Sets the parameters that this query is to run.
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