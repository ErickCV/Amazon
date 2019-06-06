package sample.Vistas;

import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXButtonSkin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.ConexionBD;
import sample.Modelos.modelo_consulta4;
import sample.Modelos.modelo_productos;
import sample.Modelos.modelo_productosMAU;
import sample.Tablas.Tabla_consulta4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MenuAsUser extends Stage
{
    private Scene escena;
    private JFXTextField txtBusqueda;
    public double valor = 250,top=20.0,top2=195;
    private AnchorPane ancInicio,ancEscena;
    Label lblNombreCel,lblPrecioCel, lblDescCel,lblNombreLav,lblPrecioLav,lblDescLav,lblNombreTenis,lblPrecioTenis,lblDescTenis,lblCategoria;
    Label lblPrecios,lblNomLava2,lblPrecLava2,lblDescLava2,lblNomTenis2,lblPreTenis2,lblDescTenis2,lblNomCel2,lblPrecCel2,lblDescCel2,lbname;
    VBox vbxCel,vbxLav,vbxTenis,vbxCheck,vbxCel2,vbxLav2,vbxTenis2;
    VBox  vbxLabels,vbxPrecio;
    HBox hbxFiltro;
    ImageView[] imvCel = new ImageView[10];
    public String[] ArrayNameM = new String[10];
    public String[] ArrayDescripM = new String[10];
    public Float[] ArrayPrecioM = new Float[10];
    int con =0;
    public JFXTextField txtP1,txtP2;
    public JFXTabPane tbpInterfaz;
    private JFXCheckBox ckbCategoria1,ckbCategoria2,ckbCategoria3;
    private Tab tapInicio,tapCompras;
    private ImageView imvCooler,imvBrother,imvLap,imvLogo,imvPubli,imvTMadre,imvLapiz,imvfolder,imvpixel,imvUser;
    private JFXButton btnBscar,btnFiltrar,btnQuitarFiltro,btnLogout;
    private Button btnAgregar;
    public ImageView image = new ImageView("/sample/imagenes/mas.png");
    //public JFXComboBox<String> chbCategorias;
    public TableView<modelo_productosMAU> tbvProductos;
    public String name, clave;

    public MenuAsUser(String name,String clave)
    {
        this.name = name;
        this.clave = clave;
        crearEscena();
        interfaz();
        if(name!=null) {
            System.out.println( "no es nulo");
            Consulta();
        }
        this.setTitle("MenuAsCustomer");
        this.setScene(escena);
        this.setMaximized(true);
        this.setResizable(false);
        this.show();

    }

    private void lista()
    {
        tbvProductos = new TableView<>();
        tbvProductos.setPrefSize(600.0,500.0);
        AnchorPane.setLeftAnchor(tbvProductos,250.0);
        AnchorPane.setTopAnchor(tbvProductos,150.0);

        TableColumn<modelo_productosMAU,String> tbcNombre = new TableColumn<>("Nombre del Producto");
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));

        TableColumn<modelo_productosMAU,Float> tbcPrice = new TableColumn<>("Precio del Producto");
        tbcPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<modelo_productosMAU,String> tbcDescrip = new TableColumn<>("Descripcion del Producto");
        tbcDescrip.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<modelo_productosMAU,ImageView> tbcImage = new TableColumn<>("Imagen");
        tbcImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        tbvProductos.getColumns().addAll(tbcNombre,tbcPrice,tbcDescrip,tbcImage);
    }
    private void filtro() throws SQLException
    {
        if(ckbCategoria1.isSelected() && !ckbCategoria2.isSelected() && !ckbCategoria3.isSelected())
            filtraElectronicos();
        else
        {
            if(ckbCategoria2.isSelected() && !ckbCategoria1.isSelected() && !ckbCategoria3.isSelected())
            {
                filtraLinea();
            }
            else
            {
                if (ckbCategoria3.isSelected() && !ckbCategoria1.isSelected() && !ckbCategoria2.isSelected())
                {
                    filtraDeportes();
                }
                else
                {
                    if (ckbCategoria1.isSelected() && ckbCategoria2.isSelected() && !ckbCategoria3.isSelected())
                    {
                        filtro1y2();
                    }
                    else
                    {
                        if (ckbCategoria1.isSelected() && !ckbCategoria2.isSelected() && ckbCategoria3.isSelected())
                        {
                            filtro1y3();
                        }
                        else
                        {
                            if (!ckbCategoria1.isSelected() && ckbCategoria2.isSelected() && ckbCategoria3.isSelected())
                            {
                                filtro2y3();
                            }
                            else
                            {
                                ancInicio.getChildren().clear();
                                lblNombreCel = new Label("Note 9");
                                lblPrecioCel = new Label("24,999");
                                lblDescCel = new Label("Celular Samsung");
                                ancInicio.getChildren().addAll(vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
                            }
                        }
                    }
                }
            }
        }


    }
    private void filtro1y2()
    {
        ancInicio.getChildren().clear();
        top = 20;
        top2 = 195;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista2(ckbCategoria1.getText(),ckbCategoria2.getText());
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            AnchorPane.setTopAnchor(imvCel[con], top);
            AnchorPane.setLeftAnchor(imvCel[con], valor);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(lblNombreCel,lblPrecioCel,lblDescCel);
            AnchorPane.setLeftAnchor(vbxLabels,valor);
            AnchorPane.setTopAnchor(vbxLabels,top2);
            ancInicio.getChildren().addAll(imvCel[con],vbxLabels);
            valor = valor + 270.0;
            con = con +1;
            if(con == 3)
            {
                valor = 250.0;
                top = 275.0;
                top2 = 450;
            }
        }
        con=0;
        valor=250;
    }
    private void filtro1y3()
    {
        ancInicio.getChildren().clear();
        top = 20;
        top2 = 195;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista2(ckbCategoria1.getText(),ckbCategoria3.getText());
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            AnchorPane.setTopAnchor(imvCel[con], top);
            AnchorPane.setLeftAnchor(imvCel[con], valor);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(
                    lblNombreCel,
                    lblPrecioCel,
                    lblDescCel);
            AnchorPane.setLeftAnchor(vbxLabels,valor);
            AnchorPane.setTopAnchor(vbxLabels,top2);
            ancInicio.getChildren().addAll(imvCel[con],vbxLabels);
            valor = valor + 270.0;
            con = con +1;
            if(con == 3)
            {
                valor = 250.0;
                top = 275.0;
                top2 = 450;
            }
        }
        con=0;
        valor=250;
    }
    private void filtro2y3()
    {
        ancInicio.getChildren().clear();
        top = 20;
        top2 = 195;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista2(ckbCategoria2.getText(),ckbCategoria3.getText());
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            AnchorPane.setTopAnchor(imvCel[con], top);
            AnchorPane.setLeftAnchor(imvCel[con], valor);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(
                    lblNombreCel,
                    lblPrecioCel,
                    lblDescCel);
            AnchorPane.setLeftAnchor(vbxLabels,valor);
            AnchorPane.setTopAnchor(vbxLabels,top2);
            ancInicio.getChildren().addAll(imvCel[con],vbxLabels);
            valor = valor + 270.0;
            con = con +1;
            if(con == 3)
            {
                valor = 250.0;
                top = 275.0;
                top2 = 450;
            }
        }
        con=0;
        valor=250;
    }

    private void PorPrecio()
    {
        try
        {
            ancInicio.getChildren().clear();
            top = 20;
            top2 = 195;
            modelo_productosMAU obj = new modelo_productosMAU();
            obj.ListaPrecios(Integer.parseInt(txtP1.getText()), Integer.parseInt(txtP2.getText()));
            while (con < obj.i) {
                lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
                lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
                lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
                imvCel[con] = new ImageView(obj.imgArray[con]);
                imvCel[con].setFitWidth(250.0);
                imvCel[con].setFitHeight(170.0);
                AnchorPane.setTopAnchor(imvCel[con], top);
                AnchorPane.setLeftAnchor(imvCel[con], valor);
                vbxLabels = new VBox();
                vbxLabels.getChildren().addAll(lblNombreCel, lblPrecioCel, lblDescCel);
                AnchorPane.setLeftAnchor(vbxLabels, valor);
                AnchorPane.setTopAnchor(vbxLabels, top2);
                ancInicio.getChildren().addAll(imvCel[con], vbxLabels);
                valor = valor + 270.0;
                con = con + 1;
                if (con == 3) {
                    valor = 250.0;
                    top = 275.0;
                    top2 = 450;
                }
            }
            con = 0;
            valor = 250;
        }
        catch(Exception e)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("ALERTA");
            a.setHeaderText("ERROR AL INGRESAR DATOS");
            a.setContentText("Debe de ingresar DOS CANTIDADES diferentes en los espacios correspondientes");
            a.show();
            Inicio();
        }

    }
    private void filtraDeportes()
    {
        ancInicio.getChildren().clear();
        top = 20;
        top2 = 195;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista(ckbCategoria3.getText());
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            AnchorPane.setTopAnchor(imvCel[con], top);
            AnchorPane.setLeftAnchor(imvCel[con], valor);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(
                    lblNombreCel,
                    lblPrecioCel,
                    lblDescCel);
            AnchorPane.setLeftAnchor(vbxLabels,valor);
            AnchorPane.setTopAnchor(vbxLabels,top2);
            ancInicio.getChildren().addAll(imvCel[con],vbxLabels);
            valor = valor + 270.0;
            con = con +1;
            if(con == 3)
            {
                valor = 250.0;
                top = 275.0;
                top2 = 450;
            }
        }
        con=0;
        valor=250;
    }

    private void filtraLinea()
    {
        ancInicio.getChildren().clear();
        top = 20;
        top2 = 195;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista(ckbCategoria2.getText());
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            AnchorPane.setTopAnchor(imvCel[con], top);
            AnchorPane.setLeftAnchor(imvCel[con], valor);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(
                    lblNombreCel,
                    lblPrecioCel,
                    lblDescCel);
            AnchorPane.setLeftAnchor(vbxLabels,valor);
            AnchorPane.setTopAnchor(vbxLabels,top2);
            ancInicio.getChildren().addAll(imvCel[con],vbxLabels);
            valor = valor + 270.0;
            con = con +1;
            if(con == 3)
            {
                valor = 250.0;
                top = 275.0;
                top2 = 450;
            }
        }
        con=0;
        valor=250;
    }

    private void filtraElectronicos()
    {
        ancInicio.getChildren().clear();
        top = 20;
        top2 = 195;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista(ckbCategoria1.getText());
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            AnchorPane.setTopAnchor(imvCel[con], top);
            AnchorPane.setLeftAnchor(imvCel[con], valor);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(
                    lblNombreCel,
                    lblPrecioCel,
                    lblDescCel);
            AnchorPane.setLeftAnchor(vbxLabels,valor);
            AnchorPane.setTopAnchor(vbxLabels,top2);
            ancInicio.getChildren().addAll(imvCel[con],vbxLabels);
            valor = valor + 270.0;
            con = con +1;
            if(con == 3)
            {
                valor = 250.0;
                top = 275.0;
                top2 = 450;
            }
        }
        con=0;
        valor=250;
    }

    private void crearEscena()
    {
        ancEscena = new AnchorPane();
        escena = new Scene(ancEscena);
        escena.getStylesheets().add("/sample/CSS/estilo.css");
    }

    private void interfaz()
    {
        botones();
        TextField();
        checks();
        imagenes();
        label();
        Vbox();
        //ESPACIO DE BUSQUEDA
        txtBusqueda = new JFXTextField();
        txtBusqueda.setPromptText("Buscar...");
        txtBusqueda.setPrefWidth(500.0);
        txtBusqueda.setPrefHeight(35.0);
        AnchorPane.setLeftAnchor(txtBusqueda,300.0);
        AnchorPane.setTopAnchor(txtBusqueda,15.0);

        btnBscar = new JFXButton("Buscar");
        AnchorPane.setLeftAnchor(btnBscar,820.0);
        AnchorPane.setTopAnchor(btnBscar,15.0);
        //btnBscar.setOnAction(event -> accion());
//--------------------------------------------------------------------------------------------------------------------------------
        /*chbCategorias = new JFXComboBox<>(FXCollections.observableArrayList("Electronicos","papeleria","componentes pc"));
        chbCategorias.setPromptText("Categorias");
        AnchorPane.setLeftAnchor(chbCategorias,180.0);
        AnchorPane.setTopAnchor(chbCategorias,92.0);
        //chbCategorias.setOnAction(event -> categorias());*/
//-----------------------------------------------------------------------------------------------
        //INSTANCIANDO TABPANE
        tbpInterfaz = new JFXTabPane();
        tbpInterfaz.setMaxWidth(1900.0);
        AnchorPane.setTopAnchor(tbpInterfaz,85.0);
//---------------------------------------------------------------------------------------------
        //INSTANCIANDO TABS
        tapInicio = new Tab("Inicio");
        tapCompras = new Tab("Tus compras");
        //tapCompras.setOnSelectionChanged(event -> Consulta());
//---------------------------------------------------------------------------------------------
        //ESCENAS DE LOS TABS
        ancInicio = new AnchorPane();
        //ancInicio.getChildren().addAll(imvCelular,imvLibros,imvRelojes,imv1,imv2,imv3,vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
        ancInicio.getChildren().addAll(imvCooler,imvBrother,imvLap,imvTMadre,imvLapiz,imvfolder,vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
//------------------------------------------------------------------------------------------------
        //INSERTANDO LAS ESCENAS A LOS TABS
        tapInicio.setContent(ancInicio);
        tbpInterfaz.getTabs().addAll(tapInicio,tapCompras);
//------------------------------------------------------------------------------------------------
        //ESCENA PRINCIPAL
        //ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvPubli,imvLogo,txtBusqueda,lbname,imvUser,btnLogout,vbxCheck,vbxPrecio);
        ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvLogo,txtBusqueda,lbname,imvUser,btnLogout,vbxCheck,vbxPrecio);
//------------------------------------------------------------------------------------------------


    }

    @Override
    public String toString() {
        return "MenuAsUser{" +
                "name='" + name + '\'' +
                '}';
    }

    public void Consulta() {
        modelo_consulta4 modelo = new modelo_consulta4();
        modelo.setNameCustomer(lbname.getText());
        VBox vbox1 = new VBox();
        AnchorPane nuevoAnchor = new AnchorPane();
        Tabla_consulta4 tabla = new Tabla_consulta4();
        vbox1.getChildren().addAll(tabla.tableViewConsulta4);
        AnchorPane.setTopAnchor(vbox1,50.0);
        AnchorPane.setLeftAnchor(vbox1,250.0);
        nuevoAnchor.getChildren().addAll(vbox1);
        tapCompras.setContent(nuevoAnchor);
        tabla.close();
    }
//------------------------------------------------------------------------------------------------


    private void Salir() {
        this.close();
        Login objL = new Login();
    }
//-------------------------------------------------------------------------------------------------

    private void botones()
    {
        btnAgregar = new JFXButton("Agregar al carrito",image);
        image.setFitHeight(20.0);
        image.setFitWidth(20.0);

        btnLogout = new JFXButton("    ");
        btnLogout.setId("button_logout");
        btnLogout.setOnAction(event -> Salir());
        AnchorPane.setTopAnchor(btnLogout,15.0);
        AnchorPane.setLeftAnchor(btnLogout,1310.0);
    }
    private void TextField()
    {
        txtP1 = new JFXTextField();
        txtP1.setPromptText("Desde...");

        txtP2 = new JFXTextField();
        txtP2.setPromptText("Hasta");
    }
    private void checks()
    {
        ckbCategoria1 = new JFXCheckBox("Electronicos");
        ckbCategoria1.setOnAction(event -> {
            try {
                filtro();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ckbCategoria2 = new JFXCheckBox("Papeleria");
        ckbCategoria2.setOnAction(event -> {
            try {
                filtro();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        ckbCategoria3 = new JFXCheckBox("Componentes PC");
        ckbCategoria3.setOnAction(event -> {
            try {
                filtro();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
    private void Vbox()
    {
        //VBOX
        vbxCel = new VBox();
        vbxCel.getChildren().addAll(lblNombreCel,lblPrecioCel,lblDescCel,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxCel,250.0);
        AnchorPane.setTopAnchor(vbxCel,200.0);

        vbxLav = new VBox();
        vbxLav.getChildren().addAll(lblNombreLav,lblPrecioLav,lblDescLav,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxLav,522.0);
        AnchorPane.setTopAnchor(vbxLav,200.0);

        vbxTenis = new VBox();
        vbxTenis.getChildren().addAll(lblNombreTenis,lblPrecioTenis,lblDescTenis,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxTenis,793.0);
        AnchorPane.setTopAnchor(vbxTenis,200.0);

        vbxLav2 = new VBox();
        vbxLav2.getChildren().addAll(lblNomLava2,lblPrecLava2,lblDescLava2,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxLav2,250.0);
        AnchorPane.setTopAnchor(vbxLav2,490.0);

        vbxTenis2 = new VBox();
        vbxTenis2.getChildren().addAll(lblNomTenis2,lblPreTenis2,lblDescTenis2,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxTenis2,522.0);
        AnchorPane.setTopAnchor(vbxTenis2,490.0);

        vbxCel2 = new VBox();
        vbxCel2.getChildren().addAll(lblNomCel2,lblPrecCel2,lblDescCel2,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxCel2,793.0);
        AnchorPane.setTopAnchor(vbxCel2,490.0);

        vbxCheck = new VBox();
        vbxPrecio = new VBox();
        vbxCheck.setSpacing(5.0);
        AnchorPane.setLeftAnchor(vbxCheck,10.0);
        AnchorPane.setTopAnchor(vbxCheck,125.0);

        hbxFiltro = new HBox();
        btnFiltrar = new JFXButton("Filtrar");
        btnFiltrar.setOnAction(event -> PorPrecio());
        btnQuitarFiltro = new JFXButton("Quitar filtro");
        btnQuitarFiltro.setOnAction(event -> Inicio());

        hbxFiltro = new HBox();
        hbxFiltro.getChildren().addAll(btnFiltrar,btnQuitarFiltro);

        vbxPrecio.getChildren().addAll(lblPrecios,txtP1,txtP2,hbxFiltro);
        vbxPrecio.setSpacing(10.0);
        AnchorPane.setLeftAnchor(vbxPrecio,10.0);
        AnchorPane.setTopAnchor(vbxPrecio,240.0);



        vbxCheck.getChildren().addAll(lblCategoria,ckbCategoria1,ckbCategoria2,ckbCategoria3,vbxPrecio);


    }

    public void Inicio()
    {
        txtP1.clear();
        txtP2.clear();
        ancInicio.getChildren().clear();
        //ancInicio.getChildren().addAll(imvCelular,imvLibros,imvRelojes,imv1,imv2,imv3,vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
        ancInicio.getChildren().addAll(imvCooler,imvBrother,imvLap,imvTMadre,imvLapiz,imvfolder,vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
    }

    private void label()
    {
        //LABELS
        lbname = new Label(name);
        lbname.setId("label_blancos");
        AnchorPane.setTopAnchor(lbname,15.0);
        AnchorPane.setLeftAnchor(lbname,1225.0);

        lblNombreCel = new Label("Cooler Liquid");
        lblNombreLav = new Label("print Brother");
        lblNombreTenis = new Label("Laptop Dell");

        lblPrecioCel = new Label("4,347.88");
        lblPrecioLav = new Label("2,999");
        lblPrecioTenis = new Label("18,500");

        lblDescCel = new Label("Enfriamento Cooler Master");
        lblDescLav = new Label("DCP-L2540DW multifuncional");
        lblDescTenis = new Label("8GB RAM Intel i7, GTX 1060");

        lblCategoria = new Label("Categorias");
        lblPrecios = new Label("Por costo");

        lblNomLava2 = new Label("Tarjeta madre");
        lblPrecLava2 = new Label("2,200");
        lblDescLava2 = new Label("AM4 AMD Gaming ATX");

        lblNomTenis2 = new Label("Lapiz");
        lblPreTenis2 = new Label("4.50");
        lblDescTenis2 = new Label("Lapiz HB");

        lblNomCel2 = new Label("Protectores");
        lblPrecCel2 = new Label("3.00");
        lblDescCel2 = new Label("Protector transparente");
    }
    private void imagenes()
    {
        //CONSTRUCCION DE IMAGENES
        imvUser = new ImageView("/sample/imagenes/usuario.png");
        imvUser.setFitHeight(35);
        imvUser.setFitWidth(35);
        AnchorPane.setTopAnchor(imvUser,15.0);
        AnchorPane.setLeftAnchor(imvUser,1270.0);

        imvLogo = new ImageView("/sample/imagenes/logo.png");
        imvLogo.setFitHeight(80.0);
        imvLogo.setFitWidth(250.0);

        imvCooler = new ImageView("/sample/imagenes/coler.jpg");
        imvCooler.setFitWidth(250.0);
        imvCooler.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvCooler,20.0);
        AnchorPane.setLeftAnchor(imvCooler,250.0);

        /*imvPubli = new ImageView("/sample/imagenes/d.jpg");
        AnchorPane.setRightAnchor(imvPubli,0.0);
        AnchorPane.setTopAnchor(imvPubli,110.0);
        imvPubli.setFitWidth(210.0);
        imvPubli.setFitHeight(595.0);*/

        imvBrother = new ImageView("/sample/imagenes/brother.jpg");
        imvBrother.setFitWidth(250.0);
        imvBrother.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvBrother,20.0);
        AnchorPane.setLeftAnchor(imvBrother,520.0);

        imvLap = new ImageView("/sample/imagenes/laptop.jpg");
        imvLap.setFitWidth(250.0);
        imvLap.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvLap,20.0);
        AnchorPane.setLeftAnchor(imvLap,790.0);

        imvTMadre = new ImageView("/sample/imagenes/tarjeta.jpg");
        imvTMadre.setFitWidth(250.0);
        imvTMadre.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvTMadre,320.0);
        AnchorPane.setLeftAnchor(imvTMadre,250.0);

        imvLapiz = new ImageView("/sample/imagenes/lapiz.jpg");
        imvLapiz.setFitWidth(250.0);
        imvLapiz.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvLapiz,320.0);
        AnchorPane.setLeftAnchor(imvLapiz,520.0);

        imvfolder = new ImageView("/sample/imagenes/protector.jpg");
        imvfolder.setFitWidth(250.0);
        imvfolder.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvfolder,320.0);
        AnchorPane.setLeftAnchor(imvfolder,790.0);
    }

    private void interfaz2()
    {
        Label lblname,lblPrice,lbldesc;
        ImageView imvImage;
        ScrollPane spInterfaz = new ScrollPane();
        spInterfaz.getContent();
        tapInicio.setContent(spInterfaz);
        ObservableList<modelo_productos> Lista = new modelo_productos().Listar();
        for (modelo_productos MP: Lista)
        {
            lblname = new Label(MP.getNameProduct());
            lblPrice = new Label(""+MP.getPrice());
            lbldesc = new Label(MP.getDescription());
            imvImage = new ImageView(MP.getImage());
        }
    }
}
