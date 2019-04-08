package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_pais;

public class Tabla_Pais extends Stage {
    private Scene scene;
    public TableView<modelo_pais> tableViewPais;
    public Tabla_Pais(){
        crearGUI();
        scene = new Scene(tableViewPais,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewPais = new TableView<>();
        TableColumn<modelo_pais,Integer> tbcidCountry= new TableColumn<>("idCountry");
        tbcidCountry.setCellValueFactory(new PropertyValueFactory<>("idCountry"));

        TableColumn<modelo_pais,Integer> tbcname = new TableColumn<>("name");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableViewPais.setItems(new modelo_pais().Listar());

        tableViewPais.getColumns().addAll(tbcidCountry,tbcname);
    }
}
