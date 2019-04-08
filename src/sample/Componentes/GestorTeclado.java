package sample.Componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import sample.Modelos.ConexionBD;
import sample.Modelos.modelo_carritocompras;
import sample.Modelos.modelo_metodopago;
import sample.Tablas.*;
import sample.Vistas.MenuAsCustomer;
import sample.Tablas.Tabla_MetodoPago;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GestorTeclado implements EventHandler<KeyEvent>
{
    ConexionBD objC;
    Connection con;
    private String opc="",string="",string2="";
    private MenuAsCustomer objMAC;
    private TableView tableView;
   private Tabla_MetodoPago objT;// = new Tabla_MetodoPago();
    private Statement objSt;
    private ResultSet rs;
    private modelo_metodopago objmp;
    private ObservableList<modelo_metodopago> listMetodoPago = null;
    private int tabla = 0;

    public GestorTeclado(MenuAsCustomer objMAC,String opc)
    {
        this.opc = opc;
        this.objMAC = objMAC;
        Seleccion();
    }

    @Override
    public void handle(KeyEvent event)
    {
        System.out.println("tabla "+string);
        System.out.println("campo "+string2);
        try
        {
            objC = new ConexionBD();
            con = objC.getConectar();
            String query = "select *" +
                    "       from "+string+
            "       where "+string2+" like "+"'%"+objMAC.txtBuscar.getText()+"%'";
            objSt = con.createStatement();
            rs = objSt.executeQuery(query);
           switch (tabla){
               case 1:
                   break;
               case 2:
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 5:
                   break;
               case 6:
                   break;
               case 7:
                   break;
               case 8:
                   break;
               case 9:
                   break;
               case 10:
                   break;
               case 11:
                   MetodoPago();
                   break;
               case 12:
                   break;
               case 13:
                   break;
               case 14:
                   break;
               case 15:
                   break;
               case 16:
                   break;
               case 17:
                   break;
               case 18:
                   break;
               case 19:
                   break;
               case 20:

           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void MetodoPago(){
        try {
            listMetodoPago = FXCollections.observableArrayList();
            while (rs.next()) {
                objmp = new modelo_metodopago();
                objmp.setIdPayment((rs.getInt("idPayment")));
                objmp.setTypePayment((rs.getString("typePayment")));
                objmp.setDescription(((rs.getString("decription"))));
                listMetodoPago.addAll(objmp);
            }
            con.close();
        objT.tableViewMetodoPago.setItems(listMetodoPago);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Seleccion()
    {
        System.out.println("tabla: "+opc);
        switch (opc)
        {
            case "Carrito de Compras":
                string ="ShoppingCart";
                string2="idCart";
                objMAC.txtBuscar.setPromptText("idCart");
                tabla=1;
                break;
            case "Categorias":
                string = "Category";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=2;
                break;
            case "Ciudad":
                string ="City";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=3;
                break;
            case "Cliente":
                string ="Customers";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=4;
                break;
            case "Contenido Paquete":
                string ="PackageContent";
                string2 = "detail";
                objMAC.txtBuscar.setPromptText("detail");
                tabla=5;
                break;
            case "Direccion":
                string ="Address";
                string2 = "street";
                objMAC.txtBuscar.setPromptText("street");
                tabla=6;
                break;
            case "Direccion Cliente":
                string="AddressCustomer";
                string2 = "idCustomer";
                objMAC.txtBuscar.setPromptText("idCustomer");
                tabla=7;
                break;
            case "Estado":
                string = "State";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=8;
                break;
            case "Factura":
                string ="invoice";
                string2 = "dateInvoice";
                objMAC.txtBuscar.setPromptText("dateInvoice");
                tabla=9;
                break;
            case "Lista Carrito":
                string ="ListShoppingCart";
                string2 = "detail";
                objMAC.txtBuscar.setPromptText("detail");
                tabla=10;
                break;
            case "Metodo de pago":
                string ="PaymentMethod";
                string2 = "decription";
                objMAC.txtBuscar.setPromptText("description");
                tabla=11;
                break;
            case "Pais":
                string ="Country";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=12;
                break;
            case "Paquete":
                string ="Package";
                string2 = "namePackage";
                objMAC.txtBuscar.setPromptText("namePackage");
                tabla=13;
                break;
            case "Productos":
                string ="Product";
                string2 = "nameProduct";
                objMAC.txtBuscar.setPromptText("nameProduct");
                tabla=14;
                break;
            case "Promociones":
                string ="Promotion";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=15;
                break;
            case "Rol":
                string ="role";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=16;
                break;
            case "Stock":
                string ="stock";
                string2 = "quantity";
                objMAC.txtBuscar.setPromptText("quantity");
                tabla=17;
                break;
            case "Tienda":
                string ="Store";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=18;
                break;
            case "Tipo de venta":
                string ="TypeSale";
                string2 = "name";
                objMAC.txtBuscar.setPromptText("name");
                tabla=19;
                break;
            case "Venta":
                string ="Sale";
                string2 = "date";
                objMAC.txtBuscar.setPromptText("date");
                tabla=20;
        }
    }


}
