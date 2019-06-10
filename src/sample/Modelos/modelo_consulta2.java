package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelo_consulta2 {
    private String nameUser,nameCustomer,nameProduct;
    private Date dateSale;
    private float total;
    ConexionBD objC;
    Connection con;


    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Date getDateSale() {
        return dateSale;
    }

    public void setDateSale(Date dateSale) {
        this.dateSale = dateSale;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ObservableList<modelo_consulta2> Listar()//de modelos
    {
        ObservableList<modelo_consulta2> listConsulta2=null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_consulta2 objA;
            listConsulta2 = FXCollections.observableArrayList();
            String query= "select c.name customerName, p.nameProduct productName, s.date saleDate, s.total saleTotal" +
                    "       from Customers c inner join Sale s on c.idCustomer = s.idCustomer" +
                    "                    inner join ShoppingCart sc on s.idCustomer = sc.idCustomer and s.idCart = sc.idCart and c.idCustomer = sc.idCustomer" +
                    "                    inner join Product p on p.idProduct = sc.idProduct";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objA = new modelo_consulta2();//de modelos
                objA.setNameCustomer(res.getString("customerName"));
                objA.setNameProduct(res.getString("productName"));
                objA.setDateSale(res.getDate("saleDate"));
                objA.setTotal(res.getFloat("saleTotal"));

                listConsulta2.add(objA);

            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listConsulta2;
    }

}
