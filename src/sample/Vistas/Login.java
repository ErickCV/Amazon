package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.ConexionBD;
import sample.Modelos.modelo_consulta4;
import sample.Modelos.modelo_carritocompras;
import sample.Modelos.modelo_consulta;
import sample.Modelos.modelo_usuarios;
import sample.Modelos.modelo_cliente;
import sample.TDAs.Customers;

import java.sql.Connection;
import java.sql.SQLException;

public class Login extends Stage {

    private Scene scene;
    private VBox vBox1;
    private ImageView imageView1;
    private Image image1;
    private JFXTextField textFieldUsuario;
    private JFXPasswordField textFieldContraseña;
    private JFXButton buttonEntrar,buttonCrear;
    private Label or;
    private modelo_usuarios objS = new modelo_usuarios();

    ConexionBD objC;
    Connection con;

    public Login(){
        crearLogin();
        scene = new Scene(vBox1,270,350);
        scene.getStylesheets().add("/sample/css/estilo.css");
        this.setScene(scene);
        this.setTitle("LogIn");
        this.show();
    }
    public void crearLogin(){
        vBox1 = new VBox();
        image1 = new Image("sample/Imagenes/usuario.png");

        imageView1 = new ImageView(image1);
        imageView1.setId("imv");
        imageView1.setFitWidth(100);
        imageView1.setFitHeight(100);
        textFieldUsuario = new JFXTextField();
        textFieldUsuario.setPromptText("Nombre de usuario");
        textFieldUsuario.setMaxSize(200,20);
        textFieldContraseña = new JFXPasswordField();
        textFieldContraseña.setPromptText("Contraseña");
        textFieldContraseña.setMaxSize(200,20);
        buttonEntrar = new JFXButton("Iniciar Sesion");
        buttonEntrar.setId("button_options");
        or = new Label("O");
        or.setId("label_blancos");
        buttonCrear = new JFXButton("Crear Usuario");
        buttonCrear.setId("button_options");
        vBox1.getChildren().addAll(imageView1,textFieldUsuario,textFieldContraseña,buttonEntrar,or,buttonCrear);
        vBox1.setSpacing(10);
        vBox1.setAlignment(Pos.CENTER);
        buttonEntrar.setOnAction(event -> Acceder(1));
        buttonCrear.setOnAction(event -> Acceder(2));
    }

    private void Acceder(int opc) {
        switch (opc) {
            case 1:


                modelo_usuarios objM = new modelo_usuarios();
                modelo_consulta objMC = new modelo_consulta();
                modelo_cliente objCLI = new modelo_cliente();

                //objCLI.setName(textFieldUsuario.getText());
               // objCLI.Listar();
                //try {
                //    objCLI.BuscarCustomer(objCLI.getIdCustomer());
               // } catch (SQLException e) {
                //    e.printStackTrace();
               // }

                MenuAsUser objMAU = new MenuAsUser();
                objMAU.setNombre(textFieldUsuario.getText());

                objMC.setName(textFieldUsuario.getText());
                objMC.setPass(textFieldContraseña.getText());

                objM.setNombre(textFieldUsuario.getText());
                objM.setContra(textFieldContraseña.getText());



                if(textFieldUsuario.getText().equals("Erick"))
                    if(textFieldContraseña.getText().equals("itgd"))
                        if (objM.consultas()==1)
                         {
                            this.close();
                            System.out.println("valor despues de == 1"+objM.valor);
                            MenuAsCustomer objMe = new MenuAsCustomer(textFieldUsuario.getText(), textFieldContraseña.getText());
                        }
                        else
                            {
                                textFieldUsuario.clear();
                                textFieldContraseña.clear();
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error message");
                                alert.setHeaderText("Something went wrong");
                                alert.setContentText("User or password incorrect");

                                alert.showAndWait();
                            }
                     else
                         {
                             this.close();

                        MenuAsUser objMAU2 = new MenuAsUser(textFieldUsuario.getText(), textFieldContraseña.getText());
                    }
                     else
                         {
                             this.close();
                    MenuAsUser objMAU2 = new MenuAsUser(textFieldUsuario.getText(), textFieldContraseña.getText());
                }
                break;
            case 2:
                this.close();
                //Signup objS = new Signup();
                Vista_cliente objVC =  new Vista_cliente();
        }

    }
}

