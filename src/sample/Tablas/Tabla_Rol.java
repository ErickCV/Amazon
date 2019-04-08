package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_rol;

public class Tabla_Rol extends Stage {
    private Scene scene;
    public TableView<modelo_rol> tableViewRol;
    public Tabla_Rol(){
        crearGUI();
        scene = new Scene(tableViewRol,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewRol = new TableView<>();
        TableColumn<modelo_rol,Integer> tbcidRol= new TableColumn<>("idRol");
        tbcidRol.setCellValueFactory(new PropertyValueFactory<>("idRol"));

        TableColumn<modelo_rol,Integer> tbcidname = new TableColumn<>("name");
        tbcidname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_rol,Integer> tbcdescription = new TableColumn<>("description");
        tbcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableViewRol.setItems(new modelo_rol().Listar());

        tableViewRol.getColumns().addAll(tbcidRol,tbcidname,tbcdescription);
    }
}
