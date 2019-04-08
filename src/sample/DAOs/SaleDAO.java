package sample.DAOs;

import sample.TDAs.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.PreparedStatement;

public class SaleDAO {
    Connection conn;
    private static ObservableList<Sale> data = FXCollections.observableArrayList();
    public SaleDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Sale sale) { data.add(sale); }

    public Boolean insert(String idCustomer_ ,int idCart_ ,int idPayment_ ,int idTypeSal_ ,int idStore_ ,int idPromo_ ) {
        try {
            String query = "Call addSale("+idCustomer_+","+idCart_+","+idPayment_+","+idTypeSal_+","+idStore_+","+idPromo_+")";
            PreparedStatement st =  conn.prepareStatement(query);

            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
