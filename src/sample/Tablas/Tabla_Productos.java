package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_productos;

public class Tabla_Productos extends Stage{
    private Scene scene;
    public TableView<modelo_productos> tableViewProductos;
    public Tabla_Productos(){
        crearGUI();
        scene = new Scene(tableViewProductos,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewProductos = new TableView<>();
        TableColumn<modelo_productos,Integer> tbcidProduct= new TableColumn<>("idProduct");
        tbcidProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));

        TableColumn<modelo_productos,Integer> tbcnameProduct = new TableColumn<>("nameProduct");
        tbcnameProduct.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));

        TableColumn<modelo_productos,Integer> tbcidCategory = new TableColumn<>("idCategory");
        tbcidCategory.setCellValueFactory(new PropertyValueFactory<>("idCategory"));

        TableColumn<modelo_productos,Integer> tbcdescription = new TableColumn<>("description");
        tbcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<modelo_productos,Integer> tbcprice = new TableColumn<>("price");
        tbcprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<modelo_productos,Integer> tbcimage = new TableColumn<>("image");
        tbcimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        tableViewProductos.setItems(new modelo_productos().Listar());

        tableViewProductos.getColumns().addAll(tbcidProduct,tbcnameProduct,tbcidCategory,tbcdescription,tbcprice,tbcimage);
    }
}
