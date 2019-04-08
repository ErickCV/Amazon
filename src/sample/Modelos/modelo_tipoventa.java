package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_tipoventa {
    private int idTypeSale;
    private String name;
    private String description;
    ConexionBD objC;
    Connection con;

    public int getIdTypeSale() {
        return idTypeSale;
    }

    public void setIdTypeSale(int idTypeSale) {
        this.idTypeSale = idTypeSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void Insert()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "insert into TypeSale values ('"+name+"','"+description+"')";

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

            String query = "update TypeSale set name='"+name+"',description='"+description+"'"+
                    "where idTypeSale="+idTypeSale;

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

            String query = "delete from TypeSale where idTypeSale = "+idTypeSale;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_tipoventa> Listar() {
        ObservableList<modelo_tipoventa> listTipoVenta = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_tipoventa objp;
            listTipoVenta = FXCollections.observableArrayList();
            String query = "select * from TypeSale";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_tipoventa();
                objp.setIdTypeSale(res.getInt("idTypeSale"));
                objp.setName(res.getString("name"));
                objp.setDescription(res.getString("description"));
                listTipoVenta.addAll(objp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listTipoVenta;
    }
}
