package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_paquete;

public class Tabla_Paquete extends Stage {
    private Scene scene;
    public TableView<modelo_paquete> tableViewPaquete;
    public Tabla_Paquete(){
        crearGUI();
        scene = new Scene(tableViewPaquete,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewPaquete = new TableView<>();
        TableColumn<modelo_paquete,Integer> tbcidPackage= new TableColumn<>("idPackage");
        tbcidPackage.setCellValueFactory(new PropertyValueFactory<>("idPackage"));

        TableColumn<modelo_paquete,Integer> tbcidnamePackage = new TableColumn<>("namePackage");
        tbcidnamePackage.setCellValueFactory(new PropertyValueFactory<>("namePackage"));

        TableColumn<modelo_paquete,Integer> tbcdescription = new TableColumn<>("description");
        tbcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<modelo_paquete,Integer> tbcidprice = new TableColumn<>("price");
        tbcidprice.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<modelo_paquete,Integer> tbcstock = new TableColumn<>("stock");
        tbcstock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TableColumn<modelo_paquete,Integer> tbcimage = new TableColumn<>("image");
        tbcimage.setCellValueFactory(new PropertyValueFactory<>("image"));

        tableViewPaquete.setItems(new modelo_paquete().Listar());

        tableViewPaquete.getColumns().addAll(tbcidPackage,tbcidnamePackage,tbcdescription,tbcidprice,tbcstock,tbcimage);
    }
}
