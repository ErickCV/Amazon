package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_direccion
{
    public int idAddress,idCity,idState,idCountry,cp;
    public String street,email,phone,nameCommerce,texto;
    ConexionBD objC;
    Connection con;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getIdState() {
        return idState;
    }

    public void setIdState(int idState) {
        this.idState = idState;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameCommerce() {
        return nameCommerce;
    }

    public void setNameCommerce(String nameCommerce) {
        this.nameCommerce = nameCommerce;
    }

    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO Address (street,email,phone,nameCommerce,idCity,idState,idCountry,cp)" +
                    "VALUES('"+street+"','"+email+"','"+phone+"','"+nameCommerce+"','"+idCity+"','"+idState+"','"+idCountry+"','"+cp+"')";
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
            String query = "UPDATE Address SET street='"+street+"',email='"+email+"',"+"phone='"+phone+"'," +
                    "nameCommerce='"+nameCommerce+ "',idCity='"+idCity+"',idState='"+idState+"',idCountry='"+idCountry+"'where idAddress="+idAddress;
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
            String query = "DELETE FROM Address WHERE idAddress = "+idAddress;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public ObservableList<modelo_direccion> Listar()
    {
        ObservableList<modelo_direccion> listDireccion = null;
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_direccion objMDreccion;
            listDireccion = FXCollections.observableArrayList();
            String query = "SELECT * FROM Address ORDER BY idAddress";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);

            while (res.next())
            {
                objMDreccion = new modelo_direccion();
                objMDreccion.setIdAddress(res.getInt("idAddress"));
                objMDreccion.setStreet(res.getString("street"));
                objMDreccion.setEmail(res.getString("email"));
                objMDreccion.setPhone(res.getString("phone"));
                objMDreccion.setNameCommerce(res.getString("nameCommerce"));
                objMDreccion.setIdCity(res.getInt("idCity"));
                objMDreccion.setIdState(res.getInt("idState"));
                objMDreccion.setIdCountry(res.getInt("idCountry"));
                objMDreccion.setCp(res.getInt("cp"));

                listDireccion.add(objMDreccion);
                texto = String.valueOf(res.getInt("idAddress"))+" "+res.getString("street")+" "
                        +res.getString("email")+" "+res.getString("phone")+" "
                        +res.getString("nameCommerce")+" "+String.valueOf(res.getInt("idCity"))+" "
                        +String.valueOf(res.getInt("idState"))+" "+String.valueOf(res.getInt("idCountry"))+" "
                        +String.valueOf(res.getInt("cp"));
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listDireccion;
    }
}
