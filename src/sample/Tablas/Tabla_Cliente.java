package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_cliente;

public class Tabla_Cliente extends Stage{
    private Scene scene;
    public TableView<modelo_cliente> tableViewCliente;
    public Tabla_Cliente(){
        crearGUI();
        scene = new Scene(tableViewCliente,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewCliente = new TableView<>();
        TableColumn<modelo_cliente,Integer> tbcidCustomer= new TableColumn<>("idCustomer");
        tbcidCustomer.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));

        TableColumn<modelo_cliente,Integer> tbcname = new TableColumn<>("name");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<modelo_cliente,Integer> tbclastName = new TableColumn<>("lastName");
        tbclastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<modelo_cliente,Integer> tbcgender = new TableColumn<>("gender");
        tbcgender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tableViewCliente.setItems(new modelo_cliente()  .Listar());

        tableViewCliente.getColumns().addAll(tbcidCustomer,tbcname,tbclastName,tbcgender);
    }
}
