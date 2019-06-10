package sample.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD
{
    public static Connection cn = null;
    private static String host="localhost";
    private  static String BD="amazonV3";
    private  static String User="root",password="yosolosequenosenada";
    public Connection getConectar()
    {

        return cn;
    }
    public ConexionBD()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://"+ host +":3306/" + BD, User, password);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
