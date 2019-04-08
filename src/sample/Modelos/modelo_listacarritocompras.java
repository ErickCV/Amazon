package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_listacarritocompras
{
    public int idCart,idCustomer,idProduct,quantityProduct,idPackage,quantityPackage;
    public String texto,detail;
    ConexionBD objC;
    Connection con;

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public int getQuantityPackage() {
        return quantityPackage;
    }

    public void setQuantityPackage(int quantityPackage) {
        this.quantityPackage = quantityPackage;
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

            String query = "INSERT INTO ListShoppingCart (idCart,idCustomer,detail,idProduct,quantityProduct,idPackage,quantityPackage) " +
                    "VALUES('"+idCart+"','"+idCustomer+"','"+detail+"','"+idProduct+"','"+quantityProduct+"','"+idPackage+"','"+quantityPackage+"')";
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
            String query = "UPDATE ListShoppingCart SET idCart ='"+idCart+"',idCustomer='"+idCustomer+"',detail='"+detail+"',"+"idProduct='"+idProduct+"'," +
                    "quantityProduct='"+quantityProduct+ "',idPackage='"+idPackage+"',quantityPackage='"+quantityPackage+"'where idCart="+idCart;
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
            String query = "DELETE FROM ListShoppingCart WHERE idCart = "+idCart;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_listacarritocompras> Listar()
    {
        ObservableList<modelo_listacarritocompras> listListaCarritoCompras = null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_listacarritocompras objMListCarCompras;
            listListaCarritoCompras = FXCollections.observableArrayList();
            String query= "SELECT * FROM ListShoppingCart ORDER BY idCart";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objMListCarCompras = new modelo_listacarritocompras();
                objMListCarCompras.setIdCart(res.getInt("idCart"));
                objMListCarCompras.setIdCustomer(res.getInt("idCustomer"));
                objMListCarCompras.setDetail(res.getString("detail"));
                objMListCarCompras.setIdProduct(res.getInt("idProduct"));
                objMListCarCompras.setQuantityProduct(res.getInt("quantityProduct"));
                objMListCarCompras.setIdPackage(res.getInt("idPackage"));
                objMListCarCompras.setQuantityPackage(res.getInt("quantityPackage"));

                listListaCarritoCompras.add(objMListCarCompras);

                texto = String.valueOf(res.getInt("idCart"))+" "+String.valueOf(res.getInt("idCustomer"))+" "
                        +res.getString("detail")+" "+String.valueOf(res.getInt("idProduct"))+" "
                        +String.valueOf(res.getInt("quantityProduct"))+" "+String.valueOf(res.getInt("idPackage"))+" "
                        +String.valueOf(res.getInt("quantityPackage"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listListaCarritoCompras;
    }
}
