package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Modelos.modelo_consulta;
import sample.Modelos.modelo_consulta2;

public class Tabla_consulta2 extends Stage{
    private Scene scene;
    public TableView<modelo_consulta2> tableViewConsulta2;
    public Tabla_consulta2(){
        crearGUI();
        scene = new Scene(tableViewConsulta2,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewConsulta2 = new TableView<>();

        TableColumn<modelo_consulta2,Integer> tbcCustomerName = new TableColumn<>("customerName");
        tbcCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<modelo_consulta2,Integer> tbcproductName = new TableColumn<>("productName");
        tbcproductName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));

        TableColumn<modelo_consulta2,Integer> tbcdateSale = new TableColumn<>("dateSale");
        tbcdateSale.setCellValueFactory(new PropertyValueFactory<>("dateSale"));

        TableColumn<modelo_consulta2,Integer> tbctotalSale = new TableColumn<>("totalSale");
        tbctotalSale.setCellValueFactory(new PropertyValueFactory<>("total"));


        tableViewConsulta2.setItems(new modelo_consulta2().Listar());

        tableViewConsulta2.getColumns().addAll(tbcCustomerName,tbcproductName,tbcdateSale,tbctotalSale);
    }
}
