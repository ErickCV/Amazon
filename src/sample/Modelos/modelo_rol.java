package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_rol {
    private int idRole;
    private String name;
    private String description;
    ConexionBD objC;
    Connection con;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
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

            String query = "insert into Role values ('"+name+"','"+description+"')";

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

            String query = "update Role set name='"+name+"',description='"+description+"'"+
                    "where idRole="+idRole;

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

            String query = "delete from Role where idRole = "+idRole;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_rol> Listar() {
        ObservableList<modelo_rol> listRol = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_rol objp;
            listRol = FXCollections.observableArrayList();
            String query = "select * from Role";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_rol();
                objp.setIdRole(res.getInt("idRole"));
                objp.setName(res.getString("name"));
                objp.setDescription(res.getString("description"));
                listRol.addAll(objp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listRol;
    }
}
