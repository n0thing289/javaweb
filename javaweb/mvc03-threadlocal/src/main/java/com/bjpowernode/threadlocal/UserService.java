package com.bjpowernode.threadlocal;



public class UserService {
    UserDAO userDAO = new UserDAO();

    public void save( ){
        Thread thread = Thread.currentThread();
        System.out.println("UserService:"+thread);

        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);
        userDAO.insert();
    }
}
