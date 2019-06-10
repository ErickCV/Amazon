package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.datatype.DatatypeConfigurationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelo_consulta {
    public String customerName, name, pass;
            private int idSale,idInvoice;
    private float total;
    private Date date, dateInvoice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        String nombre = this.name;
        ObservableList<modelo_consulta> listUsuarios=null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            System.out.println("nombre de condicion: "+nombre);
            modelo_consulta objA;
            listUsuarios = FXCollections.observableArrayList();
            String query= "select c.name customername, s.idSale, s.total,s.date saleDate, i.idInvoice, i.dateInvoice invoiceDate" +
                    "       from Customers c inner join Sale s on c.idCustomer = s.idCustomer" +
                    "                    inner join invoice i on c.idCustomer = i.idcustomer"+
                    "        where c.name = "+nombre;

            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objA = new modelo_consulta();//de modelos
                objA.setCustomerName(res.getString("customerName"));
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
