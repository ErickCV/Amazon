package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_contenidopaquete;

public class Vista_contenidopaquete extends Stage
{
    Label lblidPackage,lbldetalles,lblidProducto,lblcantidad;
    JFXTextField txtidPackage,txtdetalles,txtidProducto,txtcantidad;
    HBox hbxidPackage,hbxdetalles,hbxidproducto,hbxcantidad,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    modelo_contenidopaquete objMC;
    //Tabla_contenidopaquete objTC;
    int opci= 0;

    public Vista_contenidopaquete(int opci, modelo_contenidopaquete modelo)
    {
        this.opci = opci;
        this.objMC = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Contenido de paquete");
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
        lblidPackage = new Label("ID paquete :");
        lbldetalles = new Label("Detalles :");
        lblidProducto = new Label("ID producto :");
        lblcantidad = new Label("Cantidad :");

        //TEXT-FIELD
        txtidPackage = new JFXTextField();
        txtidPackage.setPromptText("NOT null");

        txtdetalles = new JFXTextField();

        txtidProducto = new JFXTextField();
        txtidProducto.setPromptText("NOT null");
        txtidProducto.setEditable(false);

        txtcantidad = new JFXTextField();

        //HBOX
        hbxidPackage = new HBox();
        hbxidPackage.getChildren().addAll(lblidPackage,txtidPackage);
        hbxidPackage.setSpacing(5.0);
        AnchorPane.setTopAnchor(hbxidPackage,15.0);
        AnchorPane.setLeftAnchor(hbxidPackage,15.0);

        hbxdetalles = new HBox();
        hbxdetalles.getChildren().addAll(lbldetalles,txtdetalles);
        hbxdetalles.setSpacing(5.0);
        AnchorPane.setTopAnchor(hbxdetalles,50.0);
        AnchorPane.setLeftAnchor(hbxdetalles,31.0);

        hbxidproducto = new HBox();
        hbxidproducto.getChildren().addAll(lblidProducto,txtidProducto);
        hbxidproducto.setSpacing(5.0);
        AnchorPane.setTopAnchor(hbxidproducto,85.0);
        AnchorPane.setLeftAnchor(hbxidproducto,10.0);

        hbxcantidad = new HBox();
        hbxcantidad.getChildren().addAll(lblcantidad,txtcantidad);
        hbxcantidad.setSpacing(5.0);
        AnchorPane.setTopAnchor(hbxcantidad,120.0);
        AnchorPane.setLeftAnchor(hbxcantidad,26.0);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,155.0);
        AnchorPane.setLeftAnchor(hbxBotones,15.0);

        if(opci==2){
            txtidProducto.setText(String.valueOf(objMC.getIdProduct()));
            txtidPackage.setText(String.valueOf(objMC.getIdPackage()));
            txtcantidad.setText(String.valueOf(objMC.getQuantity()));
            txtdetalles.setText(objMC.getDetail());
        }

        ancPanel.getChildren().addAll(hbxidPackage,hbxdetalles,hbxidproducto,hbxcantidad,hbxBotones);
    }

    private void Acciones(int opc)
    {
        if(opci==1)
        switch (opc)
        {
            case 1:
                objMC = new modelo_contenidopaquete();
                objMC.setIdPackage(Integer.parseInt(txtidPackage.getText()));
                objMC.setDetail(txtdetalles.getText());
                objMC.setIdProduct(Integer.parseInt(txtidProducto.getText()));
                objMC.setQuantity(Integer.parseInt(txtcantidad.getText()));
                objMC.insertar();
            case 2:
                this.close();
        }
        else{
            objMC.setIdPackage(Integer.parseInt(txtidPackage.getText()));
            objMC.setIdProduct(Integer.parseInt(txtidProducto.getText()));
            objMC.setQuantity(Integer.parseInt(txtcantidad.getText()));
            objMC.setDetail(txtdetalles.getText());
        }

    }
}
