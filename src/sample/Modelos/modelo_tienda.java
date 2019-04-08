package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_tienda
{
    private int idStore;
    private int idAddress;
    ConexionBD objC;
    Connection con;

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public ObservableList<modelo_tienda> Listar()
    {
        ObservableList<modelo_tienda> listTienda = null;
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_tienda objp;
            listTienda = FXCollections.observableArrayList();
            String query = "select * from Store";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next())
            {
                objp = new modelo_tienda();
                objp.setIdStore(res.getInt("idStore"));
                objp.setIdAddress(res.getInt("idAddress"));
                listTienda.addAll(objp);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listTienda;
    }

    public void Borrar(){
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "delete from Store where idAddress = "+idAddress+" and idStore="+idStore;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
