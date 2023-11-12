package utils;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCUtil {
    private static ResourceBundle rb = ResourceBundle.getBundle("resource.mysql");
    private static final String user;
    private static final String password;
    private static final String url;
    private static final String driver;

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
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
        Connection conn = threadLocal.get();
        //第一次调用是没有Connection对象
        if(conn == null){
            try {
                //获得 一个Connection对象
                Class.forName(driver);
                conn = DriverManager.getConnection(url, user, password);
                //绑定线程, 集合
                threadLocal.set(conn);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
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
                //关闭连接的时候,要解绑线程
                threadLocal.remove();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
