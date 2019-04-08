package sample.FormatToController;

import sample.DAOs.AdreessDAO;
import sample.DAOs.MySQL;
import sample.DAOs.StoreDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Address;
import sample.TDAs.Store;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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

public class AddStoreFormatController  implements Initializable {
    public ConexionBD objC =  new ConexionBD();
@FXML
    JFXButton btnAdd,btnCancel,btnUpdate;
@FXML
    JFXTextField txtIdStore;
@FXML
    JFXComboBox cmbAddress;
    AdreessDAO adreessDAO=new AdreessDAO(objC.getConectar());
    Store store=new Store();
    Address address=new Address();
    StoreDAO storeDAO=new StoreDAO(objC.getConectar());
    Alert info=new Alert(Alert.AlertType.INFORMATION);
    Alert error=new Alert(Alert.AlertType.ERROR);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(event);
        btnUpdate.setOnAction(event);
        btnCancel.setOnAction(event);
        cmbAddress.setItems(adreessDAO.findAll());


    }
    EventHandler event =new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource()==btnAdd){
                if (!cmbAddress.getSelectionModel().isEmpty()){
                    address= (Address) cmbAddress.getSelectionModel().getSelectedItem();
                    store.setIdAddress(address);
                    if (storeDAO.insert(store))
                    {
                        info.setContentText("Se ha agregado una nueva tienda");
                        info.setHeaderText("Tienda  agregada");
                        info.show();
                    }
                    else {
                        error.setHeaderText("Fallo al insertar");
                        error.setHeaderText("Hubo un problema al tratar de agregar una nueva tienda");
                        error.show();
                    }
                }else {
                    error.setHeaderText("Campo Vacio");
                    error.setHeaderText("Por favor seleccione una direccion");
                    error.show();
                }
            }else if (event.getSource()==btnUpdate){
                if (!cmbAddress.getSelectionModel().isEmpty()&&!txtIdStore.getText().equals("")){
                    address= (Address) cmbAddress.getSelectionModel().getSelectedItem();
                    try {
                        store.setIdStore(Integer.parseInt(txtIdStore.getText()));
                    }catch (Exception e){
                        error.setHeaderText("ID incorrecto");
                        error.setHeaderText("Revise que el campo ID sea un numero");
                        error.show();
                    }
                    store.setIdAddress(address);
                    if (storeDAO.update(store))
                    {
                        info.setContentText("Se ha actualizado una tienda");
                        info.setHeaderText("Tienda  actualizada");
                        info.show();
                    }
                    else {
                        error.setHeaderText("Fallo al actualizar");
                        error.setHeaderText("Hubo un problema al tratar de actualizar una nueva tienda");
                        error.show();
                    }
                }else {
                    error.setHeaderText("Campo Vacio");
                    error.setHeaderText("Por favor seleccione una direccion");
                    error.show();
                }


            }else if (event.getSource()==btnCancel){
                txtIdStore.setText("");
                cmbAddress.getSelectionModel().clearSelection();

            }

        }
    };
    //en la clase que se necesite se copia todo el siguiente codigo
    Stage stage= new Stage();//esto se debe crear al inicio de la clase
    // showStore(stage); //cuando se quiera abrir la ventana se manda llamar el metodo y se le manda como parametro el stage declarado arriba
    public static Stage StoreStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderStore; // Esto tambien
    private void showStore(Stage _packageListStage){
        try {
            StoreStage= _packageListStage;
            StoreStage.setTitle("Store");
            Parent root= null;
            loaderStore= new FXMLLoader(getClass().getResource("../FXML/AddStoreFormat.fxml"));
            AddStoreFormatController addStoreFormatController= new AddStoreFormatController();
            loaderStore.setController(addStoreFormatController);
            root = loaderStore.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("");//aqui va la hoja de estilo
            StoreStage.setScene(scene);
            StoreStage.setMaximized(true);
            StoreStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
