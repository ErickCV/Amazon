package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.DAOs.CategoryDAO;
import sample.DAOs.ProductDAO;
import sample.Modelos.ConexionBD;
import sample.Modelos.modelo_categoria;
import sample.Modelos.modelo_productos;
import sample.TDAs.Category;
import sample.TDAs.Product;

public class Vista_productos extends Stage {
    public ConexionBD objC =  new ConexionBD();
    private HBox botones=new HBox();
    private Scene escena;
    private GridPane gpPrincipal;
    private JFXTextField  tfName=new JFXTextField(),
            tfPrice=new JFXTextField(), tfImage=new JFXTextField("imagen"), tfDescription=new JFXTextField();
    private ComboBox cbCategory=new ComboBox();
    private JFXButton btnSave=new JFXButton("Guardar"), btnCancel=new JFXButton("Cancelar"), btnExit=new JFXButton("Salir");
    modelo_productos product = new modelo_productos();
    ProductDAO productDAO = new ProductDAO(objC.getConectar());
    Alert alert=new Alert(Alert.AlertType.ERROR);
    CategoryDAO catDao = new CategoryDAO(objC.getConectar());
    public Vista_productos() {
        crearGUI();
        ObservableList<Category> lista = catDao.findNameCategory();
        cbCategory.setItems(lista);
        cbCategory.setOnAction(Event->eventCombo());
        btnCancel.setOnAction(Event->eventT());
        btnExit.setOnAction(Event->eventT());
        btnSave.setOnAction(Event->eventSave());
    }
    public void crearGUI(){
        cbCategory.setItems(new modelo_categoria().Listar());
        tfName.setPromptText("Nombre de producto");
        tfDescription.setPromptText("Descripcion");
        tfPrice.setPromptText("Precio");
        cbCategory.setPromptText("Categoria");
        gpPrincipal=new GridPane();
        gpPrincipal.add(tfName,0,1);
        gpPrincipal.add(tfDescription,0,2);
        gpPrincipal.add(cbCategory,0,3);
        gpPrincipal.add(tfPrice,0,4);
        botones.getChildren().addAll(btnSave,btnCancel,btnExit);
        gpPrincipal.add(botones,0,5,4,1);
        escena=new Scene(gpPrincipal);
        escena.getStylesheets().add("/sample/css/estilo.css");
        setScene(escena);
        show();

    }
    private void eventSave() {
        if (notnull())
        {
            //product.setIdProduct(Integer.parseInt(tfId.getText()));
            product.setNameProduct(tfName.getText());
            Category idcategory = (Category) cbCategory.getSelectionModel().getSelectedItem();
            product.setIdCategory(idcategory.getIdCategory());
            product.setDescription(tfDescription.getText());
            product.setPrice(Float.parseFloat(tfPrice.getText()));
            product.setImage(tfImage.getText());
            System.out.println(product);
            try {
                 product.Insert();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Producto añadido");
                    alert.show();

            }
            catch (Exception e){
                System.out.println(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Error Producto");
                alert.show();
            }

        }
    }

    private void eventT() {
    }

    private void eventCombo() {
    }
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
