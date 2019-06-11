package sample.Vistas;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_carritocompras;
import sample.Modelos.modelo_cliente;
import sample.Modelos.modelo_metodopago;
import sample.Modelos.modelo_venta;
import sample.TDAs.Customers;

import java.sql.SQLException;

public class Vista_metodopago extends Stage {
    private RadioButton rbtnTypeSale;
    private JFXButton btnAceptar,btnCancelar;
    private Label lblTitle;
    private FontAwesomeIconView icon ;
    private VBox vBoxprincipal;
    private HBox hboxbotones;
    private modelo_metodopago metodopago;
    private boolean seleccion=true;
    private ToggleGroup grupo =new ToggleGroup();
    private ObservableList<modelo_metodopago> listpayment;
    private Scene escena;
    private Alert alerta;
    private modelo_carritocompras carrito;
    private Customers cliente=new Customers();
    private int Producto;


    public Vista_metodopago(int idCarrito ,int idCustomer, int producto) {
        carrito=new modelo_carritocompras();
        this.Producto = producto;
        cliente.setIdCustomer(""+idCustomer);
        carrito.setIdCart(idCarrito);
        try {
            carrito.setIdCustomer(new modelo_cliente().BuscarCustomer(idCustomer));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CrearGUI();
    }
    public  void CrearGUI(){
        vBoxprincipal=new VBox();
        hboxbotones=new HBox();
        btnAceptar=new JFXButton("Aceptar");
        btnCancelar=new JFXButton("Cancelar");
        listpayment=new modelo_metodopago().Listar();
        lblTitle=new Label("Seleccione el metodo de pago");
        icon=new FontAwesomeIconView(FontAwesomeIcon.CREDIT_CARD);
        lblTitle.setGraphic(icon);
        icon.setSize("50");
        vBoxprincipal.getChildren().add(lblTitle);
        for (modelo_metodopago metodopago:listpayment) {
            rbtnTypeSale=new RadioButton(metodopago.getTypePayment());
            rbtnTypeSale.setToggleGroup(grupo);
            vBoxprincipal.getChildren().add(rbtnTypeSale);
            rbtnTypeSale.setOnAction(Event->EventoSelect(metodopago));
            }
            btnAceptar.setOnAction(Event->EventoAceptar());
            btnCancelar.setOnAction(Event->EventoCancelar());
            hboxbotones.getChildren().addAll(btnAceptar,btnCancelar);
            hboxbotones.setAlignment(Pos.CENTER);
            vBoxprincipal.getChildren().add(hboxbotones);
            escena=new Scene(vBoxprincipal);
            setScene(escena);
            escena.getStylesheets().add("/sample/CSS/estilo.css");
            setTitle("Metodo de Pago");
            show();

    }

    private void EventoCancelar() {
        this.close();
        new Vista_carritocompras(carrito);
    }

    private void EventoAceptar() {

        if (seleccion)
        {
            alerta=new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Por Favor seleccione una forma de pago para continuar");
            alerta.setTitle("Error");
            alerta.setHeaderText("No ha seleccionado ninguna forma de pago");
            alerta.showAndWait();
        }
        else
        {
            //instrucciones para agregar al tipo de pago
            modelo_venta venta = new modelo_venta();
            venta .setIdCart(carrito.getIdCart());
            venta.setIdCustomer(Integer.parseInt(carrito.getIdCustomer().getIdCustomer()));
            venta.setIdPayment(metodopago.getIdPayment());
            venta.setProducto(this.Producto);
            venta.Insert();
            alerta=new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Compra realizada");
            alerta.setTitle("Compra realizada");
            alerta.setHeaderText("Se ha hecho su compra exitosamente");
            alerta.showAndWait();
            this.close();
            new Vista_carritocompras(carrito);
        }
    }

    private void EventoSelect(modelo_metodopago metodopago) {
        this.metodopago=metodopago;
        seleccion=false;
    }
}
