package sample.TDAs;

public class ShoppingCart {
    Customers  idCustomer;
    int idCart;
    Double subTotal;

    public ShoppingCart(Customers idCustomer, int idCart, Double subTotal) {
        this.idCustomer = idCustomer;
        this.idCart = idCart;
        this.subTotal = subTotal;
    }

    public ShoppingCart() {
    }

    public Customers getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customers idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}
