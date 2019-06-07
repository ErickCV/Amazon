package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class modelo_usuarios {
    private String nombre, clave;
    private int n_usuario,rol;
    public String[] array;
    public int i = 0;
    public String dato, string="";
    public String[] arrayUsuarios, arrayConstraseña;
    public int R=0;
    public int valor = 0;
    public Statement objSt,objSt2;


    @Override
    public String toString() {
        return "modelo_usuarios{" +
                "arrayUsuarios=" + Arrays.toString(arrayUsuarios) +
                ", arrayContraseña=" + Arrays.toString(arrayConstraseña) +
                '}';
    }

    ConexionBD objC;
    Connection con;

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getN_usuario() {
        return n_usuario;
    }

    public void setN_usuario(int n_usuario) {
        this.n_usuario = n_usuario;
    }


    public String getContra() {
        return clave;
    }

    public void setContra(String clave) {
        this.clave = clave;
    }

    public void Insertar(){
        try{
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO Users (userName,password,idRole) " +
                    "VALUES('"+nombre+"','"+clave+"','"+rol+"')";

            Statement objSt = null;//ENCARGADO DE REALIZAR LA CONSULTA
            objSt = con.createStatement();
            objSt.executeUpdate(query);//executeUpdate PARA ACTUALIZAR LA BD   Y EL EXECUTEQUERY SOLO PARA REALIZAR CONSULTAS
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Borrar(){
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "DELETE FROM Users WHERE idUser = "+n_usuario;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Actualizar(){
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();

            String query = "UPDATE Users SET userName ='"+nombre+"',password='"+clave+"',idRole='"+rol+"' where idUser="+n_usuario;

            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public int consultas()
    {
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            System.out.println("nombre "+nombre);
            System.out.println("clave "+clave);
            String query = "exec verificaUsuario '"+nombre+"','"+clave+"','"+valor+"'";
            System.out.println(query);
            objSt = con.createStatement();
            ResultSet rs = objSt.executeQuery(query);
            while(rs.next()) {
                if (rs.wasNull())
                    valor = 0;
                else
                    valor = 1;
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return valor;
    }

    public int consultas2()
    {
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            System.out.println("nombre "+nombre);
            System.out.println("clave "+clave);

            String query2 = "exec verificaCustomer '"+nombre+"','"+clave+"','"+valor+"'";

            objSt2 = con.createStatement();
            ResultSet rs2 = objSt2.executeQuery(query2);
            while(rs2.next()) {
                if (rs2.wasNull())
                    valor = 0;
                else
                    valor = 2;
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return valor;
    }

    public int consultas3()
    {
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            System.out.println("nombre "+nombre);
            System.out.println("clave "+clave);

            String query2 = "INSERT INTO Customers (name,lastName,gender,clave) " +
                    "VALUES('"+nombre+"','"+""+"','"+"F"+"','"+clave+"')";

            objSt2 = con.createStatement();
            ResultSet rs2 = objSt2.executeQuery(query2);
            while(rs2.next()) {
                if (rs2.wasNull())
                    valor = 0;
                else
                    valor = 2;
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return valor;
    }

    public ObservableList<modelo_usuarios> Listar()//de modelos
    {
        arrayUsuarios = new String[20];
        arrayConstraseña = new String[20];
        array = new String[50];

        ObservableList<modelo_usuarios> listUsuarios=null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();
            array = new String[50];

            modelo_usuarios objA;
            listUsuarios = FXCollections.observableArrayList();
            String query= "select * from Users";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objA = new modelo_usuarios();//de modelos
                objA.setN_usuario(res.getInt("idUser"));
                objA.setNombre(res.getString("userName"));
                objA.setContra(res.getString("password"));
                objA.setRol(res.getInt("idRole"));
                arrayUsuarios[i] = objA.getNombre().toString();
                arrayConstraseña[i] = objA.getContra().toString();

                string = String.valueOf(objA.getN_usuario()).toString()+" "+objA.getNombre().toString()+" "+
                        objA.getContra().toString()+" ";
                i++;
                R++;
                listUsuarios.add(objA);
                array[i]=string;
                dato=dato+array[i].toString();
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
