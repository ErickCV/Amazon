package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

            String query = "INSERT INTO Customers (name,lastName,gender) " +
                    "VALUES('"+name+"','"+lastName+"','"+gender+"')";
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
            String query = "UPDATE Customers SET name='"+name+"',lastName='"+lastName+"',"+"gender='"+gender+"',clave='"+clave+"' where idCustomer="+idCustomer;
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
            String query = "DELETE FROM Customers WHERE idCustomer = "+idCustomer;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<modelo_cliente> Listar()
    {
        ObservableList<modelo_cliente> listCliente = null;
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_cliente objMCliente;
            listCliente = FXCollections.observableArrayList();
            String query= "SELECT * FROM Customers ORDER BY idCustomer";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);

            while (res.next())
            {
                objMCliente = new modelo_cliente();
                objMCliente.setIdCustomer(res.getInt("idCustomer"));
                objMCliente.setName(res.getString("name"));
                objMCliente.setLastName(res.getString("lastName"));
                objMCliente.setGender(res.getString("gender"));
                objMCliente.setClave(res.getString("clave"));
                listCliente.add(objMCliente);
                texto = String.valueOf(res.getInt("idCustomer"))+" "+res.getString("name")+" "
                        +res.getString("lastName")+" "+res.getString("gender");
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listCliente;
    }
    public Customers BuscarCustomer(int idCustomer) throws SQLException {
        String consulta="Select * from Customers where idCustomer="+idCustomer;
        objC=new ConexionBD();
        con=objC.getConectar();
        Customers customers=null;
        Statement statement =con.createStatement();
        ResultSet resultSet=statement.executeQuery(consulta);
        while (resultSet.next()){
            customers=new Customers();
            customers.setIdCustomer(resultSet.getString("idCustomer"));
            customers.setName(resultSet.getString("name"));
            customers.setLastName(resultSet.getString("lastName"));
        }
        return  customers;
    }
}
