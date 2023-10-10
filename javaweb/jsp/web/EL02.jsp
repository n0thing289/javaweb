<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="bean.User"%>
<%@ page import="bean.Address" %>
<%
    User user = new User();
    user.setName("zhangsan");
    user.setPassword("123");

    user.setAddr(new Address("北京","海定区","123456"));

    //向域中存数据
    request.setAttribute("userObj",user);
%>
<%--取对象数据--%>
${userObj}
<br>
<%--取对象属性--%>
${userObj.name}
<br>
<%--取引用对象属性--%>
${userObj.addr.city}
${userObj.addr.street}
${userObj.addr.email}