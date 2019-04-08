package sample.DAOs;

import sample.TDAs.Address;
import sample.TDAs.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class StoreDAO {
    Connection conn;
    private static ObservableList<Store> data = FXCollections.observableArrayList();
    public StoreDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Store Store) { data.add(Store); }
    public ObservableList<Store> findAll() {
        ObservableList<Store> Stores = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Store";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Store p = null;
            Store Store=new Store();


            while(rs.next()) {
               Address address=new Address();


                p = new Store(
                        rs.getInt("idStore"),
                        address
                );
                Stores.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Stores;
    }
    public ObservableList<Store> find() {
        ObservableList<Store> stores = FXCollections.observableArrayList();
        try {
            String query = "SELECT idStore, street FROM store  inner join address  on  store.idAddress = address.idAddress";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Store p = null;
            while(rs.next()) {
                p = new Store(
                        rs.getInt("idStore"),
                        rs.getString("street")
                );
                stores.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return stores;
    }
    public Boolean insert(Store p) {
        try {
            String query = "insert into Store (idAddress)" +
                    "value(?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, p.getIdAddress().getIdAddress());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public Boolean update(Store Store) {
        try {
            String query = "update Store"
                    + " set idAddress=?"
                    + " where idStore=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, Store.getIdAddress().getIdAddress());
            st.setInt(2, Store.getIdStore());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }
}
