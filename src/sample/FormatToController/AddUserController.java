package sample.FormatToController;


import sample.DAOs.MySQL;
import sample.DAOs.RoleDAO;
import sample.DAOs.UserDAO;
import sample.Modelos.ConexionBD;
import sample.TDAs.Role;
import sample.TDAs.Users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    public ConexionBD objC =  new ConexionBD();
    @FXML
    JFXTextField txfuserName;
    @FXML
    JFXPasswordField psfPassword,psfConfirmPassword;
    @FXML
    JFXComboBox cmbRole;
    @FXML
    JFXButton btnAdd,btnCancel,btnExit;
    RoleDAO roleDAO=new RoleDAO(objC.getConectar());
    UserDAO userDAO=new UserDAO(objC.getConectar());
    Users newUser=new Users();
    Alert alert =new Alert(Alert.AlertType.ERROR);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAdd.setOnAction(EventAdd);
        btnCancel.setOnAction(EventCancel);
        btnExit.setOnAction(EventExit);
        cmbRole.setItems(roleDAO.findAll());
    }
    EventHandler EventAdd=new EventHandler() {
        @Override
        public void handle(Event event) {
            if (notnull())
                if (CheckPassword())
                {
                    newUser.setUser(txfuserName.getText());
                    newUser.setUserName(txfuserName.getText());
                    newUser.setPassword(psfPassword.getText());
                    newUser.setIdRole((Role) cmbRole.getSelectionModel().getSelectedItem());
                    if (userDAO.insert(newUser))
                    {
                        Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("Se ha agregado su usuario correctamente");
                        alert1.show();
                    }
                }

        }
    };
    EventHandler EventCancel=new EventHandler() {
        @Override
        public void handle(Event event) {
            txfuserName.setText("");
            psfPassword.setText("");
            psfConfirmPassword.setText("");
            cmbRole.getSelectionModel().clearSelection();
        }
    };
    EventHandler EventExit=new EventHandler() {
        @Override
        public void handle(Event event) {
            ((Stage)(((Button) event.getSource()).getScene().getWindow())).close();
        }
    };
    private boolean CheckPassword()
    {
        if (psfPassword.getText().equals(psfConfirmPassword.getText()))
            return true;
        else {

            alert.setHeaderText("Las contrasenas no coinciden");
            alert.show();
            return false;
        }
    }
    private  boolean notnull()
    {
        if (txfuserName.getText().equals("")||psfPassword.getText().equals("")||psfConfirmPassword.getText().equals(""))
        {
            alert.setHeaderText("Por favor llene todos los campos");
            alert.show();
            return  false;
        }else  if (cmbRole.getSelectionModel().isEmpty())
        {
            alert.setHeaderText("seleccione un tipo de rol");
            alert.show();
            return false;
        }
        return true;
    }
    Stage stage=new Stage();
    public static Stage addUserStage; // Esto es para volver a mostrar la ventana
    public static FXMLLoader loaderAddUser; // Esto tambien
    private void showAddUser(Stage _addUserStage){
        try {
            addUserStage= _addUserStage;
            addUserStage.setTitle("addUser");
            Parent root= null;
            loaderAddUser = new FXMLLoader(getClass().getResource("../FXML/addUserFormat.fxml"));
            AddUserController addUserController = new AddUserController();
            loaderAddUser.setController(addUserController);
            root = loaderAddUser.load();
            Scene scene=new Scene(root, 700, 500);
            scene.getStylesheets().add("");
            addUserStage.setScene(scene);
            addUserStage.setResizable(false);
            addUserStage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
