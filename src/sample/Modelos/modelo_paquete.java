package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_paquete {
    private int idPackage;
    private String namePackage;
    private String description;
    private float price;
    private int stock;
    private String image;
    ConexionBD objC;
    Connection con;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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
    public void Insertar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

                String query = "insert into Package values ('"+namePackage+"','"+description+"','"+price+"','"+stock+"','"+image+"')";

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

            String query = "update Package set namePackage='"+namePackage+"',description='"+description+"',price='"+price+"',stock='"+stock+"',image='"+image+"'"+
                    "where idPackage="+idPackage;

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

            String query = "delete from Package where idPayment = "+idPackage;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_paquete> Listar() {
        ObservableList<modelo_paquete> lispaquete = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_paquete objp;
            lispaquete = FXCollections.observableArrayList();
            String query = "select * from Package";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_paquete();
                objp.setIdPackage(res.getInt("idPackage"));
                objp.setNamePackage(res.getString("namePackage"));
                objp.setDescription(res.getString("description"));
                objp.setPrice(res.getFloat("price"));
                objp.setStock(res.getInt("stock"));
                objp.setImage(res.getString("image"));
                lispaquete.addAll(objp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return lispaquete;
    }
}
