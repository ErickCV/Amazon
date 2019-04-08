package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class modelo_productosMAU
{
    public int idProduct,idCategory;
    public String nameProduct,description,image,texto;
    public ResultSet res;
     public int i;
    public float price;
    public Image [] imgArray = new Image[10];
    public String[] ArrayName = new String[10];
    public String[] ArrayDescrip = new String[10];
    public Float[] ArrayPrecio = new Float[10];
    ConexionBD objC;
    Connection con;

    public modelo_productosMAU(String nameProduct,float price,String description,String image)
    {
        this.nameProduct = nameProduct;
        this.price = price;
        this.description = description;
        this.image=image;
    }

    public modelo_productosMAU()
    {

    }

    public modelo_productosMAU(Connection con) {
    }

    @Override
    public String toString() {
        return "modelo_productos{" +
                "image='" + image + '\'' +
                '}';
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }


    public ObservableList<modelo_productos> Lista(String cate)
    {
        ObservableList<modelo_productos> listProd=null;
        try
        {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD
            modelo_productos objMp;
            listProd = FXCollections.observableArrayList();

            String query = "select nameProduct,price,description,image from product " +
                    "where idCategory in (select idCategory from category where name = '" + cate + "')";
            Statement objSt = con.createStatement();//ENCARGADO DE REALIZAR LA CONSULTA
            res = objSt.executeQuery(query);
            while (res.next())
            {
                objMp = new modelo_productos();
                objMp.setNameProduct(res.getString("nameProduct"));
                objMp.setPrice(res.getFloat("price"));
                objMp.setDescription(res.getString("description"));
                objMp.setImage(res.getString("image"));
                listProd.add(objMp);
                ArrayName[i] = new String(res.getString("nameProduct"));
                ArrayPrecio[i] =  new Float(res.getFloat("price"));
                ArrayDescrip[i] = new String(res.getString("description"));
                imgArray[i] = new Image(res.getString("image"));
                i = i+1;
            }
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  listProd;
    }

    public ObservableList<modelo_productos> Lista2(String cate, String cate2)
    {
        ObservableList<modelo_productos> listProd=null;
        try
        {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD
            modelo_productos objMp;
            listProd = FXCollections.observableArrayList();

            String query = "select nameProduct,price,description,image from product where idCategory in (select idCategory from category where name = '" + cate + "')" +
                    "union select nameProduct,price,description,image from product where idCategory in (select idCategory from category where name = '" + cate2 +"')";
            Statement objSt = con.createStatement();//ENCARGADO DE REALIZAR LA CONSULTA
            ResultSet res = objSt.executeQuery(query);
            while (res.next())
            {
                objMp = new modelo_productos();
                objMp.setNameProduct(res.getString("nameProduct"));
                objMp.setPrice(res.getFloat("price"));
                objMp.setDescription(res.getString("description"));
                objMp.setImage(res.getString("image"));
                listProd.add(objMp);
                ArrayName[i] = new String(res.getString("nameProduct"));
                ArrayPrecio[i] =  new Float(res.getFloat("price"));
                ArrayDescrip[i] = new String(res.getString("description"));
                imgArray[i] = new Image(res.getString("image"));
                i = i+1;
            }
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  listProd;
    }

    public ObservableList<modelo_productos> ListaPrecios(float p1,float p2)
    {
        ObservableList<modelo_productos> listProd=null;
        try
        {
            objC = new ConexionBD();//abre la conexion a la BD
            con = objC.getConectar();//EL QUE DA ACCESO A LA BD
            modelo_productos objMp;
            listProd = FXCollections.observableArrayList();

            String query = "select nameproduct,price,description,image from product where price >= '" + p1 + "' and price <='"+ p2 +"'";
            Statement objSt = con.createStatement();//ENCARGADO DE REALIZAR LA CONSULTA
            res = objSt.executeQuery(query);
            while (res.next())
            {
                objMp = new modelo_productos();
                objMp.setNameProduct(res.getString("nameProduct"));
                objMp.setPrice(res.getFloat("price"));
                objMp.setDescription(res.getString("description"));
                objMp.setImage(res.getString("image"));
                listProd.add(objMp);
                ArrayName[i] = new String(res.getString("nameProduct"));
                ArrayPrecio[i] =  new Float(res.getFloat("price"));
                ArrayDescrip[i] = new String(res.getString("description"));
                imgArray[i] = new Image(res.getString("image"));
                i = i+1;
            }
            con.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  listProd;
    }

}
