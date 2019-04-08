package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_consulta3;

public class Tabla_consulta3 extends Stage {

    private Scene scene;
    public TableView<modelo_consulta3> tableViewConsulta3;
    public Tabla_consulta3(){
        crearGUI();
        scene = new Scene(tableViewConsulta3,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewConsulta3 = new TableView<>();
        TableColumn<modelo_consulta3,Integer> tbcidProduct= new TableColumn<>("idProduct");
        tbcidProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));

        TableColumn<modelo_consulta3,Integer> tbcnameProduct = new TableColumn<>("nameProduct");
        tbcnameProduct.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));

        tableViewConsulta3.setItems(new modelo_consulta3().Listar());

        tableViewConsulta3.getColumns().addAll(tbcidProduct,tbcnameProduct);
    }

}
