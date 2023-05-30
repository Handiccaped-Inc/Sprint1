package co.unicauca.openmarket.commons.infra;

/**
 * Parametro.
 * Lo usa la clase Protocol
 * 
 * @author Libardo, Julio
 */
public class Parameter {

    private String name;
    private String value;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Parameter() {

    }

    /**
     * Returns the name of this entity. This is the entity's name in the form of a
     * java. lang. String object.
     * 
     * 
     * @return the entity's name in the form of a java. lang. String object or null
     *         if there is no
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the resource. This is used to distinguish resources from
     * resource types that are in a resource group.
     * 
     * @param name - the name of the resource to be set as
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the value of this parameter. This method is called by #setValue (
     * java. lang. String ) when it is asked to set the parameter's value.
     * 
     * 
     * @return the value of this parameter or null if there is no value or an error
     *         occurred while setting the parameter
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of this parameter. This is used to determine the type of
     * parameter that will be used when parsing the parameter's text.
     * 
     * @param value - The value of this parameter as a string ( null if not set
     */
    public void setValue(String value) {
        this.value = value;
    }

}