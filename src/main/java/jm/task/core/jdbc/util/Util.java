package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public static Connection getConnection() {
        Connection connection;
        final String NAME_USER  = "root";
        final String NAME_PASS = "---VLAD---xXx---2000";
        final String URL = "jdbc:mysql://localhost:3306/jdbc";

        try {
            connection = DriverManager.getConnection(URL, NAME_USER, NAME_PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

}
