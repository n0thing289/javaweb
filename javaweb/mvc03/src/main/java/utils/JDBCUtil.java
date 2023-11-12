package utils;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static ResourceBundle rb = ResourceBundle.getBundle("resource.mysql");
    private static final String user;
    private static final String password;
    private static final String url;
    private static final String driver;


    static {
        //            Properties properties = new Properties();
//            properties.load(new FileReader("mysql.properties"));
        user = rb.getString("user");
        password = rb.getString("password");
        url = rb.getString("url");
        driver = rb.getString("driver");
        System.out.println("JDBC初始化成功！");
    }

    public static Connection getConnection() {
        try {
            //获得 一个Connection对象
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            if (set != null) {
                set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
