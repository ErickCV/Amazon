package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class modelo_consulta3
{
    private String nameProduct;
    private int idProduct;
    ConexionBD objC;
    Connection con;

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public ObservableList<modelo_consulta3> Listar()//de modelos
    {
        ObservableList<modelo_consulta3> listConsulta3=null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_consulta3 objA;
            listConsulta3 = FXCollections.observableArrayList();
            String query= "select p.idProduct,p.nameProduct " +
                    "      from Product p " +
                    "      where not exists (select lsc.idCustomer, lsc.idCart " +
                    "                        from ListShoppingCart lsc " +
                    "                        where lsc.idProduct = p.idProduct and not exists (select sc.idCustomer, sc.idCart " +
                    "                                                                          from ShoppingCart sc " +
                    "                                                                          where not exists (select * " +
                    "                                                                                            from Sale s " +
                    "                                                                                            where  sc.idCustomer = s.idCustomer and sc.idCart = s.idCart)))";

            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objA = new modelo_consulta3();//de modelos
                objA.setIdProduct(res.getInt("idProduct"));
                objA.setNameProduct(res.getString("nameProduct"));

                listConsulta3.add(objA);
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listConsulta3;
    }
}
