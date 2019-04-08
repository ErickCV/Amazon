package sample.TDAs;

public class ListShoppingCart {
    ShoppingCart idCart,
    idCustomer;
     int detail;
    Product idProduct;
     int quantityProduct;
    Packages idPackages;
     int quantityPackage;

    public ListShoppingCart(ShoppingCart idCart, ShoppingCart idCustomer, int detail, Product idProduct,
                            int quantityProduct, Packages idPackages, int quantityPackage)
    {
        this.idCart = idCart;
        this.idCustomer = idCustomer;
        this.detail = detail;
        this.idProduct = idProduct;
        this.quantityProduct = quantityProduct;
        this.idPackages = idPackages;
        this.quantityPackage = quantityPackage;
    }

    public ShoppingCart getIdCart() {
        return idCart;
    }

    public void setIdCart(ShoppingCart idCart) {
        this.idCart = idCart;
    }

    public ShoppingCart getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(ShoppingCart idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getDetail() {
        return detail;
    }

    public void setDetail(int detail) {
        this.detail = detail;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public Packages getIdPackages() {
        return idPackages;
    }

    public void setIdPackages(Packages idPackages) {
        this.idPackages = idPackages;
    }

    public int getQuantityPackage() {
        return quantityPackage;
    }

    public void setQuantityPackage(int quantityPackage) {
        this.quantityPackage = quantityPackage;
    }
}
