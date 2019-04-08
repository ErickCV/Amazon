package sample.DAOs;

import sample.TDAs.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RoleDAO
{
    Connection conn;
    private static ObservableList<Role> data = FXCollections.observableArrayList();
    public RoleDAO(Connection conn)
    {
        this.conn = conn;
    }
    public static void addTransaction(Role role)
    {
        data.add(role);
    }
    public ObservableList<Role> findAll()
    {
        ObservableList<Role> roles = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Role";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Role p = null;
            Role role=new Role();
            while(rs.next()) {
                p = new Role(
                       rs.getInt("idRole"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                roles.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return roles;
    }
    public Boolean update(Role Role) {
        try {
            String query = "update Role"
                    + " set name=?,description=?"
                    + " where idRole=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, Role.getName());
            st.setString(2, Role.getDescription());
            st.setInt(  3, Role.getIdRole());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
    public Boolean insert(Role p) {
        try {
            String query = "insert into Role (name,description)" +
                    "values(? ,?)";
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
