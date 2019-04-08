package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_factura;

import java.sql.Date;

public class Vista_factura extends Stage
{
    Label lblidFactura,lblidVenta,lblFecha,lblidUsuario;
    JFXTextField txtidFactura,txtidventa,txtidUsuarios;
    JFXDatePicker dtpFecha;
    HBox hbxidFactura,hbxidVenta,hbxFecha,hbxidUsuario,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    modelo_factura objMF;
    //Tabla_fcatura objTE;
    int opci;

    public Vista_factura(int opci, modelo_factura modelo)
    {
        this.opci=opci;
        this.objMF = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Facturas");
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
        lblidFactura = new Label("ID factura :");
        lblidVenta = new Label("ID venta :");
        lblFecha = new Label("Fecha :");
        lblidUsuario = new Label("ID Usuario :");

        //TEXT-FIELD
        txtidFactura = new JFXTextField();
        txtidFactura.setPromptText("NOT null");

        txtidventa = new JFXTextField();
        txtidventa.setPromptText("NOT null");

        txtidUsuarios = new JFXTextField();
        txtidUsuarios.setPromptText("NOT null");

        //DATE-PICKER
        dtpFecha = new JFXDatePicker();

        //HBOX
        hbxidFactura = new HBox();
        hbxidFactura.getChildren().addAll(lblidFactura,txtidFactura);
        AnchorPane.setTopAnchor(hbxidFactura,15.0);
        AnchorPane.setLeftAnchor(hbxidFactura,22.0);

        hbxidVenta = new HBox();
        hbxidVenta.getChildren().addAll(lblidVenta,txtidventa);
        AnchorPane.setTopAnchor(hbxidVenta,50.0);
        AnchorPane.setLeftAnchor(hbxidVenta,30.0);

        hbxFecha = new HBox();
        hbxFecha.getChildren().addAll(lblFecha,dtpFecha);
        AnchorPane.setTopAnchor(hbxFecha,81.0);
        AnchorPane.setLeftAnchor(hbxFecha,44.0);


        hbxidUsuario = new HBox();
        hbxidUsuario.getChildren().addAll(lblidUsuario,txtidUsuarios);
        AnchorPane.setTopAnchor(hbxidUsuario,120.0);
        AnchorPane.setLeftAnchor(hbxidUsuario,20.0);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,155.0);
        AnchorPane.setLeftAnchor(hbxBotones,15.0);

        if (opci == 2) {
            txtidUsuarios.setText(String.valueOf(objMF.getIdUser()));
            txtidFactura.setText(String.valueOf(objMF.getIdInvoice()));
            txtidventa.setText(String.valueOf(objMF.getIdSale()));
        }

        ancPanel.getChildren().addAll(hbxidFactura,hbxidVenta,hbxFecha,hbxidUsuario,hbxBotones);
    }

    private void Acciones(int i)
    {
        if(opci==1)
        switch (i)
        {
            case 1:
                objMF = new modelo_factura();
                objMF.setIdInvoice(Integer.parseInt(txtidFactura.getText()));
                objMF.setIdSale(Integer.parseInt(txtidUsuarios.getText()));
                //objMF.setDateInvoice();
                objMF.setIdUser(Integer.parseInt(txtidUsuarios.getText()));
                objMF.insertar();
            case 2:
                this.close();
        }
        else{
            objMF.setIdUser(Integer.parseInt(txtidUsuarios.getText()));
            objMF.setIdSale(Integer.parseInt(txtidventa.getText()));
            objMF.setIdInvoice(Integer.parseInt(txtidFactura.getText()));
            objMF.Actualizar();
        }
    }
}
