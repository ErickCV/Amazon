package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_direccion;

public class Tabla_Direccion extends Stage{
    private Scene scene;
    public TableView<modelo_direccion> tableViewDireccion;
    public Tabla_Direccion(){
        crearGUI();
        scene = new Scene(tableViewDireccion,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewDireccion = new TableView<>();
        TableColumn<modelo_direccion,Integer> tbcidAddress= new TableColumn<>("idAddress");
        tbcidAddress.setCellValueFactory(new PropertyValueFactory<>("idAddress"));

        TableColumn<modelo_direccion,Integer> tbcstreet = new TableColumn<>("street");
        tbcstreet.setCellValueFactory(new PropertyValueFactory<>("street"));

        TableColumn<modelo_direccion,Integer> tbcemail = new TableColumn<>("email");
        tbcemail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<modelo_direccion,Integer> tbcphone = new TableColumn<>("phone");
        tbcphone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<modelo_direccion,Integer> tbcnameCommerce = new TableColumn<>("nameCommerce");
        tbcnameCommerce.setCellValueFactory(new PropertyValueFactory<>("nameCommerce"));

        TableColumn<modelo_direccion,Integer> tbcidCity = new TableColumn<>("idCity");
        tbcidCity.setCellValueFactory(new PropertyValueFactory<>("idCity"));

        TableColumn<modelo_direccion,Integer> tbcidState = new TableColumn<>("idState");
        tbcidState.setCellValueFactory(new PropertyValueFactory<>("idState"));

        TableColumn<modelo_direccion,Integer> tbcidCountry = new TableColumn<>("idCountry");
        tbcidCountry.setCellValueFactory(new PropertyValueFactory<>("idCountry"));

        TableColumn<modelo_direccion,Integer> tbccp = new TableColumn<>("cp");
        tbccp.setCellValueFactory(new PropertyValueFactory<>("cp"));

        tableViewDireccion.setItems(new modelo_direccion().Listar());

        tableViewDireccion.getColumns().addAll(tbcidAddress,tbcstreet,tbcemail,tbcphone,tbcnameCommerce,tbcidCity,tbcidState,tbcidCountry,tbccp);
    }
}
