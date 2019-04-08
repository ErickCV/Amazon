package sample.TDAs;

public class Store {
     int idStore;
    Address idAddress;
    String street;

    public Store(int idStore, Address idAddress) {
        this.idStore = idStore;
        this.idAddress = idAddress;
    }

    public Store() {
    }

    public Store(int idStore, String street) {
        this.idStore = idStore;
        this.street = street;
    }

    @Override
    public String toString() {
        return  street;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public Address getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Address idAddress) {
        this.idAddress = idAddress;
    }
}
