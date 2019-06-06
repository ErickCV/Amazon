package sample.Vistas;

import com.jfoenix.controls.*;
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
import sample.Modelos.modelo_productosMAU;
import sample.Tablas.Tabla_consulta4;

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
    private ImageView imvCelular,imvLibros,imvRelojes,imvLogo,imvPubli,imv1,imv2,imv3,imvpixel,imvUser;
    private JFXButton btnBscar,btnFiltrar,btnQuitarFiltro,btnLogout;
    public JFXComboBox<String> chbCategorias;
    public TableView<modelo_productosMAU> tbvProductos;
    public String name, clave;
    private Scene scene;
    private MenuAsCustomer objMAC;

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
        TextField();
        checks();
        //imagenes();
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
        chbCategorias = new JFXComboBox<>(FXCollections.observableArrayList("Electronicos","papeleria","componentes pc"));
        chbCategorias.setPromptText("Categorias");
        AnchorPane.setLeftAnchor(chbCategorias,180.0);
        AnchorPane.setTopAnchor(chbCategorias,92.0);
        //chbCategorias.setOnAction(event -> categorias());
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
        ancInicio.getChildren().addAll(vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
//------------------------------------------------------------------------------------------------
        //INSERTANDO LAS ESCENAS A LOS TABS
        tapInicio.setContent(ancInicio);
        tbpInterfaz.getTabs().addAll(tapInicio,tapCompras);
//------------------------------------------------------------------------------------------------

        imvUser = new ImageView("/sample/imagenes/usuario.png");
        imvUser.setFitHeight(35);
        imvUser.setFitWidth(35);
        AnchorPane.setTopAnchor(imvUser,15.0);
        AnchorPane.setLeftAnchor(imvUser,1270.0);

        btnLogout = new JFXButton("    ");
        btnLogout.setId("button_logout");
        btnLogout.setOnAction(event -> Salir());
        AnchorPane.setTopAnchor(btnLogout,15.0);
        AnchorPane.setLeftAnchor(btnLogout,1310.0);

        lbname = new Label(name);
        lbname.setId("label_blancos");
        AnchorPane.setTopAnchor(lbname,15.0);
        AnchorPane.setLeftAnchor(lbname,1225.0);

        imvLogo = new ImageView("/sample/imagenes/logo.png");
        imvLogo.setFitHeight(80.0);
        imvLogo.setFitWidth(250.0);

//------------------------------------------------------------------------------------------------
        //ESCENA PRINCIPAL
        //ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvPubli,imvLogo,txtBusqueda,lbname,imvUser,btnLogout,vbxCheck,vbxPrecio);
        ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,txtBusqueda,lbname,imvLogo,imvUser,btnLogout,vbxCheck,vbxPrecio);
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
        vbxCel.getChildren().addAll(lblNombreCel,lblPrecioCel,lblDescCel);
        AnchorPane.setLeftAnchor(vbxCel,250.0);
        AnchorPane.setTopAnchor(vbxCel,200.0);

        vbxLav = new VBox();
        vbxLav.getChildren().addAll(lblNombreLav,lblPrecioLav,lblDescLav);
        AnchorPane.setLeftAnchor(vbxLav,522.0);
        AnchorPane.setTopAnchor(vbxLav,200.0);

        vbxTenis = new VBox();
        vbxTenis.getChildren().addAll(lblNombreTenis,lblPrecioTenis,lblDescTenis);
        AnchorPane.setLeftAnchor(vbxTenis,793.0);
        AnchorPane.setTopAnchor(vbxTenis,200.0);

        vbxLav2 = new VBox();
        vbxLav2.getChildren().addAll(lblNomLava2,lblPrecLava2,lblDescLava2);
        AnchorPane.setLeftAnchor(vbxLav2,250.0);
        AnchorPane.setTopAnchor(vbxLav2,450.0);

        vbxTenis2 = new VBox();
        vbxTenis2.getChildren().addAll(lblNomTenis2,lblPreTenis2,lblDescTenis2);
        AnchorPane.setLeftAnchor(vbxTenis2,522.0);
        AnchorPane.setTopAnchor(vbxTenis2,450.0);

        vbxCel2 = new VBox();
        vbxCel2.getChildren().addAll(lblNomCel2,lblPrecCel2,lblDescCel2);
        AnchorPane.setLeftAnchor(vbxCel2,793.0);
        AnchorPane.setTopAnchor(vbxCel2,450.0);

        vbxCheck = new VBox();
        vbxCheck.setSpacing(5.0);
        AnchorPane.setLeftAnchor(vbxCheck,10.0);
        AnchorPane.setTopAnchor(vbxCheck,125.0);
        vbxCheck.getChildren().addAll(lblCategoria,ckbCategoria1,ckbCategoria2,ckbCategoria3);

        vbxPrecio = new VBox();

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

    }

    public void Inicio()
    {
        txtP1.clear();
        txtP2.clear();
        ancInicio.getChildren().clear();
        //ancInicio.getChildren().addAll(imvCelular,imvLibros,imvRelojes,imv1,imv2,imv3,vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
        ancInicio.getChildren().addAll(vbxCel,vbxLav,vbxTenis,vbxLav2,vbxTenis2,vbxCel2);
    }

    private void label()
    {
        //LABELS
        lblNombreCel = new Label("Note 9");
        lblNombreLav = new Label("Lavadora 3000");
        lblNombreTenis = new Label("Tenis Bellos");

        lblPrecioCel = new Label("24,999");
        lblPrecioLav = new Label("12,599");
        lblPrecioTenis = new Label("1,599");

        lblDescCel = new Label("Celular Samsung");
        lblDescLav = new Label("Lavadora LG");
        lblDescTenis = new Label("Tenis Marca Nike");

        lblCategoria = new Label("Categorias");
        lblPrecios = new Label("Por costo");

        lblNomLava2 = new Label("Lavadora");
        lblPrecLava2 = new Label("16,500");
        lblDescLava2 = new Label("Lavadora Samsung");

        lblNomTenis2 = new Label("Tenis Modernos");
        lblPreTenis2 = new Label("2,100");
        lblDescTenis2 = new Label("Tenis Nike");

        lblNomCel2 = new Label("iPhone X");
        lblPrecCel2 = new Label("20,999");
        lblDescCel2 = new Label("Celular marca Apple");
    }
    /*private void imagenes()
    {
        //CONSTRUCCION DE IMAGENES
        imvpixel = new ImageView("/sample/imagenes/pixel.jpg");
        imvpixel.setFitWidth(250.0);
        imvpixel.setFitHeight(170.0);

        imvPubli = new ImageView("/sample/imagenes/d.jpg");
        AnchorPane.setRightAnchor(imvPubli,0.0);
        AnchorPane.setTopAnchor(imvPubli,110.0);
        imvPubli.setFitWidth(210.0);
        imvPubli.setFitHeight(595.0);

        imvLogo = new ImageView("/sample/imagenes/logo2.png");
        imvLogo.setFitHeight(80.0);
        imvLogo.setFitWidth(250.0);

        imvCelular = new ImageView("/sample/imagenes/note9.jpg");
        imvCelular.setFitWidth(250.0);
        imvCelular.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvCelular,20.0);
        AnchorPane.setLeftAnchor(imvCelular,250.0);

        imvLibros = new ImageView("/sample/imagenes/lavadora.jpg");
        imvLibros.setFitWidth(250.0);
        imvLibros.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvLibros,20.0);
        AnchorPane.setLeftAnchor(imvLibros,520.0);

        imvRelojes = new ImageView("/sample/imagenes/tenis.jpg");
        imvRelojes.setFitWidth(250.0);
        imvRelojes.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvRelojes,20.0);
        AnchorPane.setLeftAnchor(imvRelojes,790.0);

        imv1 = new ImageView("/sample/imagenes/lavadora2.jpg");
        imv1.setFitWidth(250.0);
        imv1.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imv1,275.0);
        AnchorPane.setLeftAnchor(imv1,250.0);

        imv2 = new ImageView("/sample/imagenes/tenis2.jpg");
        imv2.setFitWidth(250.0);
        imv2.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imv2,275.0);
        AnchorPane.setLeftAnchor(imv2,520.0);

        imv3 = new ImageView("/sample/imagenes/iphonex.png");
        imv3.setFitWidth(250.0);
        imv3.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imv3,275.0);
        AnchorPane.setLeftAnchor(imv3,790.0);
    }*/
}
