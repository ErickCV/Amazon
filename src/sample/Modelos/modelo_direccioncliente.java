package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_direccioncliente
{
    public int idCustomer,idAddress;
    public String texto;
    ConexionBD objC;
    Connection con;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO AddressCustomer (idCustomer,idAddress) "+
                    "VALUES('"+idCustomer+"','"+idCustomer+"')";
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
            String query = "UPDATE AddressCustomer SET idCustomer ='"+idCustomer+"',idAddress='"+idAddress+"'where idCustomer="+idCustomer+" and idAddress="+idAddress;
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
            String query = "DELETE FROM AddressCustomer WHERE idCustomer = "+idCustomer;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_direccioncliente> Listar()
    {
        ObservableList<modelo_direccioncliente> listDireccionCliente = null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_direccioncliente objMDirCliente;
            listDireccionCliente = FXCollections.observableArrayList();
            String query= "SELECT * FROM AddressCustomer ORDER BY idCustomer";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);

            while (res.next())
            {
                objMDirCliente = new modelo_direccioncliente();
                objMDirCliente.setIdCustomer(res.getInt("idCustomer"));
                objMDirCliente.setIdAddress(res.getInt("idAddress"));

                listDireccionCliente.add(objMDirCliente);
                texto = String.valueOf(res.getInt("idCustomer"))+" "+String.valueOf(res.getInt("idAddress"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listDireccionCliente;
    }
}
