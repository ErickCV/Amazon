package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_carritocompras;

public class Tabla_CarritoCompras extends Stage{
    public TableView <modelo_carritocompras>tableViewCarrito;
    private Scene scene;
    public Tabla_CarritoCompras(){
        crearGUI();
        scene = new Scene(tableViewCarrito,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewCarrito = new TableView<>();
        TableColumn<modelo_carritocompras,Integer> tbcIdCustomer = new TableColumn<>("idCustomer");
        tbcIdCustomer.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));

        TableColumn<modelo_carritocompras,Integer> tbcIdCart = new TableColumn<>("idCart");
        tbcIdCart.setCellValueFactory(new PropertyValueFactory<>("idCart"));

        TableColumn<modelo_carritocompras,Integer> tbcsubTotal = new TableColumn<>("subTotal");
        tbcsubTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tableViewCarrito.setItems(new modelo_carritocompras().Listar());

        tableViewCarrito.getColumns().addAll(tbcIdCustomer,tbcIdCart,tbcsubTotal);
    }
}
