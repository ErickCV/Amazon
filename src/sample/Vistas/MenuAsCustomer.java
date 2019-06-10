package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.FormatToController.*;
import sample.Modelos.*;
import sample.TDAs.Customers;
import sample.Tablas.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class MenuAsCustomer extends Stage {
    private Image imagelogo,imageuser,imagelogout;
    private ImageView imvlogo,imvuser,imvlogout;
    private JFXComboBox<Label> comboTablas, comboConsultas;
    private JFXButton btnUsuarios,btnResumen,btnNuevo,btnBorrar,btnEditar,btnLogout,btnConsulta3,btnActualizar;
    public JFXTextField txtBuscar;
    private HBox hbox1,hbox2,hBox3,hBox4;
    public VBox vbox1,vbox2,vboxMain,vBox3,vBoxTabla;
    private Label lbuser;
    private AnchorPane anchorPane1;
    private Scene scene;
    private int tabla = 0,rol = 0;
    private String user,contra;
    public  String opc = "";
    public TableView tableView;
    public Tabla_Categoria objC;
    public Tabla_Ciudad objCi;
    public Tabla_Cliente objCl;
    public Tabla_ContenidoPaquete objCP;
    public  Tabla_Direccion objD;
    public  Tabla_DireccionCliente objDC;
    public  Tabla_Estado objE;
    public Tabla_Factura objF;
    public Tabla_MetodoPago objMP;
    public  Tabla_Pais objP;

    public  Tabla_Productos objPro;
    public Tabla_Promocion objProm;
    public Tabla_Rol objR;
    public Tabla_Stock objS;
    public Tabla_Tienda objT;
    public  Tabla_TipoVenta objTV;
    public  Tabla_Venta objV;
    public Tabla_Usuarios objU;
    public Tabla_consulta2 objC2;
    public Tabla_consulta3 objC3;

    public ConexionBD objCon;
    public Connection con;
    private String string="",string2="";
    private Statement objSt;
    private ResultSet rs;

    private ObservableList<modelo_metodopago> listMetodoPago = null;
    private ObservableList<modelo_carritocompras> listCarritoCompras = null;
    private ObservableList<modelo_categoria> listCategoria= null;
    private ObservableList<modelo_ciudad> listCiudad = null;
    private ObservableList<modelo_cliente> listCliente = null;

    private ObservableList<modelo_direccion> listDireccion = null;
    private ObservableList<modelo_direccioncliente> listDireccionCliente = null;
    private ObservableList<modelo_estado> listEstado = null;
    private ObservableList<modelo_factura> listFactura = null;
    private ObservableList<modelo_pais> listPais= null;

    private ObservableList<modelo_productos> listProductos = null;
    private ObservableList<modelo_promocion> listPromocion = null;
    private ObservableList<modelo_rol> listRol = null;
    private ObservableList<modelo_stock> listStock = null;
    private ObservableList<modelo_tienda> listTienda = null;
    private ObservableList<modelo_tipoventa> listMTipoVenta = null;
    private ObservableList<modelo_venta> listVenta = null;
    private ObservableList<modelo_consulta2> listConsulta2 = null;
    private ObservableList<modelo_consulta2> listConsulta3 = null;

    private modelo_metodopago objmp;
    private modelo_carritocompras objcc;
    private modelo_categoria objca;
    private modelo_ciudad objci;
    private modelo_cliente objcl;
    private modelo_contenidopaquete objcp;
    private modelo_direccion objd;
    private modelo_direccioncliente objdc;
    private modelo_estado obje;
    private modelo_factura objf;
    private modelo_pais objp;

    private modelo_productos objpro;
    private modelo_promocion objprom;
    private modelo_rol objrol;
    private modelo_stock objs;
    private modelo_tienda objt;
    private modelo_tipoventa objtv;
    private modelo_venta objv;
    private modelo_usuarios obju;
    private modelo_consulta2 objc2;
    private modelo_consulta3 objc3;

    private Vista_carritocompras objvCC = null;
    private Vista_categoria objvC = null;
    private Vista_ciudad objvCi = null;
    private Vista_cliente objvCl = null;

    private Vista_direccion objvD = null;
    private Vista_direccioncliente objvDC = null;
    private Vista_estado objvE = null;
    private Vista_factura objvF = null;
    private Vista_metodopago objvMP = null;
    private Vista_pais objvP = null;

    private Vista_productos objvPro = null;
    private Vista_promocion objvProm = null;
    private Vista_rol objvR = null;
    private Vista_stock objvS = null;
    private Vista_tienda objvT = null;
    private Vista_tipoventa objvTv = null;
    private Vista_venta objvV = null;

    public Stage Stage;
    public FXMLLoader loader;

    public MenuAsCustomer(String user, String contra)
    {
        this.user=user;
        this.contra=contra;
        creaGUI();
        txtBuscar.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(tabla!=0)
                    try {
                        objCon = new ConexionBD();
                        con = objCon.getConectar();
                        String query = "select *" +
                                "       from " + string +
                                "       where " + string2 + " like " + "'%" + txtBuscar.getText() + "%'";
                        objSt = con.createStatement();
                        rs = objSt.executeQuery(query);

                        switch (tabla){
                            case 1:
                                listCarritoCompras = FXCollections.observableArrayList();
                                Customers customers;
                                while (rs.next())
                                {
                                    objcc = new modelo_carritocompras();
                                    customers=new Customers();
                                    customers.setIdCustomer(rs.getString("idCustomer"));
                                    objcc.setIdCustomer(customers);//acabo de modificar esta linea
                                    objcc.setIdCart(rs.getInt("idCart"));
                                    objcc.setSubTotal(rs.getFloat("subTotal"));
                                    listCarritoCompras.add(objcc);
                                }
                                con.close();
                                break;

                            case 2:
                                listCategoria = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objca = new modelo_categoria();
                                    objca.setIdCategory(rs.getInt("idCategory"));
                                    objca.setName(rs.getString("name"));
                                    objca.setDescription(rs.getString("description"));
                                    objca.setImage(rs.getString("image"));
                                    listCategoria.add(objca);
                                }
                                con.close();

                                objC.tableViewCategoria.setItems(listCategoria);
                                break;
                            case 3:
                                listCiudad = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objci = new modelo_ciudad();
                                    objci.setIdCity(rs.getInt("idCity"));
                                    objci.setName(rs.getString("name"));
                                    objci.setIdCountry(rs.getInt("idCountry"));
                                    objci.setIdState(rs.getInt("idState"));
                                    listCiudad.add(objci);
                                }
                                con.close();

                                objCi.tableViewCiudad.setItems(listCiudad);
                                break;
                            case 4:
                                listCliente = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objcl = new modelo_cliente();
                                    objcl.setIdCustomer(rs.getInt("idCustomer"));
                                    objcl.setName(rs.getString("name"));
                                    objcl.setLastName(rs.getString("lastName"));
                                    objcl.setGender(rs.getString("gender"));
                                    objcl.setClave(rs.getString("clave"));
                                    listCliente.add(objcl);
                                }
                                con.close();

                                objCl.tableViewCliente.setItems(listCliente);
                                break;
                            case 5:

                                break;
                            case 6:
                                listDireccion = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objd = new modelo_direccion();
                                    objd.setIdAddress(rs.getInt("idAddress"));
                                    objd.setStreet(rs.getString("street"));
                                    objd.setEmail(rs.getString("email"));
                                    objd.setPhone(rs.getString("phone"));
                                    objd.setNameCommerce(rs.getString("nameCommerce"));
                                    objd.setIdCity(rs.getInt("idCity"));
                                    objd.setIdState(rs.getInt("idState"));
                                    objd.setIdCountry(rs.getInt("idCountry"));
                                    objd.setCp(rs.getInt("cp"));
                                    listDireccion.add(objd);
                                }
                                con.close();

                                objD.tableViewDireccion.setItems(listDireccion);
                                break;
                            case 7:
                                listDireccionCliente = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objdc = new modelo_direccioncliente();
                                    objdc.setIdCustomer(rs.getInt("idCustomer"));
                                    objdc.setIdAddress(rs.getInt("idAddress"));

                                }
                                con.close();

                                objDC.tableViewDireccionCliente.setItems(listDireccionCliente);
                                break;
                            case 8:
                                listEstado = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    obje = new modelo_estado();
                                    obje.setIdState(rs.getInt("idState"));
                                    obje.setIdCountry(rs.getInt("idCountry"));
                                    obje.setName(rs.getString("name"));

                                    listEstado.add(obje);
                                }
                                con.close();

                                objE.tableViewEstado.setItems(listEstado);
                                break;
                            case 9:
                                listFactura = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objf = new modelo_factura();
                                    objf.setIdInvoice(rs.getInt("idInvoice"));
                                    objf.setIdSale(rs.getInt("idSale"));
                                    objf.setDateInvoice(rs.getDate("dateInvoice"));
                                    objf.setIdUser(rs.getInt("idUser"));

                                }
                                con.close();

                                objF.tableViewFactura.setItems(listFactura);
                                break;
                            case 10:
                                break;
                            case 11:
                                try
                                {
                                    listMetodoPago = FXCollections.observableArrayList();
                                    while (rs.next())
                                    {
                                        objmp = new modelo_metodopago();
                                        objmp.setIdPayment((rs.getInt("idPayment")));
                                        objmp.setTypePayment((rs.getString("typePayment")));
                                        objmp.setDescription(((rs.getString("decription"))));
                                        listMetodoPago.addAll(objmp);
                                    }
                                    con.close();

                                    objMP.tableViewMetodoPago.setItems(listMetodoPago);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                                break;
                            case 12:
                                listPais = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objp = new modelo_pais();
                                    objp.setIdCountry((rs.getInt("idCountry")));
                                    objp.setName((rs.getString("name")));
                                    listPais.addAll(objp);
                                }
                                con.close();

                                objP.tableViewPais.setItems(listPais);
                                break;
                            case 13:

                                break;
                            case 14:
                                listProductos = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objpro = new modelo_productos();
                                    objpro.setIdProduct(rs.getInt("idProduct"));
                                    objpro.setNameProduct(rs.getString("nameProduct"));
                                    objpro.setIdCategory(rs.getInt("idCategory"));
                                    objpro.setDescription(rs.getString("description"));
                                    objpro.setPrice(rs.getFloat("price"));
                                    objpro.setImage(rs.getString("image"));
                                    listProductos.addAll(objpro);
                                }
                                con.close();

                                objPro.tableViewProductos.setItems(listProductos);
                                break;
                            case 15:
                                listPromocion = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objprom = new modelo_promocion();
                                    objprom.setIdPromo(rs.getInt("idPromo"));
                                    objprom.setName(rs.getString("name"));
                                    objprom.setDatePromo(rs.getDate("datePromo"));
                                    listPromocion.addAll(objprom);
                                }
                                con.close();

                                objProm.tableViewPromocion.setItems(listPromocion);
                                break;
                            case 16:
                                listRol = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objrol = new modelo_rol();
                                    objrol.setIdRole(rs.getInt("idRole"));
                                    objrol.setName(rs.getString("name"));
                                    objrol.setDescription(rs.getString("description"));
                                    listRol.add(objrol);
                                }
                                con.close();

                                objR.tableViewRol.setItems(listRol);
                                break;
                            case 17:
                                listStock = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objs = new modelo_stock();
                                    objs.setIdProduct(rs.getInt("idProduct"));
                                    objs.setIdStore(rs.getInt("idStore"));
                                    objs.setQuantity(rs.getInt("quantity"));
                                    listStock.addAll(objs);
                                }
                                con.close();

                                objMP.tableViewMetodoPago.setItems(listMetodoPago);
                                break;
                            case 18:
                                listTienda = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objt = new modelo_tienda();
                                    objt.setIdStore(rs.getInt("idStore"));
                                    objt.setIdAddress(rs.getInt("idAddress"));
                                    listTienda.addAll(objt);
                                }
                                con.close();

                                objT.tableViewTienda.setItems(listTienda);
                                break;
                            case 19:
                                listMTipoVenta = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objtv = new modelo_tipoventa();
                                    objtv.setIdTypeSale(rs.getInt("idTypeSale"));
                                    objtv.setName(rs.getString("name"));
                                    objtv.setDescription(rs.getString("description"));
                                    listMTipoVenta.addAll(objtv);
                                }
                                con.close();

                                objTV.tableViewTipoVenta.setItems(listMTipoVenta);
                                break;
                            case 20:
                                listVenta = FXCollections.observableArrayList();
                                while (rs.next())
                                {
                                    objv = new modelo_venta();
                                    objv.setIdSale(rs.getInt("idSale"));
                                    objv.setIdCustomer(rs.getInt("idCustomer"));
                                    objv.setIdCart(rs.getInt("idCart"));
                                    objv.setIdUser(rs.getInt("idUser"));
                                    objv.setTotal(rs.getDouble("total"));
                                    objv.setIdPayment(rs.getInt("idPayment"));
                                    objv.setIdTypeSale(rs.getInt("idTypeSale"));
                                    objv.setIdPromo(rs.getInt("idPromo"));
                                    objv.setIdStore(rs.getInt("idStore"));
                                    objv.setDate(rs.getDate("date"));
                                    listVenta.addAll(objv);
                                }
                                con.close();

                                objV.tableViewVenta.setItems(listVenta);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Something is wrong");
                    alert.setContentText("Please, select the table first");

                    alert.showAndWait();
                }

            }
        });


        scene = new Scene(vbox2,950,630);
        this.setScene(scene);
        this.setTitle("Administration view");
        scene.getStylesheets().add("/sample/CSS/estilo.css");
        this.setResizable(false);
        this.show();
    }

    public void creaGUI()
    {
        anchorPane1 = new AnchorPane();
        hbox1 = new HBox();
        hbox2 =  new HBox();
        hBox3 = new HBox();
        hBox4 = new HBox();
        vbox1 = new VBox();
        vbox2 =  new VBox();
        vBox3 = new VBox();
        vBoxTabla = new VBox();
        vBoxTabla.setPrefSize(570,300);
        vboxMain =  new VBox();

        imageuser = new Image("/sample/Imagenes/usuario.png");
        imagelogo = new Image("/sample/Imagenes/logo.png");
        imvlogo =  new ImageView(imagelogo);
        imvuser =  new ImageView(imageuser);
        lbuser = new Label(user);
        lbuser.setId("label_blancos");

        //-----------estilo----------------
        imvlogo.setId("imv");

        Rectangle clip1 = new Rectangle(
                imagelogo.getWidth(),imagelogo.getHeight()
        );
        clip1.setArcHeight(20);
        clip1.setArcWidth(20);
        imvlogo.setClip(clip1);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imvlogo.snapshot(parameters,null);
        imvlogo.setClip(null);
        imvlogo.setEffect(new DropShadow(10, Color.BLACK));
        imvlogo.setImage(image);
        //----------------------------------


        //-----------estilo----------------
        imvuser.setId("imv");

        Circle clip2 = new Circle();
        clip2.setCenterX(100.0f);
        clip2.setCenterY(100.0f);
        clip2.setRadius(1000.0f);

        imvuser.setClip(clip2);
        SnapshotParameters parameters2 = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image2 = imvuser.snapshot(parameters,null);
        imvuser.setClip(null);
        imvuser.setEffect(new DropShadow(5, Color.WHITE));
        imvuser.setImage(image2);
        //----------------------------------

        imvlogo.setFitWidth(400);
        imvlogo.setFitHeight(150);

        imvuser.setFitWidth(35);
        imvuser.setFitHeight(35);

        txtBuscar =  new JFXTextField();
        txtBuscar.setPromptText("Buscar...");
        anchorPane1 = new AnchorPane();

        btnUsuarios = new JFXButton("Clientes-Ventas");
        btnUsuarios.setId("button_options");
        btnUsuarios.setOnAction(event -> Usuarios());
        btnResumen= new JFXButton("Resumen-Ventas");
        btnResumen.setId("button_options");
        btnResumen.setOnAction(event -> Resumen());
        comboTablas =  new JFXComboBox<>();
        comboTablas.setPromptText("Tablas");
        comboTablas.getItems().addAll(new Label("Carrito de Compras"),
                new Label("Categorias"),
                new Label("Ciudad"),


                new Label("Direccion"),
                new Label("Direccion Cliente"),
                new Label("Estado"),
                new Label("Factura"),
                new Label("Lista Carrito"),
                new Label("Metodo de pago"),
                new Label("Pais"),

                new Label("Productos"),
                new Label("Promociones"),
                new Label("Rol"),
                new Label("Stock"),
                new Label("Tienda"),
                new Label("Tipo de venta"),
                new Label("Venta"));

        comboTablas.setOnAction(event -> Tabla(comboTablas.getSelectionModel().getSelectedItem().getText()));
        btnActualizar = new JFXButton("   ");
        btnActualizar.setOnAction(event -> Actualizar());
        btnActualizar.setId("button_actualizar");
        btnConsulta3 = new JFXButton("Sin vender");
        btnConsulta3.setOnAction(event -> NoVenta() );
        btnNuevo = new JFXButton("Nuevo");
        btnNuevo.setId("button_options");
        btnNuevo.setOnAction(event -> Nuevo());
        btnEditar = new JFXButton("Editar");
        btnEditar.setId("button_options");
        btnEditar.setOnAction(event -> Editar());
        btnBorrar = new JFXButton("Borrar");
        btnBorrar.setId("button_options");
        btnBorrar.setOnAction(event -> Borrar(tabla));
        btnLogout = new JFXButton();
        btnLogout.setId("button_logout");
        btnLogout.setPrefSize(35,35);
        btnLogout.setOnAction(event -> Salir());

        hbox1.getChildren().addAll(lbuser,imvuser,btnLogout);
        hbox1.setSpacing(10);
        AnchorPane.setTopAnchor(hbox1,10.0);
        AnchorPane.setRightAnchor(hbox1,10.0);
        AnchorPane.setTopAnchor(imvlogo,10.0);
        AnchorPane.setLeftAnchor(imvlogo,10.0);

        hbox2.getChildren().addAll(comboTablas,btnUsuarios,btnResumen,btnConsulta3,btnActualizar);//tablas,usuarios,resumen
        hbox2.setSpacing(50);

        anchorPane1.getChildren().addAll(imvlogo,hbox1);//logo,usuario,ayuda

        vBox3.getChildren().addAll(txtBuscar,hBox3);//agregar aqui mas botones de consultas
        vBox3.setSpacing(10);

        System.out.println("user despues de login "+user);
        System.out.println("clave despues de login "+contra);
        if(user.equals("Erick") && contra.equals("itgd"))
        {
            btnNuevo.setDisable(false);
            btnBorrar.setDisable(false);
            btnEditar.setDisable(false);
        }
        else
        {
            btnNuevo.setDisable(true);
            btnBorrar.setDisable(true);
            btnEditar.setDisable(true);
        }
        hBox3.getChildren().addAll(btnNuevo,btnEditar,btnBorrar);
        hBox3.setSpacing(10);

        vbox1.getChildren().addAll(anchorPane1,hbox2);
        vbox1.setSpacing(10);

        hBox4.getChildren().addAll(vBoxTabla,vBox3);
        hBox4.setSpacing(70);

        vbox2.getChildren().addAll(vbox1,hBox4);//contenedor principal
        vbox2.setPadding(new Insets(10));
        vbox2.setSpacing(10);

        //-------

    }

    private void Editar()
    {
        if(tabla!=0)
            switch (tabla)
            {
                case 1:
                    break;
                case 2:
                    modelo_categoria objetoCA = objC.tableViewCategoria.getSelectionModel().getSelectedItem();
                    objvC = new Vista_categoria(2,objetoCA);
                    break;
                case 3:
                    modelo_ciudad objetoCi = objCi.tableViewCiudad.getSelectionModel().getSelectedItem();
                    objvCi = new Vista_ciudad(2,objetoCi);
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    modelo_direccion objetoD = objD.tableViewDireccion.getSelectionModel().getSelectedItem();
                    objvD = new Vista_direccion(2,objetoD);
                    break;

                case 7:
                    modelo_direccioncliente objetoDC = objDC.tableViewDireccionCliente.getSelectionModel().getSelectedItem();
                    objvDC = new Vista_direccioncliente(2,objetoDC);
                    break;
                case 8:
                    modelo_estado objetoE = objE.tableViewEstado.getSelectionModel().getSelectedItem();
                    objvE = new Vista_estado(2,objetoE);
                    break;
                case 9:
                    modelo_factura objetoF = objF.tableViewFactura.getSelectionModel().getSelectedItem();
                    //objvF = new Vista_factura(2,objetoF);
                    break;
                case 10:
                    break;
                case 11:
                    modelo_metodopago objetoMP = objMP.tableViewMetodoPago.getSelectionModel().getSelectedItem();
                    //objvMP = new Vista_metodopago(2,objetoMP);
                    break;
                case 12:
                    modelo_pais objetoP = objP.tableViewPais.getSelectionModel().getSelectedItem();
                    objvP = new Vista_pais(2,objetoP);
                    break;
               /* case 13:
                    modelo_paquete objetoPa = objPa.tableViewPaquete.getSelectionModel().getSelectedItem();
                    objvPa = new Vista_carritocompras(2,objetoPa);
                    break;
                case 14:
                    modelo_productos objetoPro = objPro.tableViewProductos.getSelectionModel().getSelectedItem();
                    objvPro = new Vista_carritocompras(2,objetoPro);
                    break;
                case 15:
                    modelo_promocion objetoProm = objProm.tableViewPromocion.getSelectionModel().getSelectedItem();
                    objvPro = new Vista_carritocompras(2,objetoProm);
                    break;
                case 16:
                    modelo_rol objetoR = objR.tableViewRol.getSelectionModel().getSelectedItem();
                    objvR = new Vista_carritocompras(2,objetoR);
                    break;
                case 17:
                    modelo_stock objetoS = objS.tableViewStock.getSelectionModel().getSelectedItem();
                    objvS = new Vista_carritocompras(2,objetoS);
                    break;
                case 18:
                    modelo_tienda objetoT = objT.tableViewTienda.getSelectionModel().getSelectedItem();
                    objvT = new Vista_carritocompras(2,objetoT);
                    break;
                case 19:
                    modelo_tipoventa objetoTv = objTV.tableViewTipoVenta.getSelectionModel().getSelectedItem();
                    objvTv = new Vista_carritocompras(2,objetoTv);
                    break;
                case 20:
                    modelo_venta objetoV = objV.tableViewVenta.getSelectionModel().getSelectedItem();
                    objvV = new Vista_carritocompras(2,objetoV);
            */

            }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Something is wrong");
            alert.setContentText("Please, select the table first");

            alert.showAndWait();
        }
    }

    private void NoVenta()
    {
        vBoxTabla.getChildren().clear();
        objC3 = new Tabla_consulta3();
        objC3.close();
        vBoxTabla.getChildren().addAll(objC3.tableViewConsulta3);
        tabla = 23;
    }

    private void Resumen() {
        vBoxTabla.getChildren().clear();
        objC2 = new Tabla_consulta2();
        objC2.close();
        vBoxTabla.getChildren().addAll(objC2.tableViewConsulta2);
        tabla = 22;
    }

    private void Usuarios() {
        vBoxTabla.getChildren().clear();
        objU = new Tabla_Usuarios();
        objU.close();
        vBoxTabla.getChildren().addAll(objU.tableViewUsuarios);
        tabla = 21;
    }


    private void Nuevo() {
        if(tabla!=0)
            switch (tabla){
                case 1:
                    //Vista_carritocompras objCC = new Vista_carritocompras(1,objcc);
                    break;
                case 2:
                    Vista_categoria objCa = new Vista_categoria(1,objca);
                    break;
                case 3:
                    Vista_ciudad objCi = new Vista_ciudad(1,objci);
                    break;
                case 4:

                    break;
                case 5:
                    Vista_contenidopaquete objCP = new Vista_contenidopaquete(1,objcp);
                    break;
                case 6:
                    Vista_direccion objD = new Vista_direccion(1,objd);
                    break;
                case 7:
                    Vista_direccioncliente objDC = new Vista_direccioncliente(1,objdc);
                    break;
                case 8:
                    Vista_estado objE = new Vista_estado(1,obje);
                    break;
                case 9:
                    //Vista_factura objF = new Vista_factura(1,objf);
                    break;
                case 10:
                    break;
                case 11:
                    //Vista_metodopago objMPa = new Vista_metodopago(1,objmp);
                    break;
                case 12:
                    Vista_pais objP = new Vista_pais(1,objp);
                    break;
                case 13:
                    //Stage stage= new Stage();
                   // showPackage(stage);
                    break;
                case 14:
                    Stage stage2= new Stage();
                    showProduct(stage2);
                    break;
                case 15:
                    Stage stage3= new Stage();
                    showPromotion(stage3);
                    break;
                case 16:
                    Stage stage4= new Stage();
                    showRol(stage4);
                    break;
                case 17:
                    Stage stage5= new Stage();
                    showStock(stage5);
                    break;
                case 18:
                    Stage stage6= new Stage();
                    showStore(stage6);
                    break;
                case 19:
                    Stage stage7= new Stage();
                    showTypeSale(stage7);
                    break;
                case 20:
                    Stage stage8= new Stage();
                    showSale(stage8);
            }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Something is wrong");
            alert.setContentText("Please, select the table first");

            alert.showAndWait();
        }
    }

    public void Actualizar()
    {
        if(tabla!=0)
            switch (tabla){
                case 1:
                    vBoxTabla.getChildren().clear();
                    break;
                case 2:
                    vBoxTabla.getChildren().clear();
                    objC = new Tabla_Categoria();
                    vBoxTabla.getChildren().addAll(objC.tableViewCategoria);
                    objC.close();
                    break;
                case 3:
                    vBoxTabla.getChildren().clear();
                    objCi = new Tabla_Ciudad();
                    vBoxTabla.getChildren().addAll(objCi.tableViewCiudad);
                    objCi.close();
                    break;
                case 4:
                    vBoxTabla.getChildren().clear();
                    objCl = new Tabla_Cliente();
                    vBoxTabla.getChildren().addAll(objCl.tableViewCliente);
                    objCl.close();
                    break;
                case 5:
                    vBoxTabla.getChildren().clear();
                    objCP = new Tabla_ContenidoPaquete();
                    vBoxTabla.getChildren().addAll(objCP.tableViewContenidoPaquete);
                    objCP.close();

                    break;
                case 6:
                    vBoxTabla.getChildren().clear();
                    objD = new Tabla_Direccion();
                    vBoxTabla.getChildren().addAll(objD.tableViewDireccion);
                    objD.close();

                    break;
                case 7:
                    vBoxTabla.getChildren().clear();
                    objDC = new Tabla_DireccionCliente();
                    vBoxTabla.getChildren().addAll(objDC.tableViewDireccionCliente);
                    objDC.close();

                    break;
                case 8:
                    vBoxTabla.getChildren().clear();
                    objE = new Tabla_Estado();
                    vBoxTabla.getChildren().addAll(objE.tableViewEstado);
                    objE.close();

                    break;
                case 9:
                    vBoxTabla.getChildren().clear();
                    objF = new Tabla_Factura();
                    vBoxTabla.getChildren().addAll(objF.tableViewFactura);
                    objF.close();

                    break;
                case 10:
                    vBoxTabla.getChildren().clear();
                    break;
                case 11:
                    vBoxTabla.getChildren().clear();
                    objMP = new Tabla_MetodoPago();
                    vBoxTabla.getChildren().addAll(objMP.tableViewMetodoPago);
                    objMP.close();

                    break;
                case 12:
                    vBoxTabla.getChildren().clear();
                    objP = new Tabla_Pais();
                    vBoxTabla.getChildren().addAll(objP.tableViewPais);
                    objP.close();

                    break;
                case 13:


                    break;
                case 14:
                    vBoxTabla.getChildren().clear();
                    objPro = new Tabla_Productos();
                    vBoxTabla.getChildren().addAll(objPro.tableViewProductos);
                    objPro.close();

                    break;
                case 15:
                    vBoxTabla.getChildren().clear();
                    objProm = new Tabla_Promocion();
                    vBoxTabla.getChildren().addAll(objProm.tableViewPromocion);
                    objProm.close();

                    break;
                case 16:
                    vBoxTabla.getChildren().clear();
                    objR = new Tabla_Rol();
                    vBoxTabla.getChildren().addAll(objR.tableViewRol);
                    objR.close();

                    break;
                case 17:
                    vBoxTabla.getChildren().clear();
                    objS = new Tabla_Stock();
                    vBoxTabla.getChildren().addAll(objS.tableViewStock);
                    objS.close();

                    break;
                case 18:
                    vBoxTabla.getChildren().clear();
                    objT= new Tabla_Tienda();
                    vBoxTabla.getChildren().addAll(objT.tableViewTienda);
                    objT.close();

                    break;
                case 19:
                    vBoxTabla.getChildren().clear();
                    objTV= new Tabla_TipoVenta();
                    vBoxTabla.getChildren().addAll(objTV.tableViewTipoVenta);
                    objTV.close();
                    break;
                case 20:

                    vBoxTabla.getChildren().clear();
                    objV = new Tabla_Venta();
                    vBoxTabla.getChildren().addAll(objV.tableViewVenta);
                    objV.close();
            }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Something is wrong");
            alert.setContentText("Please, select the table first");

            alert.showAndWait();
        }
    }

    private void Salir() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Action required");
        alert.setContentText("Are you sure of this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            this.close();
            MenuAsUser objetomau = new MenuAsUser("User","123");
        } else
        {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void Borrar(int tabla)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Action required");
        alert.setContentText("Are you sure of this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            // ... user chose OK
            switch (tabla)
            {
                case 1:

                    break;
                case 2:
                    modelo_categoria objetoC = objC.tableViewCategoria.getSelectionModel().getSelectedItem();
                    objetoC.setIdCategory(objetoC.getIdCategory());
                    objetoC.setName(objetoC.getName());
                    objetoC.setDescription(objetoC.getDescription());
                    objetoC.setImage((objetoC.getImage()));
                    objetoC.Borrar();
                    objC.tableViewCategoria.refresh();
                    break;
                case 3:
                    modelo_ciudad objetoCi = objCi.tableViewCiudad.getSelectionModel().getSelectedItem();
                    objetoCi.setIdCity(objetoCi.getIdCity());
                    objetoCi.setName(objetoCi.getName());
                    objetoCi.setIdCountry(objetoCi.getIdCountry());
                    objetoCi.setIdState(objetoCi.getIdState());
                    objetoCi.Borrar();
                    objR.tableViewRol.refresh();
                    break;
                case 4:
                    modelo_cliente objetoCL = objCl.tableViewCliente.getSelectionModel().getSelectedItem();
                    objetoCL.setIdCustomer(objetoCL.getIdCustomer());
                    objetoCL.setName(objetoCL.getName());
                    objetoCL.setLastName(objetoCL.getLastName());
                    objetoCL.setGender(objetoCL.getGender());
                    objetoCL.Borrar();
                    objCl.tableViewCliente.refresh();
                    break;
                case 5:

                    break;
                case 6:
                    modelo_direccion objetoD = objD.tableViewDireccion.getSelectionModel().getSelectedItem();
                    objetoD.setIdAddress(objetoD.getIdAddress());
                    objetoD.setIdCountry(objetoD.getIdCountry());
                    objetoD.setIdCity(objetoD.getIdCity());
                    objetoD.setIdState(objetoD.getIdState());
                    objetoD.setStreet(objetoD.getStreet());
                    objetoD.setNameCommerce(objetoD.getNameCommerce());
                    objetoD.setCp(objetoD.getCp());
                    objetoD.setEmail(objetoD.getEmail());
                    objetoD.setPhone(objetoD.getPhone());
                    objetoD.Borrar();
                    objD.tableViewDireccion.refresh();
                    break;
                case 7:
                    modelo_direccioncliente objetoDC = objDC.tableViewDireccionCliente.getSelectionModel().getSelectedItem();
                    objetoDC.setIdCustomer(objetoDC.getIdCustomer());
                    objetoDC.setIdAddress(objetoDC.getIdAddress());
                    objetoDC.Borrar();
                    objDC.tableViewDireccionCliente.refresh();
                    break;
                case 8:
                    modelo_estado objetoE = objE.tableViewEstado.getSelectionModel().getSelectedItem();
                    objetoE.setIdState(objetoE.getIdState());
                    objetoE.setName(objetoE.getName());
                    objetoE.setIdCountry(objetoE.getIdCountry());
                    objetoE.Borrar();
                    objE.tableViewEstado.refresh();
                    break;
                case 9:
                    modelo_factura objetoF = objF.tableViewFactura.getSelectionModel().getSelectedItem();
                    objetoF.setIdInvoice(objetoF.getIdInvoice());
                    objetoF.setIdSale(objetoF.getIdSale());
                    objetoF.setIdUser(objetoF.getIdUser());
                    objetoF.setDateInvoice(objetoF.getDateInvoice());
                    objetoF.Borrar();
                    objR.tableViewRol.refresh();
                    break;
                case 10:
                    objR.tableViewRol.refresh();
                    break;
                case 11:
                    modelo_metodopago objetoMP = objMP.tableViewMetodoPago.getSelectionModel().getSelectedItem();
                    objetoMP.setIdPayment(objetoMP.getIdPayment());
                    objetoMP.setTypePayment(objetoMP.getTypePayment());
                    objetoMP.setDescription(objetoMP.getDescription());
                    objetoMP.Borrar();
                    objMP.tableViewMetodoPago.refresh();
                    break;
                case 12:
                    modelo_pais objetoP = objP.tableViewPais.getSelectionModel().getSelectedItem();
                    objetoP.setIdCountry(objetoP.getIdCountry());
                    objetoP.setName(objetoP.getName());
                    objetoP.Borrar();
                    objP.tableViewPais.refresh();
                    break;
                case 13:

                    break;
                case 14:
                    modelo_productos objetoPro = objPro.tableViewProductos.getSelectionModel().getSelectedItem();
                    objetoPro.setIdProduct(objetoPro.getIdProduct());
                    objetoPro.setIdCategory(objetoPro.getIdCategory());
                    objetoPro.setNameProduct(objetoPro.getNameProduct());
                    objetoPro.setDescription(objetoPro.getDescription());
                    objetoPro.setPrice(objetoPro.getPrice());
                    objetoPro.setImage(objetoPro.getImage());
                    objetoPro.Borrar();
                    objPro.tableViewProductos.refresh();
                    break;
                case 15:
                    modelo_promocion objetoProm = objProm.tableViewPromocion.getSelectionModel().getSelectedItem();
                    objetoProm.setIdPromo(objetoProm.getIdPromo());
                    objetoProm.setName(objetoProm.getName());
                    objetoProm.setDatePromo(objetoProm.getDatePromo());
                    objetoProm.Borrar();
                    objProm.tableViewPromocion.refresh();
                    break;
                case 16:
                    modelo_rol objeto = objR.tableViewRol.getSelectionModel().getSelectedItem();
                    objeto.setIdRole(objeto.getIdRole());
                    objeto.setName(objeto.getName());
                    objeto.setDescription(objeto.getDescription());
                    objeto.Borrar();
                    objR.tableViewRol.refresh();
                    break;
                case 17:
                    modelo_stock objetoS = objS.tableViewStock.getSelectionModel().getSelectedItem();
                    objetoS.setIdProduct(objetoS.getIdProduct());
                    objetoS.setIdStore(objetoS.getIdStore());
                    objetoS.setQuantity(objetoS.getQuantity());
                    objetoS.Borrar();
                    objS.tableViewStock.refresh();
                    break;
                case 18:
                    modelo_tienda objetot = objT.tableViewTienda.getSelectionModel().getSelectedItem();
                    objetot.setIdAddress(objetot.getIdAddress());
                    objetot.setIdStore(objetot.getIdStore());
                    objetot.Borrar();
                    objT.tableViewTienda.refresh();
                    //no se puede borrar porque es n:n
                    break;
                case 19:
                    modelo_tipoventa objetotv = objTV.tableViewTipoVenta.getSelectionModel().getSelectedItem();
                    objetotv.setIdTypeSale(objetotv.getIdTypeSale());
                    objetotv.setName(objetotv.getName());
                    objetotv.setDescription(objetotv.getDescription());
                    objetotv.Borrar();
                    //no se puede borrar porque es n:n
                    break;
                case 20:
                    modelo_venta objetoV = objV.tableViewVenta.getSelectionModel().getSelectedItem();
                    objetoV.setIdSale(objetoV.getIdSale());
                    objetoV.setIdTypeSale(objetoV.getIdTypeSale());
                    objetoV.setIdCustomer(objetoV.getIdCustomer());
                    objetoV.setIdUser(objetoV.getIdUser());
                    objetoV.setIdCart(objetoV.getIdCart());
                    objetoV.setIdStore(objetoV.getIdStore());
                    objetoV.setIdPromo(objetoV.getIdPromo());
                    objetoV.setTotal(objetoV.getTotal());
                    objetoV.Borrar();
                    objV.tableViewVenta.refresh();
            }
        } else
        {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void Tabla(String opc)
    {
        this.opc = opc;
        // txtBuscar.setOnKeyPressed(new GestorTeclado(this,opc));
        switch (opc)
        {
            case "Carrito de Compras":
                vBoxTabla.getChildren().clear();
                tabla = 1;
                string ="ShoppingCart";
                string2="idCart";
                txtBuscar.setPromptText("idCart");
                break;
            case "Categorias":
                vBoxTabla.getChildren().clear();
                objC = new Tabla_Categoria();
                objC.close();
                vBoxTabla.getChildren().addAll(objC.tableViewCategoria);
                string = "Category";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 2;
                break;
            case "Ciudad":
                vBoxTabla.getChildren().clear();
                objCi = new Tabla_Ciudad();
                objCi.close();
                vBoxTabla.getChildren().addAll(objCi.tableViewCiudad);
                tabla = 3;
                string ="City";
                string2 = "name";
                txtBuscar.setPromptText("name");
                break;
            case "Cliente":
                vBoxTabla.getChildren().clear();
                objCl = new Tabla_Cliente();
                objCl.close();
                vBoxTabla.getChildren().addAll(objCl.tableViewCliente);
                string ="Customers";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 4;
                break;
            case "Contenido Paquete":
                vBoxTabla.getChildren().clear();
                objCP = new Tabla_ContenidoPaquete();
                objCP.close();
                vBoxTabla.getChildren().addAll(objCP.tableViewContenidoPaquete);
                string ="PackageContent";
                string2 = "detail";
                txtBuscar.setPromptText("detail");
                tabla = 5;
                break;
            case "Direccion":
                vBoxTabla.getChildren().clear();
                objD = new Tabla_Direccion();
                objD.close();
                vBoxTabla.getChildren().addAll(objD.tableViewDireccion);
                string ="Address";
                string2 = "street";
                txtBuscar.setPromptText("street");
                tabla = 6;
                break;
            case "Direccion Cliente":
                vBoxTabla.getChildren().clear();
                objDC = new Tabla_DireccionCliente();
                objDC.close();
                vBoxTabla.getChildren().addAll(objDC.tableViewDireccionCliente);
                string="AddressCustomer";
                string2 = "idCustomer";
                txtBuscar.setPromptText("idCustomer");
                tabla = 7;
                break;
            case "Estado":
                vBoxTabla.getChildren().clear();
                objE = new Tabla_Estado();
                objE.close();
                vBoxTabla.getChildren().addAll(objE.tableViewEstado);
                string = "State";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 8;
                break;
            case "Factura":
                vBoxTabla.getChildren().clear();
                objF = new Tabla_Factura();
                objF.close();
                vBoxTabla.getChildren().addAll(objF.tableViewFactura);
                string ="invoice";
                string2 = "dateInvoice";
                txtBuscar.setPromptText("dateInvoice");
                tabla = 9;
                break;
            case "Metodo de pago":
                vBoxTabla.getChildren().clear();
                objMP = new Tabla_MetodoPago();
                vBoxTabla.getChildren().addAll(objMP.tableViewMetodoPago);
                objMP.close();
                string ="PaymentMethod";
                string2 = "decription";
                txtBuscar.setPromptText("description");
                tabla = 11;
                break;
            case "Pais":
                vBoxTabla.getChildren().clear();
                objP = new Tabla_Pais();
                objP.close();
                vBoxTabla.getChildren().addAll(objP.tableViewPais);
                string ="Country";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 12;
                break;
            case "Paquete":

                break;
            case "Productos":
                vBoxTabla.getChildren().clear();
                objPro = new Tabla_Productos();
                objPro.close();
                vBoxTabla.getChildren().addAll(objPro.tableViewProductos);
                string ="Product";
                string2 = "nameProduct";
                txtBuscar.setPromptText("nameProduct");
                tabla = 14;
                break;
            case "Promociones":
                vBoxTabla.getChildren().clear();
                objProm = new Tabla_Promocion();
                objProm.close();
                vBoxTabla.getChildren().addAll(objProm.tableViewPromocion);
                string ="Promotion";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 15;
                break;
            case "Rol":
                vBoxTabla.getChildren().clear();
                objR = new Tabla_Rol();
                objR.close();
                vBoxTabla.getChildren().addAll(objR.tableViewRol);
                string ="role";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 16;
                break;
            case "Stock":
                vBoxTabla.getChildren().clear();
                objS = new Tabla_Stock();
                objS.close();
                vBoxTabla.getChildren().addAll(objS.tableViewStock);
                string ="stock";
                string2 = "quantity";
                txtBuscar.setPromptText("quantity");
                tabla = 17;
                break;
            case "Tienda":
                vBoxTabla.getChildren().clear();
                objT= new Tabla_Tienda();
                objT.close();
                vBoxTabla.getChildren().addAll(objT.tableViewTienda);
                string ="Store";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 18;
                break;
            case "Tipo de venta":
                vBoxTabla.getChildren().clear();
                objTV= new Tabla_TipoVenta();
                objTV.close();
                vBoxTabla.getChildren().addAll(objTV.tableViewTipoVenta);
                string ="TypeSale";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla = 19;
                break;
            case "Venta":
                vBoxTabla.getChildren().clear();
                objV= new Tabla_Venta();
                objV.close();
                vBoxTabla.getChildren().addAll(objV.tableViewVenta);
                string ="Sale";
                string2 = "date";
                txtBuscar.setPromptText("date");
                tabla = 20;

        }
    }

    public void Seleccion()
    {
        System.out.println("tabla: "+opc);
        switch (opc)
        {
            case "Carrito de Compras":
                string ="ShoppingCart";
                string2="idCart";
                txtBuscar.setPromptText("idCart");
                tabla=1;
                break;
            case "Categorias":
                string = "Category";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=2;
                break;
            case "Ciudad":
                string ="City";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=3;
                break;
            case "Cliente":
                string ="Customers";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=4;
                break;
            case "Contenido Paquete":
                string ="PackageContent";
                string2 = "detail";
                txtBuscar.setPromptText("detail");
                tabla=5;
                break;
            case "Direccion":
                string ="Address";
                string2 = "street";
                txtBuscar.setPromptText("street");
                tabla=6;
                break;
            case "Direccion Cliente":
                string="AddressCustomer";
                string2 = "idCustomer";
                txtBuscar.setPromptText("idCustomer");
                tabla=7;
                break;
            case "Estado":
                string = "State";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=8;
                break;
            case "Factura":
                string ="invoice";
                string2 = "dateInvoice";
                txtBuscar.setPromptText("dateInvoice");
                tabla=9;
                break;
            case "Metodo de pago":
                string ="PaymentMethod";
                string2 = "decription";
                txtBuscar.setPromptText("description");
                tabla=11;
                break;
            case "Pais":
                string ="Country";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=12;
                break;
            case "Paquete":
                string ="Package";
                string2 = "namePackage";
                txtBuscar.setPromptText("namePackage");
                tabla=13;
                break;
            case "Productos":
                string ="Product";
                string2 = "nameProduct";
                txtBuscar.setPromptText("nameProduct");
                tabla=14;
                break;
            case "Promociones":
                string ="Promotion";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=15;
                break;
            case "Rol":
                string ="role";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=16;
                break;
            case "Stock":
                string ="stock";
                string2 = "quantity";
                txtBuscar.setPromptText("quantity");
                tabla=17;
                break;
            case "Tienda":
                string ="Store";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=18;
                break;
            case "Tipo de venta":
                string ="TypeSale";
                string2 = "name";
                txtBuscar.setPromptText("name");
                tabla=19;
                break;
            case "Venta":
                string ="Sale";
                string2 = "date";
                txtBuscar.setPromptText("date");
                tabla=20;
        }
    }

    public void showPackage(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("package");
            Parent root= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddPackageFormat.fxml"));
            AddPackageController addPackageController1 = new AddPackageController();
            loader.setController(addPackageController1);
            root = loader.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showProduct(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("product");
            Parent root2= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/ProductAddFormat.fxml"));
            ProductAddController addPackageController2 = new ProductAddController();
            loader.setController(addPackageController2);
            root2 = loader.load();
            Scene scene=new Scene(root2);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showPromotion(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("promotion");
            Parent root3= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddPromotionFormat.fxml"));
            AddPromotionFormatController addPackageController3 = new AddPromotionFormatController();
            loader.setController(addPackageController3);
            root3 = loader.load();
            Scene scene=new Scene(root3);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showRol(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("rol");
            Parent root4= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddRoleFormat.fxml"));
            AddRoleFormat addPackageController4 = new AddRoleFormat();
            loader.setController(addPackageController4);
            root4 = loader.load();
            Scene scene=new Scene(root4);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showSale(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("sale");
            Parent root5= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddSaleFormat.fxml"));
            AddSaleFormatController addPackageController5 = new AddSaleFormatController();
            loader.setController(addPackageController5);
            root5 = loader.load();
            Scene scene=new Scene(root5);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showStore(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("store");
            Parent root6= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddStoreFormat.fxml"));
            AddStoreFormatController addPackageController6 = new AddStoreFormatController();
            loader.setController(addPackageController6);
            root6 = loader.load();
            Scene scene=new Scene(root6);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showStock(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("stock");
            Parent root7= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddToStockFormat.fxml"));
            AddToStockFormatController addPackageController7 = new AddToStockFormatController();
            loader.setController(addPackageController7);
            root7 = loader.load();
            Scene scene=new Scene(root7);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }

    public void showTypeSale(Stage _packageListStage){
        try {
            Stage = _packageListStage;
            Stage.setTitle("typesale");
            Parent root8= null;
            loader= new FXMLLoader(getClass().getResource("/sample/FXML/AddTypeSaleFormat.fxml"));
            AddTypeSaleFormatController addPackageController8 = new AddTypeSaleFormatController();
            loader.setController(addPackageController8);
            root8 = loader.load();
            Scene scene=new Scene(root8);
            scene.getStylesheets().add("/sample/css/estilo.css");
            Stage.setScene(scene);
            Stage.setMaximized(true);
            Stage.show();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
