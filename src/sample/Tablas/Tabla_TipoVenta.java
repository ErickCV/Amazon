package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_tipoventa;

public class Tabla_TipoVenta extends Stage {
    private Scene scene;
    public TableView<modelo_tipoventa> tableViewTipoVenta;
    public Tabla_TipoVenta(){
        crearGUI();
        scene = new Scene(tableViewTipoVenta,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewTipoVenta = new TableView<>();
        TableColumn<modelo_tipoventa,Integer> tbcidTypeSale= new TableColumn<>("idTypeSale");
        tbcidTypeSale.setCellValueFactory(new PropertyValueFactory<>("idTypeSale"));

        TableColumn<modelo_tipoventa,Integer> tbcname = new TableColumn<>("name");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_tipoventa,Integer> tbcdescription = new TableColumn<>("description");
        tbcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableViewTipoVenta.setItems(new modelo_tipoventa().Listar());

        tableViewTipoVenta.getColumns().addAll(tbcidTypeSale,tbcname,tbcdescription);
    }
}
