package com.bjpowernode.threadlocal;

public class UserDAO {

    public void insert() {
        Thread thread = Thread.currentThread();
        System.out.println("UserDAO:" + thread);

        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

        System.out.println("use UserDAO insert()!");
    }
}
