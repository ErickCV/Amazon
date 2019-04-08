package sample.DAOs;

import sample.TDAs.Address;
import sample.TDAs.City;
import sample.TDAs.Country;
import sample.TDAs.State;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



import java.sql.*;

public class AdreessDAO {
    Connection conn;
    private static ObservableList<Address> data = FXCollections.observableArrayList();
    public AdreessDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Address address) { data.add(address); }
    City city=new City();public ObservableList<Address> findAll() {
        ObservableList<Address> addresses = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM address ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Address p = null;
            while(rs.next()) {
                City city = new City();
                city.setIdCity(rs.getInt("idCity"));
                State state = new State();
                state.setIdState(rs.getInt("idState"));
                Country country = new Country();
                country.setIdCountry(rs.getInt("idCountry"));
                p = new Address(

                        rs.getInt("idAddress"),
                        rs.getString("street"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("nameCommerce"),
                        city,
                        state,
                        country,
                        rs.getInt("cp")
                );
                addresses.add(p);
            }
                rs.close();
                st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return addresses;
    }


    public Boolean insert(Address address) {
        try {
            String query = "insert into Address"
                    + " (street, email, phone, nameCommerce, idCity,idState,idCountry, cp)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, address.getStreet());
            st.setString(2, address.getEmail());
            st.setString(  3, address.getPhone());
            st.setString(4, address.getNameCommerce());
            st.setInt(5,address.getIdCity().getIdCity());
            st.setInt(6,address.getIdCity().getIdState().getIdState());
            st.setInt(7,address.getIdCity().getIdCountry().getIdCountry());
            st.setInt(8,address.getCp());
            st.execute();
            data.add(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
