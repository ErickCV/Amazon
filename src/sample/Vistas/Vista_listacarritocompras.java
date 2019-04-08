package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_listacarritocompras;

public class Vista_listacarritocompras extends Stage
{
    Label lblidCart,lblidCliente,lblDetalles,lblidProducto,lblCantProducto,lblidpaquete,lblCantPaquete;
    JFXTextField txtidCart,txtidCliente,txtDetaller,txtidProducto,txtCantProducto,txtidPaquete,txtCantPaquete;
    HBox hbxidCart,hbxidCliente,hbxdetalles,hbxidProductos,hbxCanProducto,hbxidPaquete,hbxlblCanPaquete,hbxBotones;
    JFXButton btnAceptar,btnCancelar;
    AnchorPane ancPanel;
    Scene escena;
    modelo_listacarritocompras objMLCC;
    int opci = 0;
    //Tabla_listacarritocompras objTMLCC;

    public Vista_listacarritocompras(int opci, modelo_listacarritocompras modelo)
    {
        this.opci= opci;
        this.objMLCC = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,400);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Lista Carrito");
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
        lblidCart = new Label("ID Carrito :");
        lblidCliente = new Label("ID Cliente");
        lblDetalles = new Label("Detalles :");
        lblidProducto = new Label("ID Producto :");
        lblCantProducto = new Label("Cantidad del producto :");
        lblidpaquete = new Label("ID Paquete :");
        lblCantPaquete = new Label("Cantidad de paquetes");

        //TEXT-FIELD

        txtidCart = new JFXTextField();
        txtidCart.setEditable(false);

        txtidCliente = new JFXTextField();
        txtidCliente.setEditable(false);

        txtDetaller = new JFXTextField();
        txtidProducto = new JFXTextField();
        txtidProducto.setEditable(false);

        txtCantProducto = new JFXTextField();
        txtidPaquete = new JFXTextField();
        txtidPaquete.setEditable(false);
        txtCantPaquete = new JFXTextField();

        //HBOX
        hbxidCart = new HBox();
        hbxidCart.getChildren().addAll(lblidCart,txtidCart);
        AnchorPane.setTopAnchor(hbxidCart,15.0);

        hbxidCliente = new HBox();
        hbxidCliente.getChildren().addAll(lblidCliente,txtidCliente);

        hbxdetalles = new HBox();
        hbxdetalles.getChildren().addAll(lblDetalles,txtDetaller);

        hbxidProductos = new HBox();
        hbxidProductos.getChildren().addAll(lblidProducto,txtidProducto);

        hbxCanProducto = new HBox();
        hbxCanProducto.getChildren().addAll(lblCantProducto,txtCantProducto);

        hbxidPaquete = new HBox();
        hbxidPaquete.getChildren().addAll(lblidpaquete,txtidPaquete);

        hbxlblCanPaquete = new HBox();
        hbxlblCanPaquete.getChildren().addAll(lblCantPaquete,txtCantPaquete);

        hbxBotones = new HBox();
        hbxBotones.getChildren().addAll(btnAceptar,btnCancelar);
        AnchorPane.setTopAnchor(hbxBotones,155.0);
        AnchorPane.setLeftAnchor(hbxBotones,15.0);

        if(opci==2){
            txtidCart.setText(String.valueOf(objMLCC.getIdCart()));
            txtidCliente.setText(String.valueOf(objMLCC.getIdCustomer()));
            txtidPaquete.setText(String.valueOf(objMLCC.getIdPackage()));
            txtidProducto.setText(String.valueOf(objMLCC.getIdProduct()));
            txtCantPaquete.setText(String.valueOf(objMLCC.getQuantityPackage()));
            txtCantProducto.setText(String.valueOf(objMLCC.getQuantityProduct()));
            txtDetaller.setText(objMLCC.getDetail());
        }

        ancPanel.getChildren().addAll(hbxidCart,hbxidCliente,hbxdetalles,hbxidProductos,hbxCanProducto,hbxidPaquete,hbxlblCanPaquete,hbxBotones);
    }

    private void Acciones(int i)
    {
        if(opci== 1)
            switch (i)
            {
                case 1:
                    objMLCC = new modelo_listacarritocompras();
                    objMLCC.setIdCart(Integer.parseInt(txtidCart.getText()));
                    objMLCC.setIdCustomer(Integer.parseInt(txtidCliente.getText()));
                    objMLCC.setDetail(txtDetaller.getText());
                    objMLCC.setIdProduct(Integer.parseInt(txtidProducto.getText()));
                    objMLCC.setQuantityProduct(Integer.parseInt(txtCantProducto.getText()));
                    objMLCC.setIdPackage(Integer.parseInt(txtidPaquete.getText()));
                    objMLCC.setQuantityPackage(Integer.parseInt(txtCantPaquete.getText()));
                    objMLCC.insertar();
                case 2:
                    this.close();
            }
        else
        {
            objMLCC.setIdProduct(Integer.parseInt(txtidProducto.getText()));
            objMLCC.setIdCart(Integer.parseInt(txtidCart.getText()));
            objMLCC.setIdPackage(Integer.parseInt(txtidPaquete.getText()));
            objMLCC.setIdCustomer(Integer.parseInt(txtidCliente.getText()));
            objMLCC.setQuantityProduct(Integer.parseInt(txtCantProducto.getText()));
            objMLCC.setQuantityPackage(Integer.parseInt(txtCantPaquete.getText()));
            objMLCC.setDetail(txtDetaller.getText());
            objMLCC.Actualizar();
        }
    }
}
