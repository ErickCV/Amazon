package sample.TDAs;

public class Stock {
    Product idProduct;
    Store idStore;
    int quantity;

    @Override
    public String toString() {
        return "Stock{" +
                "idProduct=" + idProduct +
                ", idStore=" + idStore +
                ", quantity=" + quantity +
                '}';
    }

    public Stock(Product idProduct, Store idStore, int quantity) {
        this.idProduct = idProduct;
        this.idStore = idStore;
        this.quantity = quantity;
    }

    public Stock() {
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public Store getIdStore() {
        return idStore;
    }

    public void setIdStore(Store idStore) {
        this.idStore = idStore;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
