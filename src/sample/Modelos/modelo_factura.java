package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class modelo_factura
{
    public int idInvoice,idSale,idUser;
    public Date dateInvoice;
    public String texto;
    ConexionBD objC;
    Connection con;

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDateInvoice() {
        return dateInvoice;
    }

    public void setDateInvoice(Date dateInvoice) {
        this.dateInvoice = dateInvoice;
    }

    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO invoice (idSale,dateInvoice,idUser) " +
                    "VALUES('"+idSale+"','"+dateInvoice+"','"+idUser+"')";
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
            String query = "UPDATE invoice SET  idSale='"+idSale+"',dateInvoice='"+dateInvoice+"',"+"idUser='"+idUser+
                    "'where idInvoice="+idInvoice;
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
            String query = "DELETE FROM invoice WHERE idInvoice = "+idInvoice;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_factura> Listar()
    {
        ObservableList<modelo_factura> listFactura = null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_factura objMFactura;
            listFactura = FXCollections.observableArrayList();
            String query= "SELECT * FROM invoice ORDER BY idInvoice";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objMFactura = new modelo_factura();
                objMFactura.setIdInvoice(res.getInt("idInvoice"));
                objMFactura.setIdSale(res.getInt("idSale"));
                objMFactura.setDateInvoice(res.getDate("dateInvoice"));
                objMFactura.setIdUser(res.getInt("idUser"));

                listFactura.add(objMFactura);
                texto = String.valueOf(res.getInt("idInvoice"))+" "+String.valueOf(res.getInt("idSale"))+" "
                        +String.valueOf(res.getInt("idUser"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listFactura;
    }
}
