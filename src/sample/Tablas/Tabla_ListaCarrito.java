package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oracle.jrockit.jfr.ProducerDescriptor;
import sample.Modelos.modelo_listacarritocompras;

public class Tabla_ListaCarrito extends Stage{
    private Scene scene;
    public TableView<modelo_listacarritocompras> tableViewListaCarrito;
    public Tabla_ListaCarrito(){
        crearGUI();
        scene = new Scene(tableViewListaCarrito,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewListaCarrito = new TableView<>();
        TableColumn<modelo_listacarritocompras,Integer> tbcidCart= new TableColumn<>("idCart");
        tbcidCart.setCellValueFactory(new PropertyValueFactory<>("idCart"));

        TableColumn<modelo_listacarritocompras,Integer> tbcidCustomer = new TableColumn<>("idCustomer");
        tbcidCustomer.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));

        TableColumn<modelo_listacarritocompras,Integer> tbcdetail = new TableColumn<>("detail");
        tbcdetail.setCellValueFactory(new PropertyValueFactory<>("detail"));

        TableColumn<modelo_listacarritocompras,Integer> tbcidProduct = new TableColumn<>("idProduct");
        tbcidProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));

        TableColumn<modelo_listacarritocompras,Integer> tbcquantityProduct = new TableColumn<>("idquantityProduct");
        tbcquantityProduct.setCellValueFactory(new PropertyValueFactory<>("idquantityProduct"));

        TableColumn<modelo_listacarritocompras,Integer> tbcidPackage = new TableColumn<>("idPackage");
        tbcidPackage.setCellValueFactory(new PropertyValueFactory<>("idPackage"));

        TableColumn<modelo_listacarritocompras,Integer> tbcquantityPackage = new TableColumn<>("quantityPackage");
        tbcquantityPackage.setCellValueFactory(new PropertyValueFactory<>("quantityPackage"));

        tableViewListaCarrito.setItems(new modelo_listacarritocompras().Listar());

        tableViewListaCarrito.getColumns().addAll(tbcidCart,tbcidCustomer,tbcdetail,tbcidProduct,tbcquantityProduct,tbcidPackage,tbcquantityPackage);
    }
}
