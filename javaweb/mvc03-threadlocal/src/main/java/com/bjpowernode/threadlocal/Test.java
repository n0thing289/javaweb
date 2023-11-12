package com.bjpowernode.threadlocal;



import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        Connection conn = null;

        Thread thread = Thread.currentThread();
        System.out.println("main:" + thread);
        UserService userService = new UserService();
        userService.save();
    }
}
