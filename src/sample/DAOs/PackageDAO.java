package sample.DAOs;

import sample.TDAs.Packages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

import java.sql.*;

public class PackageDAO
{
    Connection conn;
    private static ObservableList<Packages> data = FXCollections.observableArrayList();
    public PackageDAO(Connection conn)
    {

        this.conn = conn;
    }
    public static void addTransaction(Packages address)

    {
        data.add(address);
    }

    public Boolean insert(Packages p) {
        try {
            String query = "insert into package (namePackage,description,price,stock)" +
                    "values(? ,? ,? ,? )";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, p.getNamePackage());
            st.setString(2, p.getDescription());
            st.setDouble(  3, p.getPrice());
            st.setInt(4, p.getStock());
            //st.setInt(5,address.getIdCity().getIdCity());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public ObservableList<Packages> findAll() {
        ObservableList<Packages> packages = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM package";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Packages p = null;
            while (rs.next()) {
                p = new Packages(
                        rs.getInt("idPackage"),
                        rs.getString("namePackage"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getString("image")
                );
                packages.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return packages;
    }
    public Boolean update(Packages packages) {
        try {
            String query = "update package "
                    + " set namePackage=?,description=?,price=?,stock=?,image=?"
                    + " where idPackage=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, packages.getNamePackage());
            st.setString(2, packages.getDescription());
            st.setDouble(  3, packages.getPrice());
            st.setInt(4, packages.getStock());
            st.setString(5, packages.getImage());
            st.setInt(6, packages.getIdPackage());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
}
