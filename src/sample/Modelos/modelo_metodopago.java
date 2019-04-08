package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_metodopago {
    private int idPayment;
    private String typePayment;
    private String description;
    ConexionBD objC;
    Connection con;

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void Insertar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "insert into PaymentMethod values ('"+typePayment+"','"+description+"')";

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

            String query = "update PaymentMethod set typePayment='"+typePayment+"',decription='"+description+"' " +
                            "where idPayment="+idPayment;

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

            String query = "delete from PaymentMethod where idPayment = "+idPayment;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_metodopago> Listar()
    {
        ObservableList<modelo_metodopago> listMetodoPago = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_metodopago objmp;
            listMetodoPago = FXCollections.observableArrayList();
            String query = "select * from PaymentMethod";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next())
            {
                objmp = new modelo_metodopago();
                objmp.setIdPayment((res.getInt("idPayment")));
                objmp.setTypePayment((res.getString("typePayment")));
                objmp.setDescription(((res.getString("decription"))));
                listMetodoPago.addAll(objmp);
            }
            con.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return listMetodoPago;
    }
}
