package co.unicauca.openmarket.commons.domain;

import java.util.Date;

public class User {
    private Integer id;
    private Rol rol;
    private Date birthDate;
    private String email;
    private String phone;
    private String card;
    private String realName;
    private String userName;
    private String password;
    private String address;

    public User(Integer id, Rol rol, Date birthDate, String email, String phone, String card, String realName,
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

    public User() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
