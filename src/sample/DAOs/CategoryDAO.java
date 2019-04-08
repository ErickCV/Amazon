package sample.DAOs;
import sample.TDAs.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryDAO {
    Connection conn;

    private static ObservableList<Category> data = FXCollections.observableArrayList();
    public CategoryDAO(Connection conn) { this.conn = conn; }
    public static void addTransaction(Category category) { data.add(category); }

    public ObservableList<Category> findNameCategory() {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Category";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Category p = null;
            while (rs.next()) {
                p = new Category(
                        rs.getInt("idCategory"),
                        rs.getString("name")
                );
                categories.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return categories;
    }

}
