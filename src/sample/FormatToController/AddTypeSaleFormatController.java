package sample.FormatToController;

import sample.DAOs.MySQL;
import sample.DAOs.TypeSaleDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.TypeSale;
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

public class AddTypeSaleFormatController implements Initializable {
    public ConexionBD objC =  new ConexionBD();
   @FXML
   JFXButton btnAdd,btnCancel,btnUpdate;
   @FXML
    JFXTextField txtidTypeSale,txtName,txtDescription;
    Alert info=new Alert(Alert.AlertType.INFORMATION);
    Alert error=new Alert(Alert.AlertType.ERROR);
    TypeSale typeSale=new TypeSale();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(event);
        btnUpdate.setOnAction(event);
        btnCancel.setOnAction(event);
    }
    EventHandler event=new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource()==btnAdd)
            {
                try
                    {
                    if (!txtDescription.getText().equals("")||!txtName.getText().equals("")){

                            typeSale.setIdTypeSale(Integer.parseInt(txtidTypeSale.getText()));
                            typeSale.setName(txtName.getText());
                            typeSale.setDescription(txtDescription.getText());
                            if (new TypeSaleDAO(objC.getConectar()).insert(typeSale)){
                                info.setContentText("Se ha agregado la informacion correctamente");
                                info.setHeaderText("insercion completada");
                                info.show();
                            }else {
                                error.setHeaderText("Fallo al insertar");
                                error.setHeaderText("Hubo un problema al tratar de agregar la informacion");
                                error.show();
                            }

                    }else {
                        error.setHeaderText("Campo Vacio");
                        error.setHeaderText("Por favor llene todos los campos");
                        error.show();
                    }
                }catch (Exception e){
                    error.setHeaderText("Campo invalido");
                    error.setHeaderText("Por favor asegurese de llenar correctamente todos los campos");
                    error.show();
                }
            }else if (event.getSource()==btnUpdate)
            {
                try
                {
                    if (!txtidTypeSale.getText().equals("")||!txtDescription.getText().equals("")||!txtName.getText().equals("")){

                        typeSale.setIdTypeSale(Integer.parseInt(txtidTypeSale.getText()));
                        typeSale.setName(txtName.getText());
                        typeSale.setDescription(txtDescription.getText());
                        if (new TypeSaleDAO(objC.getConectar()).update(typeSale)){
                            info.setContentText("Se ha actualizado la informacion correctamente");
                            info.setHeaderText("actualizacion completada");
                            info.show();
                        }else {
                            error.setHeaderText("Fallo al actualizar");
                            error.setHeaderText("Hubo un problema al tratar de actualizar la informacion");
                            error.show();
                        }

                    }else {
                        error.setHeaderText("Campo Vacio");
                        error.setHeaderText("Por favor llene todos los campos");
                        error.show();
                    }
                }catch (Exception e){
                    error.setHeaderText("Campo invalido");
                    error.setHeaderText("Por favor asegurese de llenar correctamente todos los campos");
                    error.show();
                }

            }else if (event.getSource()==btnCancel)
            txtidTypeSale.setText("");
            txtName.setText("");
            txtDescription.setText("");
            {

            }
        }
    };




    //en la clase que se necesite se copia todo el siguiente codigo
    Stage stage= new Stage();//esto se debe crear al inicio de la clase
    // showTypeSale(stage); //cuando se quiera abrir la ventana se manda llamar el metodo y se le manda como parametro el stage declarado arriba
    public static Stage TypeSaleStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loadertypeSale; // Esto tambien
    private void showTypeSale(Stage _packageListStage){
        try {
            TypeSaleStage= _packageListStage;
            TypeSaleStage.setTitle("Type Sale");
            Parent root= null;
            loadertypeSale= new FXMLLoader(getClass().getResource("../FXML/AddTypeSaleFormat.fxml"));
            AddTypeSaleFormatController addTypeSaleFormatController= new AddTypeSaleFormatController();
            loadertypeSale.setController(addTypeSaleFormatController);
            root = loadertypeSale.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("");//aqui va la hoja de estilo
            TypeSaleStage.setScene(scene);
            TypeSaleStage.setMaximized(true);
            TypeSaleStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
