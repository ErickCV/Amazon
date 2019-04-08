package sample.FormatToController;

import sample.DAOs.MySQL;
import sample.DAOs.ProductDAO;
import sample.DAOs.StockDAO;
import sample.DAOs.StoreDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Product;
import sample.TDAs.Stock;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddToStockFormatController  implements Initializable {
    public ConexionBD objC =  new ConexionBD();
    @FXML
    JFXComboBox<Product> cmbProduct;
    @FXML
    JFXComboBox<Store> cmbStore;
    @FXML
    JFXTextField txtQuantity;
    @FXML
    JFXButton btnAdd,btnCancel,btnUpdate;
    @FXML
    Label lbl;
    Alert info=new Alert(Alert.AlertType.INFORMATION);
    Alert error=new Alert(Alert.AlertType.ERROR);
    ProductDAO productDAO=new ProductDAO(objC.getConectar());
    Stock stock=new Stock();
    Product product=new Product();
    Store store=new Store();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(event);
        btnUpdate.setOnAction(event);
        btnCancel.setOnAction(event);
        cmbProduct.setItems(productDAO.findAll());
        cmbStore.setItems(new StoreDAO(objC.getConectar()).find());
    }
    EventHandler event=new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource()==btnAdd)
            {
                if (!cmbProduct.getSelectionModel().isEmpty()||!cmbStore.getSelectionModel().isEmpty()||txtQuantity.getText().equals("")){
                    product=cmbProduct.getSelectionModel().getSelectedItem();
                    stock.setIdProduct(product);
                    stock.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    stock.setIdStore(cmbStore.getSelectionModel().getSelectedItem());
                   if (new StockDAO(objC.getConectar()).insert(stock)){
                       info.setContentText("Se ha agregado la informacion correctamente");
                       info.setHeaderText("insercion completa");
                       info.show();
                   }else {
                       error.setHeaderText("Fallo al insertar");
                       error.setHeaderText("Hubo un problema al tratar de agregar la informacion");
                       error.show();
                   }
                }
                else {
                    error.setHeaderText("Campo Vacio");
                    error.setHeaderText("Por favor llene todos los campos");
                    error.show();
                }



            }else if (event.getSource()==btnUpdate)
            {
                if (!cmbProduct.getSelectionModel().isEmpty()||!cmbStore.getSelectionModel().isEmpty()||txtQuantity.getText().equals("")){
                    product=cmbProduct.getSelectionModel().getSelectedItem();
                    stock.setIdProduct(product);
                    stock.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    stock.setIdStore(cmbStore.getSelectionModel().getSelectedItem());
                    if (new StockDAO(objC.getConectar()).update(stock)){
                        info.setContentText("Se ha Actulizado la informacion correctamente");
                        info.setHeaderText("Actualizacion completada");
                        info.show();
                    }else {
                        error.setHeaderText("Fallo al actualizar");
                        error.setHeaderText("Hubo un problema al tratar de actualizar la informacion");
                        error.show();
                    }
                }
                else {
                    error.setHeaderText("Campo Vacio");
                    error.setHeaderText("Por favor llene todos los campos");
                    error.show();
                }
            }else if (event.getSource()==btnCancel)
            {
                txtQuantity.setText("");
                cmbProduct.getSelectionModel().clearSelection();
                cmbStore.getSelectionModel().clearSelection();
            }
        }
    };
    //en la clase que se necesite se copia todo el siguiente codigo
    Stage stage= new Stage();//esto se debe crear al inicio de la clase
    // showStock(stage); //cuando se quiera abrir la ventana se manda llamar el metodo y se le manda como parametro el stage declarado arriba
    public static Stage StockStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderStock; // Esto tambien
    private void showStock(Stage _packageListStage){
        try {
            StockStage= _packageListStage;
            StockStage.setTitle("Store");
            Parent root= null;
            loaderStock= new FXMLLoader(getClass().getResource("../FXML/AddStoreFormat.fxml"));
            AddToStockFormatController addToStockFormatControllerr= new AddToStockFormatController();
            loaderStock.setController(addToStockFormatControllerr);
            root = loaderStock.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("");//aqui va la hoja de estilo
            StockStage.setScene(scene);
            StockStage.setMaximized(true);
            StockStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
