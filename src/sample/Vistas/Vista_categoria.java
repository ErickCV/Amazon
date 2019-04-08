package sample.Vistas;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_categoria;

public class Vista_categoria extends Stage
{
    public Label lblidCategoria,lblNombre,lblDescripcion;
    public JFXTextField txtidCategoria,txtNombre,txaDescrip;
    public ImageView imvCategoria;
    public AnchorPane ancPanel;
    public HBox hbxId,hbxNombre,hbxDescripcion;
    public Scene escena;
    modelo_categoria objMC;
    int opci = 0;

    public Vista_categoria(int opci, modelo_categoria modelo)
    {
        this.opci = opci;
        this.objMC = modelo;
        crearEscena();
        escena = new Scene(ancPanel,400,600);
        escena.getStylesheets().addAll("/sample/css/estilo.css");
        this.setScene(escena);
        this.setTitle("Categorias");
        this.show();
    }

    public void crearEscena()
    {
        ancPanel = new AnchorPane();

        lblidCategoria = new Label("ID Categoria :");
        lblNombre = new Label("Nombre :");
        lblDescripcion = new Label("Descripción :");

        //TEXTFIELD
        txtidCategoria = new JFXTextField();
        txtidCategoria.setPromptText("No se puede dejar vacio");
        txtidCategoria.setEditable(false);

        txtNombre = new JFXTextField();

        txaDescrip = new JFXTextField();

        imvCategoria = new ImageView("/sample/imagenes/celulares.jpg");
        AnchorPane.setLeftAnchor(imvCategoria,100.0);
        AnchorPane.setTopAnchor(imvCategoria,400.0);
        imvCategoria.setFitWidth(250.0);
        imvCategoria.setFitHeight(150.0);

        //HBOX
        hbxId = new HBox();
        hbxId.getChildren().addAll(lblidCategoria,txtidCategoria);
        AnchorPane.setTopAnchor(hbxId,15.0);
        AnchorPane.setLeftAnchor(hbxId,15.0);

        hbxNombre = new HBox();
        hbxNombre.getChildren().addAll(lblNombre,txtNombre);
        AnchorPane.setTopAnchor(hbxNombre,50.0);
        AnchorPane.setLeftAnchor(hbxNombre,37.0);

        hbxDescripcion = new HBox();
        hbxDescripcion.getChildren().addAll(lblDescripcion,txaDescrip);
        AnchorPane.setTopAnchor(hbxDescripcion,95.0);
        AnchorPane.setLeftAnchor(hbxDescripcion,19.0);

        if(opci == 2)
        {
            txtidCategoria.setText(String.valueOf(objMC.getIdCategory()));
            txaDescrip.setText(objMC.getDescription());
            txtNombre.setText(objMC.getName());
        }
        //ANCHOR-PANE
        ancPanel.getChildren().addAll(hbxId,hbxNombre,hbxDescripcion,imvCategoria);

    }

    private void Acciones(int i)
    {
        if(opci == 1)
        switch (i)
        {
            case 1:
                objMC = new modelo_categoria();
                objMC.setIdCategory(Integer.parseInt(txtidCategoria.getText()));
                objMC.setName(txtNombre.getText());
                objMC.setDescription(txaDescrip.getText());
                //objMC.setImage(String.valueOf(imvCategoria.getImage()));
                objMC.insertar();
            case 2:
                this.close();

        }
        else
        {
            objMC.setIdCategory(Integer.parseInt(txtidCategoria.getText()));
            objMC.setDescription((txaDescrip.getText()));
            objMC.setName((txtNombre.getText()));
            objMC.Actualizar();
            this.close();
        }
    }

}
