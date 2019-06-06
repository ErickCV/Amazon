package sample.Vistas;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Modelos.modelo_carritocompras;
import sample.Modelos.modelo_cliente;
import sample.Modelos.modelo_productos;


public class Vista_carritocompras extends Stage
{
    private Label lblCustomer,lblProduct,lblPrecio,lblDescripcion;
    private JFXButton btnEliminar;
    private ImageView imagen;
    private ObservableList<modelo_carritocompras> listacarrito;
    private ScrollPane principal;
    private VBox vbProduct,vboxPrincipal;
    private modelo_cliente user;
    private VBox[][] arrvbox;
    private Scene escena;
    private  int c=0,f=0;
    public void CrearGUI(){
        arrvbox=new VBox[listacarrito.size()-1][3];
        principal=new ScrollPane();
        vboxPrincipal=new VBox();
        lblCustomer=new Label(user.getName()+" "+user.getLastName());
        for (modelo_carritocompras carrito:listacarrito) {
            lblProduct=new Label(carrito.getIdProduct().getNameProduct());
            lblDescripcion=new Label(carrito.getIdProduct().getDescription());
            lblPrecio=new Label(carrito.getIdProduct().getPrice()+"");
            imagen=new ImageView(carrito.getIdProduct().getImage());
            btnEliminar=new JFXButton("Eliminar");
            btnEliminar.setOnAction(Event->EventoEliminar(carrito));
            vbProduct=new VBox();
            vbProduct.getChildren().addAll(imagen,lblProduct,lblDescripcion,lblPrecio,btnEliminar);
            if (c>=3)
                c=0;
            arrvbox[f][c].getChildren().add(vbProduct);
            principal.setContent(arrvbox[f][c]);
            f++;
            c++;
        }
        vboxPrincipal.getChildren().addAll(lblCustomer,principal);
        escena=new Scene(vboxPrincipal);
        setScene(escena);
        show();
    }

    public Vista_carritocompras(modelo_carritocompras carritocompras) {
        listacarrito=new modelo_carritocompras().Listar(carritocompras);
        CrearGUI();
    }

    private void EventoEliminar(modelo_carritocompras carrito) {
    }
}
