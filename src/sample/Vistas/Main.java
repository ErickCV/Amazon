package sample.Vistas;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Modelos.ConexionBD;
import sample.Modelos.modelo_carritocompras;

public class Main extends Application {

    public void start(Stage _primaryStage) throws Exception {
        MenuAsUser objM = new MenuAsUser("User","clave");
        ConexionBD objC = new ConexionBD();
    }
    public static void  main(String[] args) {
                launch(args);
    }
}
