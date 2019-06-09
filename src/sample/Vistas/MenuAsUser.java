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
    VBox  vbx,vbxLabels,vbxPrecio,vbxHbx;
    HBox hbxFiltro,hbxProduct;
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
    HBox hbxNode;
    HBox hbxContentProducts;
    VBox vbxContent;

    ScrollPane spInicio;

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
            filtraElectronicos(ckbCategoria1.getText());
        else
        {
            if(ckbCategoria2.isSelected() && !ckbCategoria1.isSelected() && !ckbCategoria3.isSelected())
                filtraElectronicos(ckbCategoria2.getText());
            else
            {
                if (ckbCategoria3.isSelected() && !ckbCategoria1.isSelected() && !ckbCategoria2.isSelected())
                    filtraElectronicos(ckbCategoria3.getText());
                else
                {
                    if (ckbCategoria1.isSelected() && ckbCategoria2.isSelected() && !ckbCategoria3.isSelected())
                        filtro2Cate(ckbCategoria1.getText(),ckbCategoria2.getText());
                    else
                    {
                        if (ckbCategoria1.isSelected() && !ckbCategoria2.isSelected() && ckbCategoria3.isSelected())
                            filtro2Cate(ckbCategoria1.getText(),ckbCategoria3.getText());
                        else
                        {
                            if (!ckbCategoria1.isSelected() && ckbCategoria2.isSelected() && ckbCategoria3.isSelected())
                                filtro2Cate(ckbCategoria2.getText(),ckbCategoria3.getText());
                            else
                                tapInicio.setContent(interfaz2());
                        }
                    }
                }
            }
        }


    }
    private void filtro2Cate(String cate1, String cate2)
    {
        hbxProduct = new HBox();
        vbxHbx = new VBox();
        spInicio.setContent(null);
        int pro = 0;
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista2(cate1,cate2);
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(imvCel[con],lblNombreCel,lblPrecioCel,lblDescCel);
            hbxProduct.getChildren().addAll(vbxLabels);
            hbxProduct.setSpacing(10.0);
            con = con +1;
            pro++;
            if (pro==3)
            {
                vbxHbx.getChildren().addAll(hbxProduct);
                hbxProduct = new HBox();
                pro=0;
            }
        }
        vbxHbx.getChildren().addAll(hbxProduct);
        spInicio.setContent(vbxHbx);
        con=0;
    }
    private void PorPrecio()
    {
        try
        {
            hbxProduct = new HBox();
            vbxHbx = new VBox();
            spInicio.setContent(null);
            int pro = 0;
            modelo_productosMAU obj = new modelo_productosMAU();
            obj.ListaPrecios(Integer.parseInt(txtP1.getText()), Integer.parseInt(txtP2.getText()));
            while (con < obj.i) {
                lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
                lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
                lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
                imvCel[con] = new ImageView(obj.imgArray[con]);
                imvCel[con].setFitWidth(250.0);
                imvCel[con].setFitHeight(170.0);
                vbxLabels = new VBox();
                vbxLabels.getChildren().addAll(imvCel[con],lblNombreCel, lblPrecioCel, lblDescCel);
                hbxProduct.getChildren().addAll(vbxLabels);
                hbxProduct.setSpacing(10.0);
                con = con + 1;
                pro++;
                if (pro==3)
                {
                    vbxHbx.getChildren().addAll(hbxProduct);
                    hbxProduct = new HBox();
                    pro=0;
                }
            }
            vbxHbx.getChildren().addAll(hbxProduct);
            spInicio.setContent(vbxHbx);
            con = 0;
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

    private void filtraElectronicos(String Cate)
    {
        hbxProduct = new HBox();
        vbxHbx = new VBox();
        int pro = 0;
        spInicio.setContent(null);
        modelo_productosMAU obj = new modelo_productosMAU();
        obj.Lista(Cate);
        while (con < obj.i)
        {
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(imvCel[con],lblNombreCel,lblPrecioCel,lblDescCel);
            hbxProduct.getChildren().add(vbxLabels);
            hbxProduct.setSpacing(10.0);
            con = con +1;
            pro++;
            if (pro==3)
            {
                vbxHbx.getChildren().addAll(hbxProduct);
                hbxProduct = new HBox();
                pro=0;
            }
        }
        vbxHbx.getChildren().addAll(hbxProduct);
        spInicio.setContent(vbxHbx);
        con=0;
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
       /*vbx = new VBox();
        HBox hbx = new HBox();
        HBox hbx1 = new HBox();
        HBox hbx2 = new HBox();
        HBox hbx3 = new HBox();

        hbx.getChildren().addAll(vbxCel,vbxLav,vbxTenis);
        hbx1.getChildren().addAll(vbxLav2);
        hbx2.getChildren().addAll(vbxCel2);
        hbx3.getChildren().addAll(vbxTenis2);

        hbx.setSpacing(10.0);
        hbx1.setSpacing(10.0);
        hbx2.setSpacing(10.0);
        hbx3.setSpacing(10.0);
        vbx.setSpacing(10.0);

        vbx.getChildren().addAll(hbx,hbx1,hbx2,hbx3);
        spInicio = new ScrollPane();
        spInicio.setPrefSize(800,500);
        spInicio.setContent(vbx);
        hbx.setSpacing(10.0);
        //hbxNode.getChildren().addAll(vbxCheck,spInicio);*/
//------------------------------------------------------------------------------------------------
        //INSERTANDO LAS ESCENAS A LOS TABS
        tapInicio.setContent(interfaz2());
        tbpInterfaz.getTabs().addAll(tapInicio,tapCompras);
//------------------------------------------------------------------------------------------------
        //ESCENA PRINCIPAL
        //ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvPubli,imvLogo,txtBusqueda,lbname,imvUser,btnLogout,vbxCheck,vbxPrecio);
        ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvLogo,txtBusqueda,lbname,imvUser,btnLogout);
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
        /*vbxCel = new VBox();
        vbxCel.getChildren().addAll(imvCooler,lblNombreCel,lblPrecioCel,lblDescCel,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxCel,250.0);
        AnchorPane.setTopAnchor(vbxCel,200.0);

        vbxLav = new VBox();
        vbxLav.getChildren().addAll(imvBrother,lblNombreLav,lblPrecioLav,lblDescLav,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxLav,522.0);
        AnchorPane.setTopAnchor(vbxLav,200.0);

        vbxTenis = new VBox();
        vbxTenis.getChildren().addAll(imvLap,lblNombreTenis,lblPrecioTenis,lblDescTenis,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxTenis,793.0);
        AnchorPane.setTopAnchor(vbxTenis,200.0);

        vbxLav2 = new VBox();
        vbxLav2.getChildren().addAll(imvTMadre,lblNomLava2,lblPrecLava2,lblDescLava2,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxLav2,250.0);
        AnchorPane.setTopAnchor(vbxLav2,490.0);

        vbxTenis2 = new VBox();
        vbxTenis2.getChildren().addAll(imvLapiz,lblNomTenis2,lblPreTenis2,lblDescTenis2,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxTenis2,522.0);
        AnchorPane.setTopAnchor(vbxTenis2,490.0);

        vbxCel2 = new VBox();
        vbxCel2.getChildren().addAll(imvfolder,lblNomCel2,lblPrecCel2,lblDescCel2,new Button("Agregar al carrito",image));
        AnchorPane.setLeftAnchor(vbxCel2,793.0);
        AnchorPane.setTopAnchor(vbxCel2,490.0);*/

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
        ckbCategoria1.setSelected(false);
        ckbCategoria2.setSelected(false);
        ckbCategoria3.setSelected(false);
        tapInicio.setContent(interfaz2());
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

        /*imvCooler = new ImageView("/sample/imagenes/coler.jpg");
        imvCooler.setFitWidth(250.0);
        imvCooler.setFitHeight(170.0);
        AnchorPane.setTopAnchor(imvCooler,20.0);
        AnchorPane.setLeftAnchor(imvCooler,250.0);

        imvPubli = new ImageView("/sample/imagenes/d.jpg");
        AnchorPane.setRightAnchor(imvPubli,0.0);
        AnchorPane.setTopAnchor(imvPubli,110.0);
        imvPubli.setFitWidth(210.0);
        imvPubli.setFitHeight(595.0);

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
        AnchorPane.setLeftAnchor(imvfolder,790.0);*/
    }

    private HBox interfaz2()
    {
        hbxNode = new HBox();
        hbxContentProducts = new HBox();
        vbxContent = new VBox();
        Label lblname,lblPrice,lbldesc;
        ImageView imvImage;
        int pro=0;
        spInicio = new ScrollPane();
        spInicio.setPrefSize(800,500);
        ObservableList<modelo_productos> Lista = new modelo_productos().Listar();
        for (modelo_productos MP: Lista)
        {
            pro = pro+1;
            lblname = new Label(MP.getNameProduct());
            lblPrice = new Label(""+MP.getPrice());
            lbldesc = new Label(MP.getDescription());
            imvImage = new ImageView(MP.getImage());
            imvImage.setFitHeight(200);
            imvImage.setFitWidth(220);
            VBox vbxProduct = new VBox();
            vbxProduct.getChildren().addAll(imvImage,lblname,lblPrice,lbldesc);
            hbxContentProducts.getChildren().addAll(vbxProduct);
            hbxContentProducts.setSpacing(10.0);
            if (pro==3)
            {
                vbxContent.getChildren().addAll(hbxContentProducts);
                hbxContentProducts = new HBox();
                pro=0;
            }

        }
        vbxContent.getChildren().addAll(hbxContentProducts);
        spInicio.setContent(vbxContent);
        hbxNode.getChildren().addAll(vbxCheck,spInicio);
        //me quede en agregar los checkbox y el sp a un Hbox
        return hbxNode;
    }
}
