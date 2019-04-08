package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_ciudad
{
    public int idCity,idCountry,idState;
    public String name,texto;
    ConexionBD objC;
    Connection con;

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
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

            String query = "INSERT INTO City (name,idCountry,idState)" +
                    "VALUES('"+name+"','"+idCountry+"','"+idState+"')";
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
            String query = "UPDATE City SET name='"+name+"',idCountry='"+idCountry+"',"+"idState='"+idState+"' where idCity="+idCity;
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
            String query = "DELETE FROM City WHERE idCity = "+idCity;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_ciudad> Listar()
    {
        ObservableList<modelo_ciudad> listCiudad = null;
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_ciudad objMCiudad;
            listCiudad = FXCollections.observableArrayList();
            String query= "SELECT * FROM City ORDER BY idCity";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objMCiudad = new modelo_ciudad();
                objMCiudad.setIdCity(res.getInt("idCity"));
                objMCiudad.setName(res.getString("name"));
                objMCiudad.setIdCountry(res.getInt("idCountry"));
                objMCiudad.setIdState(res.getInt("idState"));

                listCiudad.add(objMCiudad);
                texto = String.valueOf(res.getInt("idCity"))+" "+res.getString("name")+" "
                        +String.valueOf(res.getInt("idCountry"))+" "+String.valueOf(res.getInt("idCountry"))+" "
                        +String.valueOf(res.getInt("idState"));

            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listCiudad;
    }
}

