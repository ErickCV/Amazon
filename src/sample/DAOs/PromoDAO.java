package sample.DAOs;
import sample.TDAs.Packages;
import sample.TDAs.Promotion;
import sample.TDAs.Promotion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.*;

public class PromoDAO {
    Connection conn;
    private static ObservableList<Promotion> data = FXCollections.observableArrayList();
    public PromoDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Promotion Promotion) { data.add(Promotion); }
    public ObservableList<Promotion> findAll() {
        ObservableList<Promotion> Promotions = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Promotion";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Promotion p = null;
            Promotion Promotion=new Promotion();
            while(rs.next()) {
                p = new Promotion(
                        rs.getInt("idPromo"),
                        rs.getString("name"),
                        rs.getString("datePromo")
                );
                Promotions.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Promotions;
    }
    public Boolean update(Promotion promotion) {
        try {
            String query = "update Promotion"
                    + " set name=?,datepromo=?"
                    + " where idPromo=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, promotion.getName());
            st.setString(2, promotion.getDatePromo());
            st.setInt(  3, promotion.getIdPromo());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public Boolean insert(Promotion p) {
        try {
            String query = "insert into Promotion (name,datepromo)" +
                    "value(? ,?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, p.getName());
            st.setString(2, p.getDatePromo());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
