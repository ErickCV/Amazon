package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_stock {
    private int idProduct;
    private int idStore;
    private int quantity;
    ConexionBD objC;
    Connection con;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdStore() {
        return idProduct;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void Insert()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "insert into Stock values ('"+quantity+"')";

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

            String query = "update Stock set quantity='"+quantity+"'"+
                    "where idProduct="+idProduct+"and idStore="+idStore;

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

            String query = "delete from Stock where idProduct = "+idProduct+" and idStore="+idStore;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_stock> Listar() {
        ObservableList<modelo_stock> listStock = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_stock objp;
            listStock = FXCollections.observableArrayList();
            String query = "select * from Stock";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_stock();
                objp.setIdProduct(res.getInt("idProduct"));
                objp.setIdStore(res.getInt("idStore"));
                objp.setQuantity(res.getInt("quantity"));
                listStock.addAll(objp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listStock;
    }
}
