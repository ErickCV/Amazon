package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_promocion;

public class Tabla_Promocion extends Stage{
    private Scene scene;
    public TableView<modelo_promocion> tableViewPromocion;
    public Tabla_Promocion(){
        crearGUI();
        scene = new Scene(tableViewPromocion,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewPromocion = new TableView<>();
        TableColumn<modelo_promocion,Integer> tbcidPromo= new TableColumn<>("idPromo");
        tbcidPromo.setCellValueFactory(new PropertyValueFactory<>("idPromo"));

        TableColumn<modelo_promocion,Integer> tbcidname = new TableColumn<>("name");
        tbcidname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_promocion,Integer> tbcdatePromo = new TableColumn<>("datePromo");
        tbcdatePromo.setCellValueFactory(new PropertyValueFactory<>("datePromo"));

        tableViewPromocion.setItems(new modelo_promocion().Listar());

        tableViewPromocion.getColumns().addAll(tbcidPromo,tbcidname,tbcdatePromo);
    }
}
