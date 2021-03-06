package dao;

import connect.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

public class CategoryDao {

        public List<Category> getAllCategories() throws SQLException {
                List<Category> CATEGORIES = new ArrayList<Category>();

                Connection connection = JDBCConnection.getJDBCConnection();

                String sql = "SELECT * FROM CATEGORIES";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                        Category category = new Category();

                        category.setId(rs.getInt("ID"));
                        category.setCategoryname(rs.getString("CATEGORYNAME"));

                        CATEGORIES.add(category);
                }

                return CATEGORIES;
        }

        public Category getCategoryById(int id) throws SQLException {

                Connection connection = JDBCConnection.getJDBCConnection();

                String sql = "SELECT * FROM CATEGORIES WHERE ID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                        Category category = new Category();

                        category.setId(rs.getInt("ID"));
                        category.setCategoryname(rs.getString("CATEGORYNAME"));

                        return category;
                }

                return null;
        }

        public void addCategory(Category category) throws SQLException {

                Connection connection = JDBCConnection.getJDBCConnection();

                String sql = "INSERT INTO CATEGORIES(CATEGORYNAME) VALUES(?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, category.getCategoryname());

                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
        }

        public void updateCategory(Category category) throws SQLException {

                Connection connection = JDBCConnection.getJDBCConnection();

                String sql = "UPDATE CATEGORIES SET CATEGORYNAME = ? WHERE ID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, category.getCategoryname());
                preparedStatement.setInt(2, category.getId());

                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
        }

        public void deleteCategory(int id) throws SQLException {

                Connection connection = JDBCConnection.getJDBCConnection();

                String sql = "DELETE FROM CATEGORY WHERE ID = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                int rs = preparedStatement.executeUpdate();
                System.out.println(rs);
        }
}
