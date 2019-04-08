package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_estado;

public class Tabla_Estado extends Stage{
    private Scene scene;
    public TableView<modelo_estado> tableViewEstado;
    public Tabla_Estado(){
        crearGUI();
        scene = new Scene(tableViewEstado,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewEstado = new TableView<>();
        TableColumn<modelo_estado,Integer> tbcidState= new TableColumn<>("idState");
        tbcidState.setCellValueFactory(new PropertyValueFactory<>("idState"));

        TableColumn<modelo_estado,Integer> tbcname = new TableColumn<>("name");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_estado,Integer> tbcidCountry = new TableColumn<>("idCountry");
        tbcidCountry.setCellValueFactory(new PropertyValueFactory<>("idCountry"));

        tableViewEstado.setItems(new modelo_estado().Listar());

        tableViewEstado.getColumns().addAll(tbcidState,tbcname,tbcidCountry);
    }
}
