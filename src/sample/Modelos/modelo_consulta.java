package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.datatype.DatatypeConfigurationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelo_consulta {
    private String nameUser,Rolname;
            private int idSale,idInvoice;
    private float total;
    private Date date, dateInvoice;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getRolname() {
        return Rolname;
    }

    public void setRolname(String rolname) {
        Rolname = rolname;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateInvoice() {
        return dateInvoice;
    }

    public void setDateInvoice(Date dateInvoice) {
        this.dateInvoice = dateInvoice;
    }

    ConexionBD objC;
    Connection con;

    public ObservableList<modelo_consulta> Listar()//de modelos
    {
        ObservableList<modelo_consulta> listUsuarios=null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_consulta objA;
            listUsuarios = FXCollections.observableArrayList();
            String query= "select u.userName userName, r.name rolName, s.idSale, s.total,s.date saleDate, i.idInvoice, i.dateInvoice invoiceDate" +
                    "       from Users u inner join Sale s on u.idUser = s.idUser" +
                    "                    inner join invoice i on u.idUser = i.idUser" +
                    "                    inner join Role r on r.idRole = u.idRole";

            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objA = new modelo_consulta();//de modelos
                objA.setNameUser(res.getString("userName"));
                objA.setRolname(res.getString("rolName"));
                objA.setIdSale(res.getInt("idSale"));
                objA.setTotal(res.getFloat("total"));
                objA.setDate(res.getDate("saleDate"));
                objA.setIdInvoice(res.getInt("idInvoice"));
                objA.setDateInvoice(res.getDate("invoiceDate"));

                listUsuarios.add(objA);
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listUsuarios;
    }
}
