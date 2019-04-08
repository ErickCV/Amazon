package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_usuarios;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Signup extends Stage {
    private Scene scene;
    private VBox vBox1;
    private ImageView imageView1;
    private Image image1;
    private JFXTextField textFieldUsuario;
    private JFXPasswordField textFieldContraseña;
    private JFXButton buttonCrear,buttonEntrar;
    private Label or;

    public Signup(){
        crearGUI();
        scene = new Scene(vBox1,270,350);
        scene.getStylesheets().add("/sample/css/estilo.css");
        this.setTitle("SignUp");
        this.setScene(scene);
        this.show();
    }

    public void crearGUI(){
        vBox1 = new VBox();
        textFieldContraseña = new JFXPasswordField();
        textFieldContraseña.setPromptText("Contraseña");
        textFieldContraseña.setMaxSize(200,20);
        textFieldUsuario = new JFXTextField();
        textFieldUsuario.setPromptText("Nombre de suario");
        textFieldUsuario.setMaxSize(200,20);
        buttonCrear = new JFXButton("Crear Usuario");
        buttonCrear.setId("button_options");
        buttonEntrar = new JFXButton("Iniciar Sesion");
        buttonEntrar.setId("button_options");
        image1 = new Image("/sample/Imagenes/usuario.png");
        imageView1 = new ImageView(image1);
        imageView1.setId("imv");
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(100);
        or = new Label("O");
        or.setId("label_blancos");
        vBox1.getChildren().addAll(imageView1,textFieldUsuario,textFieldContraseña,buttonCrear,or,buttonEntrar);
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);
        buttonCrear.setOnAction(event -> Accion(1));
        buttonEntrar.setOnAction(event -> Accion(2));
    }

    private void Accion(int i) {
        try {
            switch (i) {
                case 1:
                    modelo_usuarios objS = new modelo_usuarios();
                    objS.setNombre(textFieldUsuario.getText());
                    objS.setContra(textFieldContraseña.getText());
                    objS.setRol(2);
                    objS.Insertar();
                    textFieldUsuario.clear();
                    textFieldContraseña.clear();

                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);//WARNING,ERROR, CONFIRMATION ETC. CAMBIA EL ICONO
                    alerta.setTitle("Information dialog");//PARA ELIMINARLOS SE DEBE PONER (null)
                    alerta.setHeaderText(null);
                    alerta.setContentText("You've been added successfully");
                    alerta.showAndWait();
                    this.close();
                    Login objLo = new Login();
                    break;
                case 2:
                    this.close();
                    Login objL = new Login();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Dialog");
            alert.setHeaderText("Exception Dialog");
            alert.setContentText("Could not find file ");

            Exception ex = new FileNotFoundException("Could not find file");

// Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);


            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        }
    }

}
