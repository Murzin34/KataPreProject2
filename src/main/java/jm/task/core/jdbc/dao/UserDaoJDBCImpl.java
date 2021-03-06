package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    
    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate( "CREATE TABLE IF NOT EXISTS users (" +
            "id int not null auto_increment primary key,"+
            "name varchar(45),"+
            "lastName varchar(45),"+
            "age int)");
        } catch (SQLException e) {

        }
    }
/*    public void createUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement stmt = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (" +
                    "id int not null auto_increment primary key,"+
                    "name varchar(45),"+
                    "lastName varchar(45),"+
                    "age int)";
            stmt.execute(createTableQuery);
        } catch (SQLException e) {

        }
   }
 */
    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        try (Statement stmt = connection.createStatement()) {
            String dropTableQuery = "DROP TABLE users";
            stmt.execute(dropTableQuery);
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) value (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {

        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = Util.getConnection();
        try (Statement statement = connection.createStatement();) {
            ResultSet res = statement.executeQuery("select * from Users");
            while (res.next()){
                User user = new User();
                user.setAge(res.getByte("age"));
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException throwables) {
            return null;
        }
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try(Statement statement = connection.createStatement()){
            String cleanTable = "DELETE FROM users";
            statement.execute(cleanTable);
        } catch (SQLException throwables) {

        }
    }
}
