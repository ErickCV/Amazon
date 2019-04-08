package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_cliente;

public class Vista_cliente extends Stage
{

    Label lblidCustumer,lblnombre,lbllastName,lblgenero;
    JFXTextField txtidCustumer,txtnombre,txtlastName;
    JFXRadioButton rdbMasculino,rdbFemenino;
    HBox hbxidCustumer,hbxnombre,hbxlastName,hbxgenero,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    VBox vBox1;
    final ToggleGroup group = new ToggleGroup();
    modelo_cliente objMC;
    int opci = 0;
    //Tabla_clientes objTC;

    public Vista_cliente(int opci,modelo_cliente modelo)
    {
        this.opci = opci;
        this.objMC = modelo;
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

        //btnAceptar.setOnAction(event -> Acciones(1));
        btnCancelar.setOnAction(event -> Acciones(2));

        //LABELS
        lblidCustumer = new Label("ID cliente :");
        lblnombre = new Label("Nombre :");
        lbllastName = new Label("Apellidos :");
        lblgenero = new Label("Genero :");

        //TEXTFIELD
        txtidCustumer = new JFXTextField();
        txtidCustumer.setPromptText("NOT null");

        txtnombre = new JFXTextField();

        txtlastName = new JFXTextField();

        vBox1 = new VBox();

        //RADIO-BUTTON
        rdbMasculino = new JFXRadioButton("Masculino");
        rdbMasculino.setToggleGroup(group);
        rdbMasculino.setSelected(true);
        rdbFemenino = new JFXRadioButton("Femenino");
        rdbFemenino.setToggleGroup(group);

        //HBOX

        hbxidCustumer = new HBox();
        hbxidCustumer.getChildren().addAll(lblidCustumer,txtidCustumer);
        AnchorPane.setTopAnchor(hbxidCustumer,15.0);
        AnchorPane.setLeftAnchor(hbxidCustumer,20.0);
        hbxidCustumer.setSpacing(10.0);

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

        vBox1.getChildren().addAll(rdbMasculino,rdbFemenino);
        vBox1.setSpacing(10);

        hbxgenero = new HBox();
        hbxgenero.getChildren().addAll(lblgenero,vBox1);
        AnchorPane.setTopAnchor(hbxgenero,120.0);
        AnchorPane.setLeftAnchor(hbxgenero,20.0);
        hbxgenero.setSpacing(10);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,170.0);
        AnchorPane.setLeftAnchor(hbxBotones,15.0);
        hbxBotones.setSpacing(10);

        if(opci==2){
            txtidCustumer.setText(String.valueOf(objMC.getIdCustomer()));
            txtlastName.setText(objMC.getLastName());
            txtnombre.setText((objMC.getName()));
            if(objMC.getGender().equals("Masculino")){
                rdbMasculino.setSelected(true);
            }
            else
                rdbFemenino.setSelected(true);
        }

        ancPanel.getChildren().addAll(hbxidCustumer,hbxnombre,hbxlastName,hbxgenero,hbxBotones);
    }

    private void Acciones(int opc)
    {
        if(opci==1)
        switch (opc)
        {
            case 1:
                objMC = new modelo_cliente();
                objMC.setIdCustomer(Integer.parseInt(txtidCustumer.getText()));
                objMC.setName(txtnombre.getText());
                objMC.setLastName(txtlastName.getText());
                objMC.setGender(group.getSelectedToggle().toString());
                objMC.insertar();
            case 2:
                this.close();
        }
        else{
            objMC.setIdCustomer(Integer.parseInt(txtidCustumer.getText()));
            objMC.setName(txtnombre.getText());
            objMC.setLastName(txtlastName.getText());
            if(objMC.getGender().equals("Masculino")){
                objMC.setGender("Masculino");
            }
            else
                objMC.setGender("Femenino");
            objMC.Actualizar();
        }
    }
}
