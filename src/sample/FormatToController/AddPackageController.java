package sample.FormatToController;

import sample.DAOs.PackageDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Packages;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPackageController implements Initializable
{
    public ConexionBD objC = new ConexionBD();
    @FXML
    JFXTextField txtNamePackage,txtDescription,txtPice,txtstock,txtIdPackage;
    @FXML
    JFXButton btnImge,btnAdd,btnCancel,btnUpdate;
    Packages packages=new Packages();
    Alert alert=new Alert(Alert.AlertType.ERROR);//y tus label?
    Alert confirm=new Alert(Alert.AlertType.INFORMATION);
    PackageDAO packageDAO=new PackageDAO(objC.getConectar());//a ver calemos no paso nada pero ya no salio el error eso es bueno, creo que entonces hay que poner el metodo que tenemos para hacer que abra la ventana
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  txtIdPackage.setDisable(true);
        btnAdd.setOnAction(event);
        btnUpdate.setOnAction(event);
        btnCancel.setOnAction(event);
        btnImge.setOnAction(event);

    }
    EventHandler event=new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource()==btnAdd)//donde deja de serlo?
            {

                if (txtNamePackage.getText().equals("")||txtDescription.getText().equals("")
                        ||txtPice.getText().equals("")||txtstock.getText().equals("")){
                    alert.setHeaderText("Campo Vacio");
                    alert.setContentText("Alguno de los campos esta vacio");
                    alert.show();

                }else {
                    packages.setNamePackage(txtNamePackage.getText());
                    packages.setDescription(txtDescription.getText());
                    try{
                        packages.setPrice(Double.parseDouble(txtPice.getText()));
                        packages.setStock(Integer.parseInt(txtstock.getText()));

                    }catch (Exception e){
                        alert.setTitle("Contenido invalido");
                        alert.setHeaderText("Campo invalido");
                        alert.setContentText("Un dato que se trata de introducir no es valido");
                        alert.show();
                    }
                    if (packageDAO.insert(packages))
                        System.out.println("Se agrego de forma exitosa");
                    else
                        System.out.println("No se pudo agregar");
                }
//aqui?si
            }
            else if (event.getSource()==btnCancel)//
            {

               txtIdPackage.setText("");
               txtNamePackage.setText("");
               txtDescription.setText("");
               txtPice.setText("");
               txtstock.setText("");

            }else if (event.getSource()==btnUpdate)
            {
               //estaba probando si si hacia lo que queria, entonces ya esta?segun yo si,puedes porfa activar el primer textfield
                if (checkdata()&&notnull()){
                    if(packageDAO.update(packages))
                    {
                        confirm.setHeaderText("Actualizacion Completa");
                      confirm.setContentText("Se ha actualizado de forma correcta");
                      confirm.show();
                    }else {
                        alert.setHeaderText("Fallo al actualizar");
                        alert.setContentText("No se pudo actualizar el dato, por favor revise que el ID exista o que todos los campos ean correctos");
                        alert.show();
                    }
                }

            }else if (event.getSource()==btnImge)
            {

            }

        }
    };
    public boolean notnull(){
            if (txtNamePackage.getText().equals("")||txtDescription.getText().equals("")||txtIdPackage.getText().equals("")
            ||txtPice.getText().equals("")||txtstock.getText().equals(""))
            return false;
            else
                return true;
    }
    public boolean checkdata(){
        packages.setNamePackage(txtNamePackage.getText());
        packages.setDescription(txtDescription.getText());
        try{
            packages.setIdPackage(Integer.parseInt(txtIdPackage.getText()));
            packages.setPrice(Double.parseDouble(txtPice.getText()));
            packages.setStock(Integer.parseInt(txtstock.getText()));
            return true;
        }catch (Exception e){
            alert.setTitle("Contenido invalido");
            alert.setHeaderText("Campo invalido");
            alert.setContentText("Un dato que se trata de introducir no es valido");
            alert.show();
            return false;
        }
    }
   //en la clase que se necesite se copia todo el siguiente codigo
    Stage stage= new Stage();//esto se debe crear al inicio de la clase
   // showPackage(stage); //cuando se quiera abrir la ventana se manda llamar el metodo y se le manda como parametro el stage declarado arriba
    public static Stage packageStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderPackage; // Esto tambien
    public void showPackage(Stage _packageListStage){//si mando a llamar a este metodo se muestra la ventana? si y solo debemos pasarle ese parametro
        try {// y de donde obtengo ese stage?, asi nadamas?si
            packageStage= _packageListStage;
            packageStage.setTitle("package");
            Parent root= null;
            loaderPackage= new FXMLLoader(getClass().getResource("sample/FXML/AddPackageFormat.fxml"));
            AddPackageController addPackageController = new AddPackageController();
            loaderPackage.setController(addPackageController);
            root = loaderPackage.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/sample/css/estilo.css");//aqui va la hoja de estilo
            packageStage.setScene(scene);
            packageStage.setMaximized(true);
            packageStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
