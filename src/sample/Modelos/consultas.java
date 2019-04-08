package sample.Modelos;

import java.sql.Connection;

public class consultas
{
    ConexionBD objC;
    Connection con;
    public consultas()
    {
        String query = "select userName, password" +
                "          from Users " +
                "           where password = ";
    }
}
