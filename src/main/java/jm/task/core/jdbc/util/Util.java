package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static final String NAME_USER  = "root";
    public static final String NAME_PASS = "---VLAD---xXx---2000";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, NAME_USER, NAME_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }







}
