package sample.DAOs;

import sample.TDAs.Role;
import sample.TDAs.TypeSale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class TypeSaleDAO  {
    Connection conn;
    private static ObservableList<TypeSale> data = FXCollections.observableArrayList();
    public TypeSaleDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(TypeSale TypeSale) { data.add(TypeSale); }
    public ObservableList<TypeSale> findAll() {
        ObservableList<TypeSale> TypeSales = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM TypeSale";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TypeSale p = null;
            TypeSale TypeSale=new TypeSale();
            while(rs.next()) {
                p = new TypeSale(
                        rs.getInt("idTypeSale"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                TypeSales.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return TypeSales;
    }
    public Boolean update(TypeSale TypeSale) {
        try {
            String query = "update TypeSale"
                    + " set name=?,description=?"
                    + " where idTypeSale=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, TypeSale.getName());
            st.setString(2, TypeSale.getDescription());
            st.setInt(  3, TypeSale.getIdTypeSale());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public Boolean insert(TypeSale p) {
        try {
            String query = "insert into TypeSale (name,description)" +
                    "value(? ,?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, p.getName());
            st.setString(2, p.getDescription());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
