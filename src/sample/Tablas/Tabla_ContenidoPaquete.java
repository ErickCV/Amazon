package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_contenidopaquete;

public class Tabla_ContenidoPaquete extends Stage {
    private Scene scene;
    public TableView<modelo_contenidopaquete> tableViewContenidoPaquete;
    public Tabla_ContenidoPaquete(){
        crearGUI();
        scene = new Scene(tableViewContenidoPaquete,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewContenidoPaquete = new TableView<>();
        TableColumn<modelo_contenidopaquete,Integer> tbcidPackage= new TableColumn<>("idPackage");
        tbcidPackage.setCellValueFactory(new PropertyValueFactory<>("idPackage"));

        TableColumn<modelo_contenidopaquete,Integer> tbcdetails = new TableColumn<>("detail");
        tbcdetails.setCellValueFactory(new PropertyValueFactory<>("detail"));

        TableColumn<modelo_contenidopaquete,Integer> tbcidProduct = new TableColumn<>("idProduct");
        tbcidProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));

        TableColumn<modelo_contenidopaquete,Integer> tbcidQuantity = new TableColumn<>("quantity");
        tbcidQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableViewContenidoPaquete.setItems(new modelo_contenidopaquete().Listar());

        tableViewContenidoPaquete.getColumns().addAll(tbcidPackage,tbcdetails,tbcidProduct,tbcidQuantity);
    }
}
