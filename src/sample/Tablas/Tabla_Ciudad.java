package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_ciudad;

public class Tabla_Ciudad extends Stage{
    private Scene scene;
    public TableView<modelo_ciudad> tableViewCiudad;
    public Tabla_Ciudad(){
        crearGUI();
        scene = new Scene(tableViewCiudad,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewCiudad = new TableView<>();
        TableColumn<modelo_ciudad,Integer> tbcidCity= new TableColumn<>("idCity");
        tbcidCity.setCellValueFactory(new PropertyValueFactory<>("idCity"));

        TableColumn<modelo_ciudad,Integer> tbcname = new TableColumn<>("name");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_ciudad,Integer> tbcidCountry = new TableColumn<>("idCountry");
        tbcidCountry.setCellValueFactory(new PropertyValueFactory<>("idCountry"));

        TableColumn<modelo_ciudad,Integer> tbcidState = new TableColumn<>("idState");
        tbcidState.setCellValueFactory(new PropertyValueFactory<>("idState"));

        tableViewCiudad.setItems(new modelo_ciudad().Listar());

        tableViewCiudad.getColumns().addAll(tbcidCity,tbcname,tbcidCountry,tbcidState);
    }
}
