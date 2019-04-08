package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_stock;

public class Tabla_Stock extends Stage{
    private Scene scene;
    public TableView<modelo_stock> tableViewStock;
    public Tabla_Stock(){
        crearGUI();
        scene = new Scene(tableViewStock,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewStock = new TableView<>();
        TableColumn<modelo_stock,Integer> tbcidProduct= new TableColumn<>("idProduct");
        tbcidProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));

        TableColumn<modelo_stock,Integer> tbcidStore = new TableColumn<>("idStore");
        tbcidStore.setCellValueFactory(new PropertyValueFactory<>("idStore"));

        TableColumn<modelo_stock,Integer> tbcquantity = new TableColumn<>("quantity");
        tbcquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableViewStock.setItems(new modelo_stock().Listar());

        tableViewStock.getColumns().addAll(tbcidProduct,tbcidStore,tbcquantity);
    }
}
