package sample.TDAs;

public class Product {
     int idProduct;
    String nameProduct;
     int idCategory;
    String description;
     double price;
    String image ;
    int quantity;

    public Product() {
    }

    @Override
    public String toString() {
        return nameProduct ;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(int idProduct, String nameProduct, int idCategory, String description, double price, String image) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.idCategory = idCategory;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Product(int idProduct, String nameProduct, int idCategory, String description, double price, String image, int quantity) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.idCategory = idCategory;
        this.description = description;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
