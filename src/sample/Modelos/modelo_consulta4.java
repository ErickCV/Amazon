package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelo_consulta4 {
    public String nameUser,nameProduct,nameCustomer;
    private float total;
    private Date fecha;
   public  String name = "";
    ConexionBD objC;
    Connection con;

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ObservableList<modelo_consulta4> Listar(String name)//de modelos
    {
        this.name = name;
        ObservableList<modelo_consulta4> listConsulta4=null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_consulta4 objA;
            System.out.println("aqui va: "+name);
            listConsulta4 = FXCollections.observableArrayList();
            String query= "select u.userName, c.name customerName, p.nameProduct productName, s.date saleDate, s.total saleTotal" +
                    "                          from Users u inner join Sale s on u.idUser = s.idUser" +
                    "                                        inner join ShoppingCart sc on s.idCustomer = sc.idCustomer and s.idCart = sc.idCart" +
                    "                                        inner join Customers c on c.idCustomer = sc.idCustomer" +
                    "                                        inner join ListShoppingCart lsc on sc.idCustomer = lsc.idCustomer and sc.idCart = lsc.idCart" +
                    "                                        inner join Product p on p.idProduct = lsc.idProduct"+
                    "                          where c.name = '"+name+"'";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objA = new modelo_consulta4();//de modelos
                objA.setNameUser(res.getString("userName"));
                objA.setNameCustomer(res.getString("customerName"));
                objA.setNameProduct(res.getString("productName"));
                objA.setFecha(res.getDate("saleDate"));
                objA.setTotal(res.getFloat("saleTotal"));

                listConsulta4.add(objA);

            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listConsulta4;
    }
}
