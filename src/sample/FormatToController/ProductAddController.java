package sample.FormatToController;



import sample.DAOs.CategoryDAO;
import sample.DAOs.MySQL;
import sample.DAOs.ProductDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Category;
import sample.TDAs.Product;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductAddController implements Initializable {
    public ConexionBD objC =  new ConexionBD();
    @FXML
    private JFXTextField tfId, tfName, tfPrice, tfImage, tfDescription;
    @FXML
    private ComboBox cbCategory;
    @FXML
    private JFXButton btnSave, btnCancel, btnExit;

    Product product = new Product();
    ProductDAO productDAO = new ProductDAO(objC.getConectar());
    Alert alert=new Alert(Alert.AlertType.ERROR);
    CategoryDAO catDao = new CategoryDAO(objC.getConectar());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Category> lista = catDao.findNameCategory();
        cbCategory.setItems(lista);
        cbCategory.setOnAction(eventCombo);
        btnCancel.setOnAction(event);
        btnExit.setOnAction(event);
        btnSave.setOnAction(eventSave);

    }
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

        }
    };

    EventHandler<ActionEvent> eventCombo = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {


        }
    };

    EventHandler eventSave = new EventHandler() {
        @Override
        public void handle(Event event) {
            if (notnull())
            {
                //product.setIdProduct(Integer.parseInt(tfId.getText()));
                product.setNameProduct(tfName.getText());
                Category idcategory = (Category) cbCategory.getSelectionModel().getSelectedItem();
                product.setIdCategory(idcategory.getIdCategory());
                product.setDescription(tfDescription.getText());
                product.setPrice(Double.parseDouble(tfPrice.getText()));
                product.setImage(tfImage.getText());
                System.out.println(product);
                try {
                    if (productDAO.insert(product)) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Producto añadido");
                        alert.show();
                    }
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Error Producto");
                    alert.show();
                }

            }

        }
    };

    private  boolean notnull(){

        if (tfName.getText().equals("")||tfDescription.getText().equals("")||tfPrice.getText().equals("")||tfImage.getText().equals(""))
        {
            alert.setHeaderText("Favor de llenar todos los campos");
            alert.showAndWait();
            return false;
        }else if (cbCategory.getSelectionModel().isEmpty()) {
            alert.setHeaderText("Debes seleccionar una categoria");
            alert.showAndWait();
            return false;
        }
        return true;
    }

}
