package sample.Vistas;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_carritocompras;
import sample.TDAs.Customers;

import java.sql.SQLException;


public class Vista_carritocompras extends Stage
{
    private Label lblCustomer,lblProduct,lblPrecio,lblDescripcion,lblCantidad;
    private JFXButton btnEliminar;
    private ImageView imagen;
    private ObservableList<modelo_carritocompras> listacarrito;
    private ScrollPane principal;
    private VBox vbProduct,vboxPrincipal;
    private JFXButton btnPayment;
    private Customers user;
    private GridPane gpProduct;
    private Scene escena;
    public FontAwesomeIconView icono;
    private  int c=0,f=0;

    public void CrearGUI(){
        icono=new FontAwesomeIconView(FontAwesomeIcon.MONEY);
        icono.setSize("50");
        btnPayment=new JFXButton();
        btnPayment.setGraphic(icono);
        btnPayment.setOnAction(Event->EventoPagar());
        gpProduct=new GridPane();
        principal=new ScrollPane();
        vboxPrincipal=new VBox();
        lblCustomer=new Label("Cliente: "+user.getName()+" "+user.getLastName());
        icono=new FontAwesomeIconView(FontAwesomeIcon.USER_CIRCLE_ALT);
        icono.setSize("50");
        lblCustomer.setGraphic(icono);
        for (modelo_carritocompras carrito: listacarrito) {
            lblProduct=new Label("Nombre: "+carrito.getIdProduct().getNameProduct());
            lblDescripcion=new Label("Descripcion: "+carrito.getIdProduct().getDescription());
            lblPrecio=new Label("Precio $"+carrito.getIdProduct().getPrice());
            lblCantidad=new Label(carrito.getCantidad()+"");
            try
            {
                imagen=new ImageView(carrito.getIdProduct().getImage().toString());
                System.out.println(carrito.getIdProduct().getImage());
            }
            catch (Exception e){
                e.getMessage();

              imagen=new ImageView("/sample/imagenes/null.png");
            }
            imagen.setFitWidth(100);
            imagen.setFitHeight(100);
            btnEliminar=new JFXButton("Eliminar");
            btnEliminar.setOnAction(Event->EventoEliminar(carrito));
            vbProduct=new VBox();
            vbProduct.setAlignment(Pos.CENTER);
            vbProduct.getChildren().addAll(imagen,lblProduct,lblDescripcion,lblPrecio,lblCantidad,btnEliminar);
            if (c>=10){
                f++;
                c=0;
            }
            gpProduct.add(vbProduct,c,f);
            principal.setContent(gpProduct);
            c++;
        }
        if (listacarrito.isEmpty()){
            btnPayment.setDisable(true);
        }
        vboxPrincipal.setAlignment(Pos.TOP_CENTER);
        vboxPrincipal.getChildren().addAll(lblCustomer,principal,btnPayment);
       // vboxPrincipal.getChildren().addAll(principal,btnPayment);
        escena=new Scene(vboxPrincipal);
        escena.getStylesheets().add("/sample/CSS/estilo.css");
        setScene(escena);
        setMaximized(true);
        setTitle("Contenido del carrito de compras del usuario: "+user.getName()+" "+user.getLastName());
        show();
    }

    private void EventoPagar() {
        this.close();
        Vista_metodopago pago = new Vista_metodopago(listacarrito.get(0).idCart,Integer.parseInt(listacarrito.get(0).idCustomer.getIdCustomer()));
    }

    public Vista_carritocompras(modelo_carritocompras carritocompras) {
        user=new Customers();
        listacarrito=new modelo_carritocompras().Listar(carritocompras);
       // System.out.println("usuario"+carritocompras.getIdCustomer().getName());
        user=carritocompras.getIdCustomer();
        CrearGUI();
    }

    private void EventoEliminar(modelo_carritocompras carrito) {
        try {
            carrito.eliminarItem();
            this.close();
            new Vista_carritocompras(carrito);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
