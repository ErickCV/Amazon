package sample.Vistas;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Modelos.ConexionBD;
import sample.Modelos.modelo_carritocompras;
import sample.Modelos.modelo_cliente;
import sample.TDAs.Customers;
import sample.Tablas.Tabla_CarritoCompras;

public class Main extends Application {

    public void start(Stage _primaryStage) throws Exception {
       // MenuAsUser objM = new MenuAsUser("user","clave");
        //ConexionBD objC = new ConexionBD();
        //new Tabla_CarritoCompras();
        modelo_carritocompras carrito=new modelo_carritocompras();
        carrito.setIdCart(1);
        carrito.setIdCustomer(new modelo_cliente().BuscarCustomer(1));
        new Vista_carritocompras(carrito);
        //customers.setName("Luis");
        //customers.setLastName("Ramirez");


    }
    public static void  main(String[] args) {
                launch(args);
    }
}
