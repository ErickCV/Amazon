package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class modelo_venta {
    private int idSale,idCustomer,idCart,idUser,idPayment,idTypeSale,idStore,idPromo;
    private double total;
    private Date date;
    ConexionBD objC;
    Connection con;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdSale() {
        return idSale;
    }

    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public int getIdTypeSale() {
        return idTypeSale;
    }

    public void setIdTypeSale(int idTypeSale) {
        this.idTypeSale = idTypeSale;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void Insert()
    {
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "call insertventa("+this.idCart+","+this.idCustomer+","+this.idPayment+")";

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

            String query = "update Sale set idCustomer='"+idCustomer+"',idCart'"+idCart+"',idUser='"+idUser+"',total='"+total+"',idPayment='"+idPayment+"',idTypeSale='"+idTypeSale+"',idStore='"+idStore+"',idPromo='"+idPromo+"',date='"+date+"'"+
                    "where idSale="+idSale;

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

            String query = "delete from Sale where idSale = "+idSale;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_venta> Listar() {
        ObservableList<modelo_venta> listVenta = null;
        try{
            objC = new ConexionBD();
            con = objC.getConectar();
            modelo_venta objp;
            listVenta = FXCollections.observableArrayList();
            String query = "select * from Sale";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while(res.next()){
                objp = new modelo_venta();
                objp.setIdSale(res.getInt("idSale"));
                objp.setIdCustomer(res.getInt("idCustomer"));
                objp.setIdCart(res.getInt("idCart"));
                objp.setIdUser(res.getInt("idUser"));
                objp.setTotal(res.getDouble("total"));
                objp.setIdPayment(res.getInt("idPayment"));
                objp.setIdTypeSale(res.getInt("idTypeSale"));
                objp.setIdPromo(res.getInt("idPromo"));
                objp.setIdStore(res.getInt("idStore"));
                objp.setDate(res.getDate("date"));
                listVenta.addAll(objp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listVenta;
    }
}
