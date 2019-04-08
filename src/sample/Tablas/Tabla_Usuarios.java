package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_consulta;
import sample.Modelos.modelo_usuarios;

public class Tabla_Usuarios extends Stage{
    private Scene scene;
    public TableView<modelo_consulta> tableViewUsuarios;
    public Tabla_Usuarios(){
        crearGUI();
        scene = new Scene(tableViewUsuarios,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewUsuarios = new TableView<>();
        TableColumn<modelo_consulta,Integer> tbcUserName= new TableColumn<>("userName");
        tbcUserName.setCellValueFactory(new PropertyValueFactory<>("nameUser"));

        TableColumn<modelo_consulta,Integer> tbcRolname = new TableColumn<>("Rolname");
        tbcRolname.setCellValueFactory(new PropertyValueFactory<>("idStore"));

        TableColumn<modelo_consulta,Integer> tbcidSale = new TableColumn<>("idSale");
        tbcidSale.setCellValueFactory(new PropertyValueFactory<>("idSale"));

        TableColumn<modelo_consulta,Integer> tbctotalSale = new TableColumn<>("total");
        tbctotalSale.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<modelo_consulta,Integer> tbcdateSale = new TableColumn<>("date");
        tbcdateSale.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<modelo_consulta,Integer> tbcidInvoice = new TableColumn<>("idInvoice");
        tbcidInvoice.setCellValueFactory(new PropertyValueFactory<>("idInvoice"));

        TableColumn<modelo_consulta,Integer> tbcdateInvoice = new TableColumn<>("dateInvoice");
        tbcdateInvoice.setCellValueFactory(new PropertyValueFactory<>("dateInvoice"));

        tableViewUsuarios.setItems(new modelo_consulta().Listar());

        tableViewUsuarios.getColumns().addAll(tbcUserName,tbcRolname,tbcidSale,tbctotalSale,tbcdateSale,tbcidInvoice,tbcdateInvoice);
    }
}
