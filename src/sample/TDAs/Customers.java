package sample.TDAs;

public class Customers {
    String idCustomer;
    String name,
    lastName;
    String gender;

    public Customers(String idCustomer, String name, String lastName, String gender) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Customers() {
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return  idCustomer +" "+name +" "+  lastName +" "+ gender ;
    }
}
