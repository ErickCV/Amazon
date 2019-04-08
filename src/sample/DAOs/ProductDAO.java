package sample.DAOs;

import sample.TDAs.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class ProductDAO {
    Connection conn;

    private static ObservableList<Product> data = FXCollections.observableArrayList();
    public ProductDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Product product) { data.add(product); }

    public ObservableList<Product> findAll() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Product";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Product p = null;
            while (rs.next()) {
                p = new Product(
                        rs.getInt("idProduct"),
                        rs.getString("nameProduct"),
                        rs.getInt("idCategory"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getString("image")
                );
                products.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return products;
    }




    public Product findProduct(int id) {
        Product product = new Product();
        try {
            String query = "SELECT * FROM Product where idProduct = "+id;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Product p = null;
            p = new Product(
                rs.getInt("idProduct"),
                rs.getString("nameProduct"),
                rs.getInt("idCategory"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getString("image"),
                rs.getInt("quantity")
            );
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return product;
    }


    public Boolean delete(int idProduct) {
        try {
            String query = "delete from Product where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, idProduct);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean update(Product product) {
        try {
            String query = "update Product "
                    + " set nameProduct = ?, idCategory = ?, description = ?, price = ?, image = ?"
                    + " where idProduct = ?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, product.getNameProduct());
            st.setInt(2, product.getIdCategory());
            st.setString(3, product.getDescription());
            st.setDouble(4, product.getPrice());
            st.setString(5,product.getImage());
            st.setInt(6,product.getIdProduct());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }
        return false;
    }


    public Boolean insert(Product product) {
        try {
            String query = "insert into Product"
                    + " (nameProduct, idCategory, description, price, image)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, product.getNameProduct());
            st.setInt(2, product.getIdCategory());
            st.setString(  3, product.getDescription());
            st.setDouble(4, product.getPrice());
            st.setString(5,product.getImage());
            st.execute();
            data.add(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
