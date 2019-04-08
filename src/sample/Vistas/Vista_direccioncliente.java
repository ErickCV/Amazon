package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_direccion;
import sample.Modelos.modelo_direccioncliente;

public class Vista_direccioncliente extends Stage
{
    Label lblidCustomer,lblidAddress;
    JFXTextField txtidAddress,txtidCustomer;
    HBox hbxidCustomer,hbxidAddress,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    modelo_direccioncliente objMdC;
    //Tabla_direccioncliente objTdC;
    int opci = 0;

    public Vista_direccioncliente(int opci, modelo_direccioncliente modelo)
    {
        this.opci = opci;
        this.objMdC = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Direccion cliente");
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
        lblidCustomer = new Label("ID cliente :");
        lblidAddress = new Label("ID Direccion :");

        //TEXT-FIELD
        txtidCustomer = new JFXTextField();
        txtidCustomer.setEditable(false);

        txtidAddress = new JFXTextField();
        txtidAddress.setEditable(false);

        //HBOX
        hbxidCustomer = new HBox();
        hbxidCustomer.getChildren().addAll(lblidCustomer,txtidCustomer);
        AnchorPane.setTopAnchor(hbxidCustomer,15.0);
        AnchorPane.setLeftAnchor(hbxidCustomer,22.0);

        hbxidAddress = new HBox();
        hbxidAddress.getChildren().addAll(lblidAddress,txtidAddress);
        AnchorPane.setTopAnchor(hbxidAddress,50.0);
        AnchorPane.setLeftAnchor(hbxidAddress,7.0);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,81.0);
        AnchorPane.setLeftAnchor(hbxBotones,51.0);

        if(opci==2){
            txtidAddress.setText(String.valueOf(objMdC.getIdAddress()));
            txtidCustomer.setText(String.valueOf(objMdC.getIdCustomer()));
        }

        ancPanel.getChildren().addAll(hbxidAddress,hbxidCustomer,hbxBotones);
    }

    private void Acciones(int opc)
    {
        if(opci==1)
        switch (opc)
        {
            case 1:
                objMdC = new modelo_direccioncliente();
                objMdC.setIdCustomer(Integer.parseInt(txtidCustomer.getText()));
                objMdC.setIdAddress(Integer.parseInt(txtidAddress.getText()));
            case 2:
                this.close();
        }
        else{
            objMdC.setIdAddress(Integer.parseInt(txtidAddress.getText()));
            objMdC.setIdCustomer(Integer.parseInt(txtidCustomer.getText()));
            objMdC.Actualizar();
            this.close();
        }
    }
}
