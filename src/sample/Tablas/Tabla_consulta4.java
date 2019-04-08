package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_consulta4;

public class Tabla_consulta4 extends Stage {
    private Scene scene;
    public TableView<modelo_consulta4> tableViewConsulta4;
    public Tabla_consulta4(){
        crearGUI();
        scene = new Scene(tableViewConsulta4,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewConsulta4 = new TableView<>();
        TableColumn<modelo_consulta4,Integer> tbcUserName= new TableColumn<>("userName");
        tbcUserName.setCellValueFactory(new PropertyValueFactory<>("nameUser"));

        TableColumn<modelo_consulta4,Integer> tbcCustomerName = new TableColumn<>("customerName");
        tbcCustomerName.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));

        TableColumn<modelo_consulta4,Integer> tbcproductName = new TableColumn<>("productName");
        tbcproductName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));

        TableColumn<modelo_consulta4,Integer> tbcdateSale = new TableColumn<>("dateSale");
        tbcdateSale.setCellValueFactory(new PropertyValueFactory<>("saleDate"));

        TableColumn<modelo_consulta4,Integer> tbctotalSale = new TableColumn<>("totalSale");
        tbctotalSale.setCellValueFactory(new PropertyValueFactory<>("saleTotal"));


        tableViewConsulta4.setItems(new modelo_consulta4().Listar("user"));

        tableViewConsulta4.getColumns().addAll(tbcUserName,tbcCustomerName,tbcproductName,tbcdateSale,tbctotalSale);
    }
}
