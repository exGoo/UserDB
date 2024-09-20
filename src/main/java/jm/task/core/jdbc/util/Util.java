package jm.task.core.jdbc.util;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;

import java.lang.module.Configuration;
import java.sql.*;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }
}
