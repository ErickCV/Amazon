package sample.Tablas;

import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Modelos.modelo_venta;

public class Tabla_Venta extends Stage{
    private Scene scene;
    public TableView<modelo_venta> tableViewVenta;
    public Tabla_Venta(){
        crearGUI();
        scene = new Scene(tableViewVenta,500,300);
        this.setScene(scene);
        this.show();
    }
    public void crearGUI(){
        tableViewVenta = new TableView<>();
        TableColumn<modelo_venta,Integer> tbcidSale= new TableColumn<>("idSale");
        tbcidSale.setCellValueFactory(new PropertyValueFactory<>("idSale"));

        TableColumn<modelo_venta,Integer> tbcidCustomer = new TableColumn<>("idCustomer");
        tbcidCustomer.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));

        TableColumn<modelo_venta,Integer> tbcidCart = new TableColumn<>("idCart");
        tbcidCart.setCellValueFactory(new PropertyValueFactory<>("idCart"));

        TableColumn<modelo_venta,Integer> tbcidUser= new TableColumn<>("idUser");
        tbcidUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));

        TableColumn<modelo_venta,Integer> tbctotal = new TableColumn<>("total");
        tbctotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<modelo_venta,Integer> tbcidPayment = new TableColumn<>("idPayment");
        tbcidPayment.setCellValueFactory(new PropertyValueFactory<>("idPayment"));

        TableColumn<modelo_venta,Integer> tbcidTypeSale = new TableColumn<>("idTypeSale");
        tbcidTypeSale.setCellValueFactory(new PropertyValueFactory<>("idTypeSale"));

        TableColumn<modelo_venta,Integer> tbcidStore = new TableColumn<>("idStore");
        tbcidStore.setCellValueFactory(new PropertyValueFactory<>("idStore"));

        TableColumn<modelo_venta,Integer> tbcidPromo = new TableColumn<>("idPromo");
        tbcidPromo.setCellValueFactory(new PropertyValueFactory<>("idPromo"));

        TableColumn<modelo_venta,Integer> tbcdate = new TableColumn<>("date");
        tbcdate.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableViewVenta.setItems(new modelo_venta().Listar());

        tableViewVenta.getColumns().addAll(tbcidSale,tbcidCustomer,tbcidCart,tbcidUser,tbctotal,tbcidPayment,tbcidTypeSale,tbcidStore,tbcidPromo,tbcdate);
    }
}
