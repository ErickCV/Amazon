package sample.DAOs;

import sample.TDAs.Address;
import sample.TDAs.Product;
import sample.TDAs.Stock;

import sample.TDAs.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class StockDAO
{
    Connection conn;
    private static ObservableList<Stock> data = FXCollections.observableArrayList();
    public StockDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Stock stock) { data.add(stock); }
    public ObservableList<Stock> findAll() {
        ObservableList<Stock> stocks = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM stock";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Stock p = null;
            Stock stock=new Stock();


            while(rs.next()) {
                Store store=new Store();
                Product product=new Product();
                store.setIdStore(rs.getInt("idStore"));
                product.setIdProduct(rs.getInt("idProduct"));
                p = new Stock(
                        product,
                        store,
                        rs.getInt("quantity")
                );
                stocks.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return stocks;
    }

    public Boolean insert(Stock p) {
        try {
            String query = "insert into stock (idProduct,idStore,quantity)" +
                    "value(?,?,?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, p.getIdProduct().getIdProduct());
            st.setInt(2,p.getIdStore().getIdStore());
            st.setInt(3,p.getQuantity());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public Boolean update(Stock stock) {
        try {
            String query = "update Stock"
                    + " set idProduct=?,idStore=?,quantity=?"
                    + " where idProduct=? and idStore";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, stock.getIdProduct().getIdProduct());
            st.setInt(2, stock.getIdStore().getIdStore());
            st.setInt(3,stock.getQuantity());
            st.setInt(4,stock.getIdProduct().getIdProduct());
            st.setInt(5,stock.getIdStore().getIdStore());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
