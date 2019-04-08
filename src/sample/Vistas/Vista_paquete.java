package sample.Vistas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Vista_paquete extends Stage{
    public Stage packageStage; // Esto es para volver a mostrar la ventana
    public FXMLLoader loaderPackage; // Esto tambien

    public Vista_paquete(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample/FXML/AddPackageFormat.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            this.setScene(new Scene(root1,100,200));
            this.show();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
