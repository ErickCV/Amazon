package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_estado
{
    public int idState,idCountry;
    public String name,texto;
    ConexionBD objC;
    Connection con;

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

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

    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO State (name,idCountry)" +
                    "VALUES('"+name+"','"+idCountry+"')";
            Statement objSt = con.createStatement();//ENCARGADO DE REALIZAR LA CONSULTA
            objSt.executeUpdate(query);//executeUpdate PARA ACTUALIZAR LA BD   Y EL EXECUTEQUERY SOLO PARA REALIZAR CONSULTAS
            this.Listar();
            con.close();
        }
        catch (Exception e)
        {
            e.toString();
        }
    }

    public  void Actualizar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();
            String query = "UPDATE State SET name='"+name+"',idCountry='"+idCountry+"'where idState="+idState;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        }
        catch (Exception e)
        {

        }
    }

    public void Borrar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();
            String query = "DELETE FROM State WHERE idState = "+idState;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_estado> Listar()
    {
        ObservableList<modelo_estado> listEstado = null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_estado objMEstado;
            listEstado = FXCollections.observableArrayList();
            String query= "SELECT * FROM State ORDER BY idState";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objMEstado = new modelo_estado();
                objMEstado.setIdState(res.getInt("idState"));
                objMEstado.setIdCountry(res.getInt("idCountry"));
                objMEstado.setName(res.getString("name"));

                listEstado.add(objMEstado);
                texto = String.valueOf(res.getInt("idState"))+" "+String.valueOf(res.getInt("idCountry"))+" "
                        +res.getString("name");
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listEstado;
    }
}
