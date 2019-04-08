package sample.TDAs;

public class PackageContent {
     int idPackages;
    int detail;
    Product idProduct;
    int quantity;

    public PackageContent(int idPackages, int detail, Product idProduct, int quantity) {
        this.idPackages = idPackages;
        this.detail = detail;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdPackages() {
        return idPackages;
    }

    public void setIdPackages(int idPackages) {
        this.idPackages = idPackages;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
