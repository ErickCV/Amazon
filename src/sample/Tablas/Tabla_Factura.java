package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_factura;

public class Tabla_Factura extends Stage{
    private Scene scene;
    public TableView<modelo_factura> tableViewFactura;
    public Tabla_Factura(){
        crearGUI();
        scene = new Scene(tableViewFactura,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewFactura = new TableView<>();
        TableColumn<modelo_factura,Integer> tbcidInvoice= new TableColumn<>("idInvoice");
        tbcidInvoice.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));

        TableColumn<modelo_factura,Integer> tbcname = new TableColumn<>("sale");
        tbcname.setCellValueFactory(new PropertyValueFactory<>("sale"));

        TableColumn<modelo_factura,Integer> tbcdateInvoice = new TableColumn<>("dateInvoice");
        tbcdateInvoice.setCellValueFactory(new PropertyValueFactory<>("dateInvoice"));

        TableColumn<modelo_factura,Integer> tbcidUser = new TableColumn<>("idUser");
        tbcidUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        tableViewFactura.setItems(new modelo_factura().Listar());

        tableViewFactura.getColumns().addAll(tbcidInvoice,tbcname,tbcdateInvoice,tbcidUser);
    }
}
