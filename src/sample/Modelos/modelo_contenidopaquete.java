package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_contenidopaquete
{
    public int idPackage,idProduct,quantity;
    public String texto,detail;
    ConexionBD objC;
    Connection con;

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO PackageContent (idPackage,detail,idProduct,quantity) " +
                    "VALUES('"+idPackage+"','"+detail+"','"+idProduct+"','"+quantity+"')";
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
            String query = "UPDATE PackageContent SET idPackage ='"+idPackage+"',detail='"+detail+"',idProduct='"+idProduct+"',"+"quantity='"+quantity+"WHERE idPackage ="+idPackage+" and detail="+detail;
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
            String query = "DELETE FROM PackageContent WHERE idPackage = "+idPackage+" and detail="+detail;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_contenidopaquete> Listar()
    {
        ObservableList<modelo_contenidopaquete> listContenidoPaquete = null;

        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_contenidopaquete objMContPaquete;
            listContenidoPaquete = FXCollections.observableArrayList();
            String query = "SELECT * FROM PackageContent ORDER BY idPackage";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next()) {
                objMContPaquete = new modelo_contenidopaquete();
                objMContPaquete.setIdPackage(res.getInt("idPackage"));
                objMContPaquete.setDetail(res.getString("detail"));
                objMContPaquete.setIdProduct(res.getInt("idProduct"));
                objMContPaquete.setQuantity(res.getInt("quantity"));

                listContenidoPaquete.add(objMContPaquete);
                texto = String.valueOf(res.getInt("idPackage")) + " " + res.getString("detail") + " "
                        + String.valueOf(res.getInt("idProduct")) + " " + String.valueOf(res.getInt("quantity"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listContenidoPaquete;
    }
}
