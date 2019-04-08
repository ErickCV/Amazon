package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_ciudad;
import sample.Tablas.Tabla_Ciudad;

public class Vista_ciudad extends Stage
{
    Label lblidCiudad,lblnombre,lblidCountry,lblstate;
    JFXTextField txtidCiudad,txtnombre,txtidCountry,txtstate;
    JFXButton btnAceptar,btnCancelar;
    HBox hbxidCiudad,hbxnombre,hbxCountry,hbxState,hbxBotones;
    Scene escena;
    AnchorPane ancPanel;
    modelo_ciudad objMC;
    Tabla_Ciudad objTC;
    int opci = 0;

    public Vista_ciudad(int opci, modelo_ciudad modelo)
    {
        this.objMC = modelo;
        this.opci = opci;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Registro");
        this.show();
    }

    public void crearEscena()
    {
        ancPanel = new AnchorPane();

        //BUTTONS
        btnAceptar = new JFXButton("Guardar");

        btnCancelar = new JFXButton("Cancelar");

        //btnAceptar.setOnAction(event -> Acciones(1));
        btnCancelar.setOnAction(event -> Acciones(2));

        //LABELS
        lblidCiudad = new Label("ID Ciudad :");
        lblnombre = new Label("Nombre :");
        lblidCountry = new Label("ID Pais :");
        lblstate = new Label("ID Estado :");

        //TEXTFIELD
        txtidCiudad = new JFXTextField();
        txtidCiudad.setPromptText("NOT null");

        txtnombre = new JFXTextField();

        txtidCountry = new JFXTextField();
        txtidCountry.setPromptText("NOT null");

        txtstate = new JFXTextField();
        txtstate.setPromptText("NOT null");

        //HBOX
        hbxidCiudad = new HBox();
        hbxidCiudad.getChildren().addAll(lblidCiudad,txtidCiudad);
        AnchorPane.setTopAnchor(hbxidCiudad,15.0);
        AnchorPane.setLeftAnchor(hbxidCiudad,20.0);
        hbxidCiudad.setSpacing(10.0);

        hbxnombre = new HBox();
        hbxnombre.getChildren().addAll(lblnombre,txtnombre);
        AnchorPane.setTopAnchor(hbxnombre,50.0);
        AnchorPane.setLeftAnchor(hbxnombre,30.0);
        hbxnombre.setSpacing(10.0);

        hbxCountry = new HBox();
        hbxCountry.getChildren().addAll(lblidCountry,txtidCountry);
        AnchorPane.setTopAnchor(hbxCountry,85.0);
        AnchorPane.setLeftAnchor(hbxCountry,38.0);
        hbxCountry.setSpacing(11.0);

        hbxState = new HBox();
        hbxState.getChildren().addAll(lblstate,txtstate);
        AnchorPane.setTopAnchor(hbxState,120.0);
        AnchorPane.setLeftAnchor(hbxState,23.0);
        hbxState.setSpacing(11.0);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,155.0);
        AnchorPane.setLeftAnchor(hbxBotones,15.0);

        if(opci==2){
            txtidCiudad.setText(String.valueOf(objMC.getIdCity()));
            txtidCountry.setText(String.valueOf(objMC.getIdCountry()));
            txtstate.setText(String.valueOf(objMC.getIdState()));
            txtnombre.setText(objMC.getName());
        }

        ancPanel.getChildren().addAll(hbxidCiudad,hbxnombre,hbxCountry,hbxState,hbxBotones);
    }

    private void Acciones(int opc)
    {
        if(opci==1)
        switch (opc)
        {
            case 1:
                objMC = new modelo_ciudad();
                objMC.setIdCountry(Integer.parseInt(txtidCountry.getText()));
                objMC.setIdCity(Integer.parseInt(txtidCiudad.getText()));
                objMC.setIdState(Integer.parseInt(txtstate.getText()));
                objMC.setName(txtnombre.getText());
                objMC.insertar();
            case 2:
                this.close();
        }
        else {
            objMC.setIdCountry(Integer.parseInt(txtidCountry.getText()));
            objMC.setIdCity((Integer.parseInt(txtidCiudad.getText())));
            objMC.setIdState((Integer.parseInt(txtstate.getText())));
            objMC.setName(txtnombre.getText());
            objMC.Actualizar();
        }
    }

}
