package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Statement stmt;
    private static PreparedStatement prepStat;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTab = "CREATE TABLE IF NOT EXISTS Users" +
                "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, userName TEXT, userLastName TEXT, age INT)";

        try (Connection conn = Util.getConnection()) {
            stmt = conn.createStatement();
            stmt.executeUpdate(createTab);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTab = "DROP TABLE IF EXISTS Users";
        try (Connection conn = Util.getConnection()) {
            stmt = conn.createStatement();
            stmt.executeUpdate(dropTab);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO Users VALUES (NULL, ?, ?, ?)";
        try (Connection conn = Util.getConnection()) {
            prepStat = conn.prepareStatement(insert);
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.setByte(3, age);
            prepStat.executeUpdate();
            prepStat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM Users WHERE id = ?";
        try (Connection conn = Util.getConnection()) {
            prepStat = conn.prepareStatement(delete);
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
            prepStat.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM Users";
        try(Connection conn = Util.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getAll);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("userName"));
                user.setLastName(rs.getString("userLastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE Users";
        try (Connection conn = Util.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(clean);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
