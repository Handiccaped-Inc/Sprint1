package co.unicauca.openmarket.commons.domain;

import java.util.Date;

/**
 * Objeto usuario
 */
public class User {
    private Long id;
    private Rol rol;
    private Date birthDate;
    private String email;
    private String phone;
    private String card;
    private String realName;
    private String userName;
    private String password;
    private String address;

    /**
     * Constructor del usuario
     * @param id Identificador
     * @param rol Objeto rol del usuario
     * @param birthDate Fecha de nacimiento
     * @param email Correo
     * @param phone Numero de telefono
     * @param card Numero de la tarjeta
     * @param realName Nombre real
     * @param userName Nombre de usuario
     * @param password Contraseña
     * @param address Direccion
     */
    public User(Long id, Rol rol, Date birthDate, String email, String phone, String card, String realName,
            String userName, String password, String address) {
        this.id = id;
        this.rol = rol;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.card = card;
        this.realName = realName;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

    /**
     * Construccion del usuario sin parametros
     */
    public User() {

    }

    /**
     * Obtiene el id actual
     * @return long con id
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece un nuevo id
     * @param id nuevo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el rol actual del usuario
     * @return objeto rol actual
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * Establece un nuevo rol
     * @param rol nuevo rol
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * Obtiene la fecha de nacimiento actual
     * @return date con la fecha de nacimiento
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Establece una nueva fecha de nacimiento
     * @param birthDate nueva fecha de nacimiento
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Obtiene el correo actual del usuario
     * @return string con el correo
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece un nuevo correo al usuario
     * @param email nuevo correo
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el telefono del usuario
     * @return string con el numero de telefono
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece un nuevo numero de telefono
     * @param phone nuevo numero de telefono
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtiene el numero de tarjeta
     * @return string con numero de tarjeta
     */
    public String getCard() {
        return card;
    }

    /**
     * Establece un nuevo numero de tarjeta
     * @param card nuevo numero de tarjeta
     */
    public void setCard(String card) {
        this.card = card;
    }

    /**
     * Obtiene el nombre real del usuario
     * @return string con el nombre
     */
    public String getRealName() {
        return realName;
    }

    /**
     * Establece un nuevo nombre real al usuario
     * @param realName nuevo nombre
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Obtiene el nombre "avatar" del usuario
     * @return string con el nombre 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Establece un nuevo nombre "avatar" de usuario
     * @param userName nuevo nombre
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return string con la contraseña
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece una nueva contraseña
     * @param password nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la direccion del usuario
     * @return string con la direccion
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece un nuevo correo
     * @param address nuevo correo
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
