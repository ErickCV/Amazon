package sample.TDAs;

public class Role {
    int idRole;
    String name,
    description;

    public Role() {
    }


    @Override
    public String toString() {
        return name ;
    }

    public Role(int idRole, String name, String description) {
        this.idRole = idRole;
        this.name = name;
        this.description = description;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
