package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;;
import javafx.stage.Stage;
import sample.Modelos.modelo_direccioncliente;

public class Tabla_DireccionCliente extends Stage {
    private Scene scene;
    public TableView<modelo_direccioncliente> tableViewDireccionCliente;
    public Tabla_DireccionCliente(){
        crearGUI();
        scene = new Scene(tableViewDireccionCliente,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewDireccionCliente = new TableView<>();
        TableColumn<modelo_direccioncliente,Integer> tbcidCostumer= new TableColumn<>("idCostumer");
        tbcidCostumer.setCellValueFactory(new PropertyValueFactory<>("idCostumer"));

        TableColumn<modelo_direccioncliente,Integer> tbcAddress = new TableColumn<>("idAddress");
        tbcAddress.setCellValueFactory(new PropertyValueFactory<>("idAddress"));

        tableViewDireccionCliente.setItems(new modelo_direccioncliente().Listar());

        tableViewDireccionCliente.getColumns().addAll(tbcidCostumer,tbcAddress);
    }
}
