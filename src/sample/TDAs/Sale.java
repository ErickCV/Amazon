package sample.TDAs;

public class Sale {
     int idSale;
     ShoppingCart idCustomer, idCart;
    Users user;
    float  total;
    PaymentMethod idPayment;
    TypeSale idTypeSale;
    Store idStore;
    Promotion idPromo;
    String date;

    public Sale() {
    }

    public Sale(int idSale, ShoppingCart idCustomer, ShoppingCart idCart, Users user, float total, PaymentMethod idPayment, TypeSale idTypeSale, Store idStore, Promotion idPromo, String date) {
        this.idSale = idSale;
        this.idCustomer = idCustomer;
        this.idCart = idCart;
        this.user = user;
        this.total = total;
        this.idPayment = idPayment;
        this.idTypeSale = idTypeSale;
        this.idStore = idStore;
        this.idPromo = idPromo;
        this.date = date;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public ShoppingCart getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(ShoppingCart idCustomer) {
        this.idCustomer = idCustomer;
    }

    public ShoppingCart getIdCart() {
        return idCart;
    }

    public void setIdCart(ShoppingCart idCart) {
        this.idCart = idCart;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public PaymentMethod getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(PaymentMethod idPayment) {
        this.idPayment = idPayment;
    }

    public TypeSale getIdTypeSale() {
        return idTypeSale;
    }

    public void setIdTypeSale(TypeSale idTypeSale) {
        this.idTypeSale = idTypeSale;
    }

    public Store getIdStore() {
        return idStore;
    }

    public void setIdStore(Store idStore) {
        this.idStore = idStore;
    }

    public Promotion getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(Promotion idPromo) {
        this.idPromo = idPromo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
