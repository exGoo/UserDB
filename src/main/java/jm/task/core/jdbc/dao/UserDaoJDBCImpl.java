package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection conn = Util.getConnection();
    private static Statement stmt;
    private static PreparedStatement prepStat;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTab = "CREATE TABLE IF NOT EXISTS Users" +
                "(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, userName TEXT, userLastName TEXT, age INT)";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(createTab);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropTab = "DROP TABLE IF EXISTS Users";
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(dropTab);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO Users VALUES (NULL, ?, ?, ?)";
        try {
            prepStat = conn.prepareStatement(insert);
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.setByte(3, age);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM Users WHERE id = ?";
        try {
            prepStat = conn.prepareStatement(delete);
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String getAll = "SELECT * FROM Users";
        try {
            ResultSet rs = stmt.executeQuery(getAll);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("userName"));
                user.setLastName(rs.getString("userLastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE Users";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(clean);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
