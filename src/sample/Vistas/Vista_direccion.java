package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_contenidopaquete;
import sample.Modelos.modelo_direccion;

public class Vista_direccion extends Stage
{
    Label lblidAdress,lblCalle,lblemail,lbltelefono,lblNomComer,lblidCity,lblEstado,lblPais,lblCP;
    JFXTextField txtidAdress,txtCalle,txtemail,txttelefono,txtNomComer,txtCity,txtEstado,txtPais,txtCP;
    HBox hbxidAdress,hbxCalle,hbxemail,hbxtelefono,hbxNomComer,hbxCity,hbxEstado,hbxPais,hbxCP,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    modelo_direccion objMD;
    //Tabla_direccion objTC;
    int opci =0;

    public Vista_direccion(int opci, modelo_direccion modelo)
    {
        this.opci = opci;
        this.objMD = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Direccion");
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
        lblidAdress= new Label("ID direccion :");
        lblCalle = new Label("Calle :");
        lblemail = new Label("Email :");
        lbltelefono = new Label("Telefono :");
        lblNomComer = new Label("Nombre del comercio :");
        lblidCity = new Label("ID ciudad :");
        lblEstado = new Label("ID Estado :");
        lblPais = new Label("ID Pais :");
        lblCP = new Label("Codigo Postal :");

        //TEXT-FIELDS
        txtidAdress = new JFXTextField();
        txtidAdress.setPromptText("NOT null");

        txtCalle = new JFXTextField();
        txtemail = new JFXTextField();
        txttelefono = new JFXTextField();
        txtNomComer = new JFXTextField();
        txtCity = new JFXTextField();
        txtCity.setPromptText("NOT null");

        txtEstado = new JFXTextField();
        txtEstado.setPromptText("NOT null");

        txtPais = new JFXTextField();
        txtPais.setPromptText("NOT null");
        txtCP = new JFXTextField();

        //HBOX
        hbxidAdress = new HBox();
        hbxidAdress.getChildren().addAll(lblidAdress,txtidAdress);
        AnchorPane.setTopAnchor(hbxidAdress,15.0);
        AnchorPane.setLeftAnchor(hbxidAdress,15.0);

        hbxCalle = new HBox();
        hbxCalle.getChildren().addAll(lblCalle,txtCalle);
        AnchorPane.setTopAnchor(hbxCalle,50.0);
        AnchorPane.setLeftAnchor(hbxCalle,52.0);

        hbxemail = new HBox();
        hbxemail.getChildren().addAll(lblemail,txtemail);
        AnchorPane.setTopAnchor(hbxemail,85.0);
        AnchorPane.setLeftAnchor(hbxemail,51.0);

        hbxtelefono = new HBox();
        hbxtelefono.getChildren().addAll(lbltelefono,txttelefono);
        AnchorPane.setTopAnchor(hbxtelefono,120.0);
        AnchorPane.setLeftAnchor(hbxtelefono,34.0);

        hbxNomComer = new HBox();
        hbxNomComer.getChildren().addAll(lblNomComer,txtNomComer);
        AnchorPane.setTopAnchor(hbxNomComer,155.0);
        AnchorPane.setLeftAnchor(hbxNomComer,15.0);

        hbxCity = new HBox();
        hbxCity.getChildren().addAll(lblidCity,txtCity);
        AnchorPane.setTopAnchor(hbxCity,190.0);
        AnchorPane.setLeftAnchor(hbxCity,31.0);

        hbxEstado = new HBox();
        hbxEstado.getChildren().addAll(lblEstado,txtEstado);
        AnchorPane.setTopAnchor(hbxEstado,225.0);
        AnchorPane.setLeftAnchor(hbxEstado,31.0);

        hbxPais = new HBox();
        hbxPais.getChildren().addAll(lblPais,txtPais);
        AnchorPane.setTopAnchor(hbxPais,260.0);
        AnchorPane.setLeftAnchor(hbxPais,50.0);

        hbxCP = new HBox();
        hbxCP.getChildren().addAll(lblCP,txtCP);
        AnchorPane.setTopAnchor(hbxCP,295.0);
        AnchorPane.setLeftAnchor(hbxCP,10.0);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,330.0);
        AnchorPane.setLeftAnchor(hbxBotones,15.0);

        if(opci==2){
            txtidAdress.setText(String.valueOf(objMD.getIdAddress()));
            txtCity.setText(String.valueOf(objMD.getIdCity()));
            txtEstado.setText(String.valueOf(objMD.getIdState()));
            txtPais.setText(String.valueOf(objMD.getIdCountry()));
            txtCalle.setText((objMD.getStreet()));
            txtNomComer.setText((objMD.getNameCommerce()));
            txtCP.setText(String.valueOf(objMD.getCp()));
            txtemail.setText(objMD.getEmail());
            txttelefono.setText(objMD.getPhone());
        }

        ancPanel.getChildren().addAll(hbxidAdress,hbxCalle,hbxemail,hbxtelefono,hbxNomComer,hbxCity,hbxEstado,hbxPais,hbxCP,hbxBotones);
    }

    private void Acciones(int i)
    {
        if(opci==1)
        switch (i)
        {
            case 1:
                objMD = new modelo_direccion();
                objMD.setIdAddress(Integer.parseInt(txtidAdress.getText()));
                objMD.setStreet(txtCalle.getText());
                objMD.setEmail(txtemail.getText());
                objMD.setEmail(txttelefono.getText());
                objMD.setNameCommerce(txtNomComer.getText());
                objMD.setIdCity(Integer.parseInt(txtCity.getText()));
                objMD.setIdState(Integer.parseInt(txtEstado.getText()));
                objMD.setIdCountry(Integer.parseInt(txtPais.getText()));
                objMD.setCp(Integer.parseInt(txtCP.getText()));
                objMD.insertar();
            case 2:
                this.close();
        }
        else{
            objMD.setIdAddress(Integer.parseInt(txtidAdress.getText()));
            objMD.setIdState(Integer.parseInt(txtEstado.getText()));
            objMD.setIdCountry(Integer.parseInt(txtPais.getText()));
            objMD.setIdCity(Integer.parseInt(txtCity.getText()));
            objMD.setPhone((txttelefono.getText()));
            objMD.setEmail(txtemail.getText());
            objMD.setCp(Integer.parseInt(txtCP.getText()));
            objMD.setNameCommerce(objMD.getNameCommerce());
            objMD.Actualizar();

        }

    }
}

