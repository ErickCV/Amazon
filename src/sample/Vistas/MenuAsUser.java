package sample.Vistas;

import com.jfoenix.controls.*;
import com.jfoenix.skins.JFXButtonSkin;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import sample.Modelos.*;
import sample.Tablas.Tabla_consulta4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MenuAsUser extends Stage
{
    private  int noCarrito;

    public int getNoCarrito() {
        return noCarrito;
    }

    public void setNoCarrito(int noCarrito) {
        this.noCarrito = noCarrito;
    }

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
    private JFXButton btnBscar,btnFiltrar,btnQuitarFiltro,btnLogout,btnShoppinCart;
    private Button btnAgregar;
    public ImageView image,image2;
    public TableView<modelo_productosMAU> tbvProductos;
    public String name, clave;
    HBox hbxNode;
    HBox hbxContentProducts;
    VBox vbxContent;
    FontAwesomeIconView icono,icono2;
    ScrollPane spInicio;
    String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public MenuAsUser(String name, String clave)
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

    public MenuAsUser() {
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
            image = new ImageView("/sample/imagenes/mas.png");
            image.setFitHeight(20.0);
            image.setFitWidth(20.0);
            btnAgregar = new Button("Agregar a carrito",image);
            btnAgregar.setOnAction(event -> EventoAgregar(ArrayNameM[con]));
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(imvCel[con],lblNombreCel,lblPrecioCel,lblDescCel,btnAgregar);
            hbxProduct.getChildren().addAll(vbxLabels);
            hbxProduct.setSpacing(10.0);
            con = con +1;
            pro++;
            if (pro==3)
            {
                vbxHbx.getChildren().addAll(hbxProduct);
                vbxHbx.setSpacing(10.0);
                hbxProduct = new HBox();
                pro=0;
            }
        }
        vbxHbx.getChildren().addAll(hbxProduct);
        vbxHbx.setSpacing(10.0);
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
                image = new ImageView("/sample/imagenes/mas.png");
                image.setFitHeight(20.0);
                image.setFitWidth(20.0);
                btnAgregar = new Button("Agregar a carrito",image);
                btnAgregar.setOnAction(event -> EventoAgregar(ArrayNameM[con]));
                lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
                lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
                lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
                imvCel[con] = new ImageView(obj.imgArray[con]);
                imvCel[con].setFitWidth(250.0);
                imvCel[con].setFitHeight(170.0);
                vbxLabels = new VBox();
                vbxLabels.getChildren().addAll(imvCel[con],lblNombreCel, lblPrecioCel, lblDescCel,btnAgregar);
                hbxProduct.getChildren().addAll(vbxLabels);
                hbxProduct.setSpacing(10.0);
                con = con + 1;
                pro++;
                if (pro==3)
                {
                    vbxHbx.getChildren().addAll(hbxProduct);
                    vbxHbx.setSpacing(10.0);
                    hbxProduct = new HBox();
                    pro=0;
                }
            }
            vbxHbx.getChildren().addAll(hbxProduct);
            vbxHbx.setSpacing(10.0);
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
            image = new ImageView("/sample/imagenes/mas.png");
            image.setFitHeight(20.0);
            image.setFitWidth(20.0);
            btnAgregar = new Button("Agregar a carrito",image);
            btnAgregar.setOnAction(event -> EventoAgregar(ArrayNameM[con]));
            lblNombreCel = new Label(ArrayNameM[con] = new String(obj.ArrayName[con]));
            lblPrecioCel = new Label(String.valueOf(ArrayPrecioM[con] = new Float(obj.ArrayPrecio[con])));
            lblDescCel = new Label(ArrayDescripM[con] = new String(obj.ArrayDescrip[con]));
            imvCel[con] = new ImageView(obj.imgArray[con]);
            imvCel[con].setFitWidth(250.0);
            imvCel[con].setFitHeight(170.0);
            vbxLabels = new VBox();
            vbxLabels.getChildren().addAll(imvCel[con],lblNombreCel,lblPrecioCel,lblDescCel,btnAgregar);
            hbxProduct.getChildren().add(vbxLabels);
            hbxProduct.setSpacing(10.0);
            con = con +1;
            pro++;
            if (pro==3)
            {
                vbxHbx.getChildren().addAll(hbxProduct);
                vbxHbx.setSpacing(10.0);
                hbxProduct = new HBox();
                pro=0;
            }
        }
        vbxHbx.getChildren().addAll(hbxProduct);
        vbxHbx.setSpacing(10.0);
        spInicio.setContent(vbxHbx);
        con=0;
    }

    private void EventoAgregar(String s) {
        modelo_carritocompras carritocompras=new modelo_carritocompras();
        if (lbname.getText().equals("user"))
        {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Debe Iniciar sesion para poder agregar productos al carrito de compra");
            alert.showAndWait();
        }
        else {

            try {
                carritocompras.setIdCart(noCarrito);
                carritocompras.setIdProduct(new modelo_productos().BuscarByName(s));
                carritocompras.setCantidad(1);
                carritocompras.setIdCustomer(new modelo_cliente().BuscarCustomer(new modelo_cliente().Listar(lbname.getText())));
                carritocompras.setSubTotal((float) carritocompras.getIdProduct().getPrice());
                carritocompras.insertar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
        //INSERTANDO LAS ESCENAS A LOS TABS
        tapInicio.setContent(interfaz2());
        tbpInterfaz.getTabs().addAll(tapInicio,tapCompras);
//------------------------------------------------------------------------------------------------
        //ESCENA PRINCIPAL
        //ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvPubli,imvLogo,txtBusqueda,lbname,imvUser,btnLogout,vbxCheck,vbxPrecio);
        ancEscena.getChildren().addAll(tbpInterfaz,btnBscar,imvLogo,txtBusqueda,lbname,imvUser,btnLogout,btnShoppinCart);
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
        if (lbname.getText().equals("user"))
        {
            this.close();
            Login objL = new Login();
        }
        else
            {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Action required");
            alert.setContentText("Are you sure of this?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                this.close();
                Login objL = new Login();
            }
            else
                {
                // ... user chose CANCEL or closed the dialog
            }
        }


    }
//-------------------------------------------------------------------------------------------------

    private void botones()
    {
        icono2 = new FontAwesomeIconView(FontAwesomeIcon.SIGN_IN);
        icono2.setSize("20");
        btnLogout = new JFXButton();
        btnLogout.setGraphic(icono2);
        btnLogout.setId("button_logout");
        btnLogout.setOnAction(event -> Salir());
        AnchorPane.setTopAnchor(btnLogout,15.0);
        AnchorPane.setLeftAnchor(btnLogout,1310.0);

        icono = new FontAwesomeIconView(FontAwesomeIcon.SHOPPING_CART);
        icono.setSize("12.5");
        btnShoppinCart = new JFXButton("Carrito de compras");
        btnShoppinCart.setGraphic(icono);
        btnShoppinCart.setOnAction(event -> llamadaVista());
        AnchorPane.setTopAnchor(btnShoppinCart,15.0);
        AnchorPane.setLeftAnchor(btnShoppinCart,1020.0);
            }
    private void llamadaVista()
    {

        nombre=lbname.getText();
        System.out.print("esto es: "+nombre);
            if(!nombre.equals("User")) {
                modelo_carritocompras mc = new modelo_carritocompras();
                try {
                    modelo_carritocompras carrito = new modelo_carritocompras();
                    carrito.setIdCart(6);
                    carrito.setIdCustomer(new modelo_cliente().BuscarCustomer(new modelo_cliente().Listar(lbname.getText())));
                    new Vista_carritocompras(carrito);
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Error Dialog");
                alert.setContentText("Please log in before you check your cart");

                alert.showAndWait();
            }


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
        spInicio.setPrefSize(800,600);
        ObservableList<modelo_productos> Lista = new modelo_productos().Listar();
        for (modelo_productos MP: Lista)
        {
            pro = pro+1;
            image = new ImageView("/sample/imagenes/mas.png");
            image.setFitHeight(20.0);
            image.setFitWidth(20.0);
            btnAgregar = new Button("Agregar a carrito",image);
            btnAgregar.setOnAction(event -> System.out.println("funciona"));
            lblname = new Label(MP.getNameProduct());
            lblPrice = new Label(""+MP.getPrice());
            lbldesc = new Label(MP.getDescription());
            try {
                imvImage = new ImageView(MP.getImage());
                imvImage.setFitHeight(170);
                imvImage.setFitWidth(250);
            }catch (Exception e)
            {
                imvImage = new ImageView("sample/imagenes/null.png");
            }
            VBox vbxProduct = new VBox();
            vbxProduct.getChildren().addAll(imvImage,lblname,lblPrice,lbldesc,btnAgregar);
            hbxContentProducts.getChildren().addAll(vbxProduct);
            hbxContentProducts.setSpacing(10.0);
            if (pro==3)
            {
                vbxContent.getChildren().addAll(hbxContentProducts);
                vbxContent.setSpacing(10.0);
                hbxContentProducts = new HBox();
                pro=0;
            }

        }
        vbxContent.getChildren().addAll(hbxContentProducts);
        vbxContent.setSpacing(10.0);
        spInicio.setContent(vbxContent);
        hbxNode.getChildren().addAll(vbxCheck,spInicio);
        hbxNode.setSpacing(10.0);
        return hbxNode;
    }
}
