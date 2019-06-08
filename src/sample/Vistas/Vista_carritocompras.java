package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_carritocompras;
import sample.Modelos.modelo_cliente;
import sample.TDAs.Customers;

public class Vista_carritocompras extends Stage
{
    private Label lblCustomer,lblProduct,lblPrecio,lblDescripcion,lblCantidad;
    private JFXButton btnEliminar;
    private ImageView imagen;
    private ObservableList<modelo_carritocompras> listacarrito;
    private ScrollPane principal;
    private VBox vbProduct,vboxPrincipal;
    private AnchorPane apPrincipal;
    private Customers user;
    private GridPane gpProduct;
    private Scene escena;
    private  int c=0,f=0;
    public void CrearGUI(){
        apPrincipal=new AnchorPane();
        gpProduct=new GridPane();
        principal=new ScrollPane();
        vboxPrincipal=new VBox();
        user=listacarrito.get(0).getIdCustomer();
        lblCustomer=new Label("Cliente: "+user.getName()+" "+user.getLastName());//
        for (modelo_carritocompras carrito:listacarrito) {
            lblProduct=new Label("Nombre: "+carrito.getIdProduct().getNameProduct());
            lblDescripcion=new Label("Descripcion: "+carrito.getIdProduct().getDescription());
            lblPrecio=new Label("Precio $"+carrito.getIdProduct().getPrice());
            lblCantidad=new Label(carrito.getCantidad()+"");
            try
            {
                imagen=new ImageView(carrito.getIdProduct().getImage());
            }
            catch (Exception e){
              imagen=new ImageView("sample/imagenes/null.png");
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
        vboxPrincipal.getChildren().addAll(lblCustomer,principal);
        escena=new Scene(vboxPrincipal);
        escena.getStylesheets().add("/sample/CSS/estilo.css");
        setScene(escena);
        setMaximized(true);
        setTitle("Contenido del carrito de compras del usuario: "+user.getName()+" "+user.getLastName());
        show();
    }

    public Vista_carritocompras(modelo_carritocompras carritocompras) {
        listacarrito=new modelo_carritocompras().Listar(carritocompras);
        CrearGUI();
    }

    private void EventoEliminar(modelo_carritocompras carrito) {
    }
}
