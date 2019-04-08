package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.FormatToController.AddPackageController;
import sample.Modelos.ConexionBD;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main extends Application {

    public void start(Stage _primaryStage) throws Exception {
        MenuAsUser objM = new MenuAsUser("User","clave");
        ConexionBD objC = new ConexionBD();

    }
    public static void  main(String[] args) {
                launch(args);
    }
}
