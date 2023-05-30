package co.unicauca.openmarket.commons.domain;

public class Rol {
    private Integer rolId;
    private String rolName;

    public Rol(Integer rolId, String rolName) {
        this.rolId = rolId;
        this.rolName = rolName;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

}
