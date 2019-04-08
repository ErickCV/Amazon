package sample.TDAs;

public class AddressCustomer {
    Customers idCustomer;
    Address idAddress;

    public AddressCustomer(Customers idCustomer, Address idAddress) {
        this.idCustomer = idCustomer;
        this.idAddress = idAddress;
    }

    public Customers getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customers idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }
}
