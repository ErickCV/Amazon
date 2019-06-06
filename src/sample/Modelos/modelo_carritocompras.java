package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.TDAs.Customers;
import sample.TDAs.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_carritocompras
{

    public int idCart,cantidad;
    public Product idProduct;
    public Customers idCustomer;
    public float subTotal;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    ConexionBD objC;
    Connection con;

    public Customers getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Customers idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }


    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO ShoppingCart (idCustomer,subTotal) " +
                    "VALUES('"+idCustomer+"','"+subTotal+"')";
            Statement objSt = con.createStatement();//ENCARGADO DE REALIZAR LA CONSULTA
            objSt.executeUpdate(query);//executeUpdate PARA ACTUALIZAR LA BD   Y EL EXECUTEQUERY SOLO PARA REALIZAR CONSULTAS
            //this.Listar();
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
            String query = "UPDATE ShoppingCart SET idCustomer ='"+idCustomer+"',subTotal='"+subTotal+"' where idCart="+idCart ;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            //this.Listar();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void borrar()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();
            String query = "DELETE FROM ShoppingCart WHERE idCart = "+idCart;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            //this.Listar();
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public ObservableList<modelo_carritocompras> Listar(modelo_carritocompras modeloCarritocompras)
    {
        ObservableList<modelo_carritocompras> listCarritoCompras = null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_productos producto=null;
            modelo_carritocompras objMCarrito;
            listCarritoCompras = FXCollections.observableArrayList();
            String query= "SELECT * FROM ShoppingCart where idCuetomer="+modeloCarritocompras.getIdCart()+" and idCustomer="+modeloCarritocompras.getIdCustomer().getIdCustomer();
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);

            while (res.next())
            {
                objMCarrito = new modelo_carritocompras();
                producto=new modelo_productos();
                objMCarrito.setIdCustomer(new modelo_cliente().BuscarCustomer(res.getInt("idCustomer")));
                objMCarrito.setIdCart(res.getInt("idCart"));
                objMCarrito.setSubTotal(res.getFloat("subTotal"));
                objMCarrito.setIdProduct(producto.BuscarProducto(res.getInt("idProduct")));
                objMCarrito.setCantidad(res.getInt("cantidad"));
                listCarritoCompras.add(objMCarrito);
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listCarritoCompras;
    }



}

