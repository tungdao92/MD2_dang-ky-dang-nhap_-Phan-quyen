package rikkei.academy.model;

import java.io.Serializable;

public class Role implements Serializable {
    int id;
    RoleName roleName;

    public Role() {
    }

    public Role(int id, RoleName name) {
        this.id = id;
        this.roleName = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + roleName +
                '}';
    }
}
