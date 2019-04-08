package sample.FormatToController;

import sample.DAOs.MySQL;
import sample.DAOs.PromoDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Promotion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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

public class AddPromotionFormatController  implements Initializable {
    public ConexionBD objC =  new ConexionBD();
    @FXML
    JFXTextField txtPromo,txtName;
    @FXML
    JFXDatePicker dpPromo;
    @FXML
    JFXButton btnAdd,btnCancel,btnUpdate;
    Promotion promotion=new Promotion();
    PromoDAO promoDAO=new PromoDAO(objC.getConectar());
    Alert error=new Alert(Alert.AlertType.ERROR);
    Alert info=new Alert(Alert.AlertType.INFORMATION);
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
                if (txtName.getText().equals("")||dpPromo.getEditor().getText().equals(""))
                {
                    error.setHeaderText("Campo Vacio");
                    error.setContentText("Algun Campo esta vacio");
                    error.show();
                }
                else {
                    txtPromo.setText("");
                    promotion.setName(txtName.getText());
                    promotion.setDatePromo(dpPromo.getEditor().getText());
                    if (promoDAO.insert(promotion))
                    {
                        info.setContentText("Se ha agregado una nueva promocion");
                        info.setHeaderText("Promocion agregada");
                        info.show();
                    }
                    else {
                        error.setHeaderText("Fallo al insertar");
                        error.setHeaderText("Hubo un problema al tratar de agregar una nueva promocion");
                        error.show();
                    }
                }

            }else if (event.getSource()==btnUpdate)
            {
                if (txtName.getText().equals("")||dpPromo.getEditor().getText().equals("")||txtPromo.getText().equals(""))
                {
                    error.setHeaderText("Campo Vacio");
                    error.setContentText("Algun Campo esta vacio");
                    error.show();
                }
                else {
                    promotion.setName(txtName.getText());
                    promotion.setDatePromo(dpPromo.getEditor().getText());
                    try{
                    promotion.setIdPromo(Integer.parseInt(txtPromo.getText()));
                        if (promoDAO.update(promotion))
                        {
                            info.setContentText("Se ha agregado una nueva promocion");
                            info.setHeaderText("Promocion agregada");
                            info.show();
                        }
                        else {
                            error.setHeaderText("Fallo al insertar");
                            error.setHeaderText("Hubo un problema al tratar de agregar una nueva promocion");
                            error.show();
                        }
                    }
                    catch(Exception e){
                        error.setHeaderText("ID invalido");
                        error.setHeaderText("puede ser que campo de ID no es un numero");
                        error.show();
                    }

                }

            }else if (event.getSource()==btnCancel)
            {
                txtPromo.setText("");
                txtName.setText("");
                dpPromo.getEditor().setText("");

            }
        }
    };

    //en la clase que se necesite se copia todo el siguiente codigo
    Stage stage= new Stage();//esto se debe crear al inicio de la clase
    // showAddPromo(stage); //cuando se quiera abrir la ventana se manda llamar el metodo y se le manda como parametro el stage declarado arriba
    public static Stage addPromoStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderAddPromo; // Esto tambien
    private void showAddPromo(Stage _addPromoStage){
        try {
            addPromoStage= _addPromoStage;
            addPromoStage.setTitle("Agregar Promocion");
            Parent root= null;
            loaderAddPromo = new FXMLLoader(getClass().getResource("/sample/FXML/AddPromotionFormat.fxml"));
            AddPromotionFormatController addPromotionFormatController= new AddPromotionFormatController();
            loaderAddPromo .setController(addPromotionFormatController);
            root = loaderAddPromo .load();
            Scene scene=new Scene(root,700,650);
            scene.getStylesheets().add("");//aqui va la hoja de estilo
            addPromoStage.setScene(scene);
            addPromoStage.setResizable(false);
            addPromoStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
