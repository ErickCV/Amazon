package sample.FormatToController;

import sample.DAOs.MySQL;
import sample.DAOs.RoleDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Role;
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


public class AddRoleFormat implements Initializable {
    public ConexionBD objC =  new ConexionBD();
    @FXML
    JFXTextField txtidRole,txtName,txtDescription;
    @FXML
    JFXButton btnAdd,btnCancel,btnUpdate;
    Role role=new Role();
    Alert info=new Alert(Alert.AlertType.INFORMATION);
    Alert error=new Alert(Alert.AlertType.ERROR);
    RoleDAO roleDAO=new RoleDAO(objC.getConectar());
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(event );
        btnUpdate.setOnAction(event);
        btnCancel.setOnAction(event );
    }
    EventHandler event=new EventHandler() {
        @Override
        public void handle(Event event) {
            if (event.getSource()==btnAdd)
            {
                if (!txtName.getText().equals("")||!txtDescription.getText().equals("")) {
                    txtidRole.setText("");
                    role.setName(txtName.getText());
                    role.setDescription(txtDescription.getText());
                    if(roleDAO.insert(role)){
                        info.setContentText("Se ha agregado un nuevo rol");
                        info.setHeaderText("Rol agregado");
                        info.show();
                    }else {
                        error.setHeaderText("Fallo al insertar");
                        error.setHeaderText("Hubo un problema al tratar de agregar un nuevo rol");
                        error.show();
                    }
                }

            }else if (event.getSource()==btnUpdate){
                if (!txtName.getText().equals("")||!txtDescription.getText().equals("")||!txtidRole.getText().equals("")){
                    role.setName(txtName.getText());
                    role.setDescription(txtDescription.getText());
                    try{ role.setIdRole(Integer.parseInt(txtidRole.getText()));
                        if (roleDAO.update(role))
                        {
                            info.setContentText("Se ha actualizado el role");
                            info.setHeaderText("Rol actualizado");
                            info.show();
                        }
                        else {
                            error.setHeaderText("Fallo al actualizar");
                            error.setHeaderText("Hubo un problema al tratar de actualizar el rol");
                            error.show();
                        }
                    }catch (Exception e){
                        error.setHeaderText("ID invalido");
                        error.setHeaderText("puede ser que campo de ID no es un numero");
                        error.show();
                    }
                }


            }else if (event.getSource()==btnCancel){
                txtidRole.setText("");
                txtName.setText("");
                txtDescription.setText("");
            }

        }
    };
    //en la clase que se necesite se copia todo el siguiente codigo
    Stage stage= new Stage();//esto se debe crear al inicio de la clase
    // showRole(stage); //cuando se quiera abrir la ventana se manda llamar el metodo y se le manda como parametro el stage declarado arriba
    public static Stage RoleStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderRole; // Esto tambien
    private void showRole(Stage _packageListStage){
        try {
            RoleStage= _packageListStage;
            RoleStage.setTitle("package");
            Parent root= null;
            loaderRole= new FXMLLoader(getClass().getResource("../FXML/AddRoleFormat.fxml"));
            AddRoleFormat addRoleFormat= new AddRoleFormat();
            loaderRole.setController(addRoleFormat);
            root = loaderRole.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("");//aqui va la hoja de estilo
            RoleStage.setScene(scene);
            RoleStage.setMaximized(true);
            RoleStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
