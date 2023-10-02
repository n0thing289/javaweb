<%--
  Created by IntelliJ IDEA.
  User: 28927
  Date: 2023/10/1
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
System.out.println("hi");
<%
    //现在是在service方法中
    System.out.println("hi");
    System.out.println("因为是在方法内所有什么能写什么不能写,你必须清楚");
%>

<%!
   private String info = "定义一个成员变量,jsp位置虽然在下面,但是翻译的时候会放到service()上面";
%>