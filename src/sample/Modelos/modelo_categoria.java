package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_categoria
{
    public int idCategory;
    public String name,description,image,texto;
    ConexionBD objC;
    Connection con;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void insertar()
    {
        try {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD

            String query = "INSERT INTO Category (name,description,image) " +
                    "VALUES('"+name+"','"+description+"','"+image+")";
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
            String query = "UPDATE Category SET name='"+name+"',description='"+description+"',"+"image='"+image+"' where idCategory="+idCategory;
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
            String query = "DELETE FROM Category WHERE idCategory = "+idCategory;
            Statement objSt = con.createStatement();
            objSt.executeUpdate(query);
            this.Listar();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<modelo_categoria> Listar()
    {

        ObservableList<modelo_categoria> listCategoria = null;
        try {
            objC = new ConexionBD();
            con = objC.getConectar();

            modelo_categoria objMCategoria;
            listCategoria = FXCollections.observableArrayList();
            String query= "SELECT * FROM Category ORDER BY idCategory";
            Statement ObjSt = con.createStatement();
            ResultSet res = ObjSt.executeQuery(query);
            while (res.next())
            {
                objMCategoria = new modelo_categoria();
                objMCategoria.setIdCategory(res.getInt("idCategory"));
                objMCategoria.setName(res.getString("name"));
                objMCategoria.setDescription(res.getString("description"));
                objMCategoria.setImage(res.getString("image"));
                listCategoria.add(objMCategoria);

                texto = String.valueOf(res.getInt("idCategory"))+" "+res.getString("name")+" "
                        +res.getString("description")+" "+res.getString("image");
            }
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return listCategoria;
    }
}
