package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_metodopago;

public class Tabla_MetodoPago extends Stage{
    public Scene scene;
    public TableView<modelo_metodopago> tableViewMetodoPago;
    public Tabla_MetodoPago(){
        crearGUI();
        scene = new Scene(tableViewMetodoPago,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewMetodoPago = new TableView<>();
        TableColumn<modelo_metodopago,Integer> tbcidPayment= new TableColumn<>("idPayment");
        tbcidPayment.setCellValueFactory(new PropertyValueFactory<>("idPayment"));

        TableColumn<modelo_metodopago,Integer> tbctypePayment = new TableColumn<>("typePayment");
        tbctypePayment.setCellValueFactory(new PropertyValueFactory<>("typePayment"));

        TableColumn<modelo_metodopago,Integer> tbcdescription = new TableColumn<>("description");
        tbcdescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableViewMetodoPago.setItems(new modelo_metodopago().Listar());

        tableViewMetodoPago.getColumns().addAll(tbcidPayment,tbctypePayment,tbcdescription);
    }
}
