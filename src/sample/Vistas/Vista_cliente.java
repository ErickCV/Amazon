package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_cliente;

public class Vista_cliente extends Stage
{

    Label lblnombre,lbllastName,lblgenero,lbclave;
    JFXTextField txtnombre,txtlastName;
    JFXPasswordField txtclave;
    JFXRadioButton rdbMasculino,rdbFemenino;
    HBox hbxclave,hbxnombre,hbxlastName,hbxgenero,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    VBox vBox1;//configurar cuado de texto para password
    final ToggleGroup group = new ToggleGroup();
    modelo_cliente objMC;
    int opci = 0;
    //Tabla_clientes objTC;

    public Vista_cliente()
    {
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Cliente");
        this.show();
    }

    private void crearEscena()
    {
        ancPanel = new AnchorPane();

        //BUTTONS
        btnAceptar = new JFXButton("Guardar");

        btnCancelar = new JFXButton("Cancelar");

        btnAceptar.setOnAction(event -> Acciones(1));
        btnCancelar.setOnAction(event -> Acciones(2));

        //LABELS
        lbclave = new Label("Clave :");
        lblnombre = new Label("Nombre :");
        lbllastName = new Label("Apellidos :");
        lblgenero = new Label("Genero :");

        //TEXTFIELD
        txtclave = new JFXPasswordField();

        txtnombre = new JFXTextField();

        txtlastName = new JFXTextField();

        vBox1 = new VBox();

        //RADIO-BUTTON
        rdbMasculino = new JFXRadioButton("Masculino");
        rdbMasculino.setToggleGroup(group);
        rdbMasculino.setUserData("M");
        rdbMasculino.setSelected(true);
        rdbFemenino = new JFXRadioButton("Femenino");
        rdbFemenino.setToggleGroup(group);
        rdbFemenino.setUserData("F");

        //HBOX


        hbxnombre = new HBox();
        hbxnombre.getChildren().addAll(lblnombre,txtnombre);
        AnchorPane.setTopAnchor(hbxnombre,50.0);
        AnchorPane.setLeftAnchor(hbxnombre,20.0);
        hbxnombre.setSpacing(10.0);

        hbxlastName = new HBox();
        hbxlastName.getChildren().addAll(lbllastName,txtlastName);
        AnchorPane.setTopAnchor(hbxlastName,85.0);
        AnchorPane.setLeftAnchor(hbxlastName,20.0);
        hbxlastName.setSpacing(10.0);

        hbxclave = new HBox();
        hbxclave.getChildren().addAll(lbclave,txtclave);
        AnchorPane.setTopAnchor(hbxclave,120.0);
        AnchorPane.setLeftAnchor(hbxclave,20.0);
        hbxclave.setSpacing(10.0);

        vBox1.getChildren().addAll(rdbMasculino,rdbFemenino);
        vBox1.setSpacing(10);

        hbxgenero = new HBox();
        hbxgenero.getChildren().addAll(lblgenero,vBox1);
        AnchorPane.setTopAnchor(hbxgenero,170.0);
        AnchorPane.setLeftAnchor(hbxgenero,20.0);
        hbxgenero.setSpacing(10);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,250.0);
        AnchorPane.setLeftAnchor(hbxBotones,20.0);
        hbxBotones.setSpacing(10);

        ancPanel.getChildren().addAll(hbxnombre,hbxlastName,hbxclave,hbxgenero,hbxBotones);
    }

    private void Acciones(int opc)
    {
        switch (opc)
        {
            case 1:
                objMC = new modelo_cliente();
                objMC.setName(txtnombre.getText());
                objMC.setLastName(txtlastName.getText());
                objMC.setGender(group.getSelectedToggle().getUserData().toString());
                objMC.setClave(txtclave.getText());
                objMC.insertar();
                MenuAsCustomer objMe = new MenuAsCustomer(txtnombre.getText(), txtclave.getText());
            case 2:
                this.close();
        }
    }
}
