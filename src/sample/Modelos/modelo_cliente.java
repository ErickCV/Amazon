package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import sample.TDAs.Customers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_cliente
{
    public int idCustomer;
    public String texto,name,lastName,gender,clave;
    ConexionBD objC;
    Connection con;
    modelo_cliente objMCliente;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public void insertar()
    {
        try {

            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD
            System.out.println(name+""+lastName+""+gender+""+clave);
            String query1 = "INSERT INTO Customers VALUES('"+name+"','"+lastName+"','"+gender+"','"+clave+"')";


            Statement objSt = null;//ENCARGADO DE REALIZAR LA CONSULTA
            objSt = con.createStatement();

            objSt.executeUpdate(query1);//executeUpdate PARA ACTUALIZAR LA BD   Y EL EXECUTEQUERY SOLO PARA REALIZAR CONSULTAS

            //this.Listar();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Confirmacion de usuario");
            alert.setContentText("Tu nuevo usuario es '" + name + "' con clave '" + clave + "'");

            alert.showAndWait();
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
            String query = "UPDATE Customers SET name='"+name+"',lastName='"+lastName+"',"+"gender='"+gender+"',clave='"+clave+"' where idCustomer="+idCustomer;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            //this.Listar();
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
            String query = "DELETE FROM Customers WHERE idCustomer = "+idCustomer;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            //this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int Listar(String name)
    {
        objMCliente = new modelo_cliente();
        ObservableList<modelo_cliente> listCliente = null;
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();
            System.out.println(name);

            listCliente = FXCollections.observableArrayList();
            String query= "SELECT idCustomer FROM Customers where name ='"+name+"'";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            objMCliente=new modelo_cliente();
            while (res.next())
            {
                objMCliente.idCustomer=res.getInt("idCustomer");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("id: "+objMCliente.idCustomer);
        return objMCliente.idCustomer;

    }

    public Customers BuscarCustomer(int idCustomer) throws SQLException
    {
        String consulta="Select * from Customers where idCustomer="+idCustomer;
        objC=new ConexionBD();
        con=objC.getConectar();
        Customers customers=null;
        Statement statement =con.createStatement();
        ResultSet resultSet=statement.executeQuery(consulta);
        while (resultSet.next())
        {
            customers=new Customers();
            customers.setIdCustomer(resultSet.getString("idCustomer"));
            customers.setName(resultSet.getString("name"));
            customers.setLastName(resultSet.getString("lastName"));
        }
        return  customers;
    }
}
