package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_carritocompras;
import sample.Tablas.Tabla_CarritoCompras;


public class Vista_carritocompras extends Stage
{
    public Label lblIdCustumer,lblIdCart,lblSubTotal;
    public JFXTextField txtIdCustumer,txtIdCart,txtSubTotal;
    public HBox hbxCustumer,hbxIdCart,hbxSubTotal;
    public JFXButton btnAceptar,btnCancelar;
    public modelo_carritocompras objMC;
    public Tabla_CarritoCompras objT;
    public AnchorPane ancPanel;
    public Scene escena;
    public int opci = 0;


    public Vista_carritocompras(int opci, modelo_carritocompras modelo)
    {
        this.objMC = modelo;
        this.opci = opci;
        CrearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().add(getClass().getResource("/sample/css/estilo.css").toExternalForm());
        this.setScene(escena);
        this.setTitle("Carrito de compras");
        this.show();
    }

    public void CrearEscena()
    {
        ancPanel = new AnchorPane();
        hbxCustumer = new HBox();
        hbxIdCart = new HBox();
        hbxSubTotal = new HBox();

        lblIdCustumer = new Label("ID Custumer :");
        lblIdCart = new Label("ID Cart :");
        lblSubTotal = new Label("SubTotal :");

        txtIdCustumer = new JFXTextField();
        txtIdCustumer.setEditable(false);
        txtIdCustumer.setPromptText("no puede quedar vacio");

        txtIdCart = new JFXTextField();
        txtIdCart.setEditable(false);
        txtIdCart.setPromptText("no puede quedar vacio");

        txtSubTotal = new JFXTextField();

        btnAceptar = new JFXButton("Guardar");
        AnchorPane.setTopAnchor(btnAceptar,130.0);

        btnCancelar = new JFXButton("Cancelar");
        AnchorPane.setTopAnchor(btnCancelar,130.0);
        AnchorPane.setLeftAnchor(btnCancelar,100.0);

        btnAceptar.setId("button_options");
        btnCancelar.setId("button_options");
        btnAceptar.setOnAction(event -> Acciones(1));
        btnCancelar.setOnAction(event -> Acciones(2));

        hbxCustumer.getChildren().addAll(lblIdCustumer,txtIdCustumer);
        AnchorPane.setTopAnchor(hbxCustumer,15.0);
        AnchorPane.setLeftAnchor(hbxCustumer,15.0);

        hbxIdCart.getChildren().addAll(lblIdCart,txtIdCart);
        AnchorPane.setTopAnchor(hbxIdCart,50.0);
        AnchorPane.setLeftAnchor(hbxIdCart,50.0);

        hbxSubTotal.getChildren().addAll(lblSubTotal,txtSubTotal);
        AnchorPane.setTopAnchor(hbxSubTotal,95.0);
        AnchorPane.setLeftAnchor(hbxSubTotal,39.0);

        if(opci == 2)
        {
            txtIdCart.setText(String.valueOf(objMC.getIdCart()));
            txtIdCustumer.setText(String.valueOf(objMC.getIdCustomer()));
            txtSubTotal.setText(String.valueOf(objMC.getSubTotal()));

        }

        ancPanel.getChildren().addAll(hbxCustumer,hbxIdCart,hbxSubTotal,btnAceptar,btnCancelar);
    }

    private void Acciones(int opc)
    {
        if(opci == 1)
            switch (opc)
            {
                case 1:
                    objMC = new modelo_carritocompras();
                    objMC.setIdCustomer(Integer.parseInt(txtIdCustumer.getText()));
                    objMC.setIdCart(Integer.parseInt(txtIdCart.getText()));
                    objMC.setSubTotal(Float.valueOf(txtSubTotal.getText()));
                    objMC.insertar();
                case 2:
                    this.close();
            }
        else
        {
            objMC.setIdCustomer(Integer.parseInt(txtIdCustumer.getText()));
            objMC.setIdCart(Integer.parseInt(txtIdCart.getText()));
            objMC.setSubTotal(Float.valueOf(txtSubTotal.getText()));
            objMC.Actualizar();
            this.close();
        }
    }
}
