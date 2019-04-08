package sample.Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD
{
    public Connection cn = null;
    public Connection getConectar()
    {

        return cn;
    }
    public ConexionBD()
    {
        try
        {
            String connectionUrl = "jdbc:sqlserver://;database=AmazonV3;integratedSecurity=true;";
            cn = DriverManager.getConnection(connectionUrl);
            System.out.println("Conectado.");
        } catch (SQLException ex)
        {
            System.out.println("Error.");
        }
    }
}
