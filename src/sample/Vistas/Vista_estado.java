package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_direccioncliente;
import sample.Modelos.modelo_estado;

public class Vista_estado extends Stage
{
    Label lblidState,lblnombre,lblidPais;
    JFXTextField txtidState,txtnombre,txtPais;
    HBox hbxidState,hbxnombre,hbxPais,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    modelo_estado objME;
    //Tabla_estado objTE;
    int opci = 0;

    public Vista_estado(int opci, modelo_estado modelo)
    {
        this.opci = opci;
        this.objME = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Estado");
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

        //LABEL
        lblidState = new Label("ID Estado :");
        lblnombre = new Label("Nombre :");
        lblidPais = new Label("ID Pais :");

        //TEXT-FIELD
        txtidState = new JFXTextField();
        txtidState.setEditable(false);

        txtnombre = new JFXTextField();
        txtPais = new JFXTextField();
        txtPais.setEditable(false);

        //HBOX
        hbxidState = new HBox();
        hbxidState.getChildren().addAll(lblidState,txtidState);
        AnchorPane.setTopAnchor(hbxidState,15.0);
        AnchorPane.setLeftAnchor(hbxidState,22.0);

        hbxnombre = new HBox();
        hbxnombre.getChildren().addAll(lblnombre,txtnombre);
        AnchorPane.setTopAnchor(hbxnombre,50.0);
        AnchorPane.setLeftAnchor(hbxnombre,7.0);

        hbxPais = new HBox();
        hbxPais.getChildren().addAll(lblidPais,txtPais);
        AnchorPane.setTopAnchor(hbxPais,81.0);
        AnchorPane.setLeftAnchor(hbxPais,51.0);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,120.0);
        AnchorPane.setLeftAnchor(hbxBotones,34.0);

        if(opci==2){
            txtidState.setText(String.valueOf(objME.getIdState()));
            txtPais.setText(String.valueOf(objME.getIdCountry()));
            txtnombre.setText(objME.getName());
        }

        ancPanel.getChildren().addAll(hbxidState,hbxnombre,hbxPais,hbxBotones);

    }

    private void Acciones(int i)
    {
        if(opci==1)
        switch (i)
        {
            case 1:
                objME = new modelo_estado();
                objME.setIdState(Integer.parseInt(txtidState.getText()));
                objME.setName(txtnombre.getText());
                objME.setIdCountry(Integer.parseInt(txtPais.getText()));
                objME.insertar();
            case 2:
                this.close();
        }
        else{
            objME.setIdCountry(Integer.parseInt(txtPais.getText()));
            objME.setIdState(Integer.parseInt(txtidState.getText()));
            objME.setName(txtnombre.getText());
            objME.Actualizar();
        }
    }
}
