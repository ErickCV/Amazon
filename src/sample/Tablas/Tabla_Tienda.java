package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_tienda;

public class Tabla_Tienda extends Stage {
    private Scene scene;
    public TableView<modelo_tienda> tableViewTienda;
    public Tabla_Tienda(){
        crearGUI();
        scene = new Scene(tableViewTienda,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewTienda = new TableView<>();
        TableColumn<modelo_tienda,Integer> tbcidStore= new TableColumn<>("idStore");
        tbcidStore.setCellValueFactory(new PropertyValueFactory<>("idStore"));

        TableColumn<modelo_tienda,Integer> tbcidAddress = new TableColumn<>("idAddress");
        tbcidAddress.setCellValueFactory(new PropertyValueFactory<>("idAddress"));

        tableViewTienda.setItems(new modelo_tienda().Listar());

        tableViewTienda.getColumns().addAll(tbcidStore,tbcidAddress);
    }
}
