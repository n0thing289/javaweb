package com.bjpowernode.threadlocal;

public class JDBCUtil {
    private static final MyThreadLocal<Connection> myThreadLocal = new MyThreadLocal<>();

    public static Connection getConnection(){
        Connection connection = myThreadLocal.get();
        if(connection == null){
            //第一次是空
            connection = new Connection();
            //绑定
            myThreadLocal.set(connection);
        }
        return connection;
    }
}
