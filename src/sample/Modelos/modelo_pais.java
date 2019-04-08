package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_pais {
    private int idCountry;
    private String name;
    ConexionBD objC;
    Connection con;

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Insertar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "insert into Country values ('"+idCountry+"','"+name+"')";

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

            String query = "update Country set name='"+name+"' " +
                    "where idCountry="+idCountry;

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

            String query = "delete from Country where idCountry = "+idCountry;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_pais> Listar() {
        ObservableList<modelo_pais> listpais = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_pais objp;
            listpais = FXCollections.observableArrayList();
            String query = "select * from Country";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_pais();
                objp.setIdCountry((res.getInt("idCountry")));
                objp.setName((res.getString("name")));
                listpais.addAll(objp);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return listpais;
    }
}
