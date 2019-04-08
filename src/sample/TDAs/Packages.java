package sample.TDAs;

import javafx.collections.ObservableList;

public class Packages {
    private int idPackage;
    private String namePackage, description ;
    private double price;
    private int stock;
    private String image;


    public Packages(int idPackage, String namePackage, String description, double price, int stock, String image) {
        this.idPackage = idPackage;
        this.namePackage = namePackage;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
    }


    public Packages() {
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
