package sample.DAOs;

import sample.TDAs.Role;
import sample.TDAs.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UserDAO {
    Connection conn;
    private static ObservableList<Users> data = FXCollections.observableArrayList();
    public UserDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Users users) { data.add(users); }
    public ObservableList<Users> findAll() {
        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Users ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Users p = null;
            Role role=new Role();
            role.setIdRole(rs.getInt("idRole"));
            while(rs.next()) {
                p = new Users(
                        rs.getString("user"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        role
                );
                usersObservableList.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return usersObservableList;
    }

    public int login(String user, String pass) {
        int log = 3;
        try {
            String query = "CALL login('"+user+"','"+pass+"')";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.last();
            log = rs.getInt("log");

            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return log;
    }
    public void loginClose() {
        try {
            String query = "CALL loginClose()";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }

    }

    public Boolean insert(Users users) {
        try {
            String query = "insert into Users "
                    + " (user ,userName, password, idRole)"
                    + " values (?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, users.getUser());
            st.setString(2, users.getUserName());
            st.setString(3, users.getPassword());
            st.setInt(  4, users.getIdRole().getIdRole());
            st.execute();
            data.add(users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

}
