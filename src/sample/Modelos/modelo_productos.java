package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.TDAs.Product;
import sample.TDAs.State;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_productos {
private int idProduct;
private String nameProduct;
private int idCategory;
private String description;
private float price;
private String image;
ConexionBD objC;
Connection con;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void Insert()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "insert into Product values ('"+nameProduct+"','"+idCategory+"','"+description+"','"+price+"','"+image+"')";

            Statement objSt = null;
            objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void Actualizar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "update Product set nameProduct='"+nameProduct+"',idCategory='"+idCategory+"',description='"+description+"',price='"+price+"',image='"+image+"'"+
                    "where idProduct="+idProduct;

            Statement objSt = null;
            objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void Borrar(){
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "delete from Product where idProduct = "+idProduct;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<modelo_productos> showSales(int idCustomer) {
        ObservableList<modelo_productos> listProductos = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_productos objp;
            listProductos = FXCollections.observableArrayList();
            String query = "exec ShowSales'"+idCustomer+"'";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_productos();
                objp.setNameProduct(res.getString("nameProduct"));
                objp.setDescription(res.getString("description"));
                objp.setPrice(res.getFloat("price"));
                objp.setImage(res.getString("image"));
                listProductos.addAll(objp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProductos;
    }

    public ObservableList<modelo_productos> Listar() {
        ObservableList<modelo_productos> listProductos = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_productos objp;
            listProductos = FXCollections.observableArrayList();
            String query = "select * from Product";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_productos();
                objp.setIdProduct(res.getInt("idProduct"));
                objp.setNameProduct(res.getString("nameProduct"));
                objp.setIdCategory(res.getInt("idCategory"));
                objp.setDescription(res.getString("description"));
                objp.setPrice(res.getFloat("price"));
                objp.setImage(res.getString("image"));
                listProductos.addAll(objp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProductos;
    }

    public Product BuscarProducto(int idproduct) throws SQLException {
        String consulta="Select * from Product where idProduct="+idproduct;
        Product product=null;
        objC=new ConexionBD();
        con=objC.getConectar();
        Statement statement=con.createStatement();
        ResultSet resultSet=statement.executeQuery(consulta);
        while (resultSet.next()){
            product=new Product();
            product.setIdProduct(resultSet.getInt("idProduct"));
            product.setNameProduct(resultSet.getString("nameProduct"));
            product.setDescription(resultSet.getString("description"));
            product.setIdCategory(resultSet.getInt("idCategory"));
            product.setPrice(resultSet.getDouble("price"));
            product.setImage(resultSet.getString("image"));
        }
        return  product;
    }
    public Product BuscarByName(String name){
        objC=new ConexionBD();
        con=objC.getConectar();
        Product product=new Product();;
        String consulta="Select * from Product where nameProduct='"+name+"'";
        try {
            Statement statement =con.createStatement();
            ResultSet resultSet=statement.executeQuery(consulta);
            while (resultSet.next()){

                product.setIdProduct(resultSet.getInt("idProduct"));
                product.setNameProduct(resultSet.getString("nameProduct"));
                product.setDescription(resultSet.getString("description"));
                product.setIdCategory(resultSet.getInt("idCategory"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImage(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}

