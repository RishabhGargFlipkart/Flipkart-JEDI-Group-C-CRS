package com.flipkart.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DBUtils {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crsdatabase";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Nidhi@2002";

    private static Connection connection = null;
    public static Connection getConnection()
    {
        if (connection != null)
        {
            try
            {
                if (connection.isClosed())
                {
                    connection = null;
                    return getConnection();
                }
                else
                {
                    return connection;
                }
            }
            catch (SQLException e)
            {
                return getConnection();
            }
        }
        else
        {
            try
            {
//                Properties prop = new Properties();
////                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
////                prop.load(inputStream);
//                String driver = prop.getProperty(JDBC_DRIVER);
//                String url = prop.getProperty(DB_URL);
//                String user = prop.getProperty(USER);
//                String password = prop.getProperty(PASS);
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
