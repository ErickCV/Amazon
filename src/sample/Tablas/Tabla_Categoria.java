package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_categoria;

public class Tabla_Categoria extends Stage{
    private Scene scene;
    public TableView<modelo_categoria> tableViewCategoria;
    public Tabla_Categoria(){
        crearGUI();
        scene = new Scene(tableViewCategoria,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewCategoria = new TableView<>();
        TableColumn<modelo_categoria,Integer> tbcidCategory = new TableColumn<>("idCategory");
        tbcidCategory.setCellValueFactory(new PropertyValueFactory<>("idCategory"));

        TableColumn<modelo_categoria,Integer> tbcname = new TableColumn<>("name");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_categoria,Integer> tbcdescription = new TableColumn<>("description");
        tbcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<modelo_categoria,Integer> tbcimage = new TableColumn<>("image");
        tbcimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        tableViewCategoria.setItems(new modelo_categoria().Listar());

        tableViewCategoria.getColumns().addAll(tbcidCategory,tbcname,tbcdescription,tbcimage);
    }
}
