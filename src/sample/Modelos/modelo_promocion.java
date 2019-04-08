package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class modelo_promocion {
private int idPromo;
private String name;
private Date datePromo;
ConexionBD objC;
Connection con;

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDatePromo() {
        return datePromo;
    }

    public void setDatePromo(Date datePromo) {
        this.datePromo = datePromo;
    }

    public void Insert()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "insert into Promotion values ('"+name+"','"+datePromo+"')";

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

            String query = "update Promotion set name='"+name+"',datePromo='"+datePromo+"'"+
                    "where idPromo="+idPromo;

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

            String query = "delete from Promotion where idPromo = "+idPromo;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_promocion> Listar() {
        ObservableList<modelo_promocion> listPromocion = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_promocion objp;
            listPromocion = FXCollections.observableArrayList();
            String query = "select * from Promotion";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_promocion();
                objp.setIdPromo(res.getInt("idPromo"));
                objp.setName(res.getString("name"));
                objp.setDatePromo(res.getDate("datePromo"));
                listPromocion.addAll(objp);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listPromocion;
    }
}
