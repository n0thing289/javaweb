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
EL：${userObj} 传统方式：<%=request.getAttribute("userObj")%>
<hr>
<%--取对象属性--%>
EL: ${userObj.name} 传统方式: <%=((User)request.getAttribute("userObj")).getName()%>
<hr>
<%--取引用对象属性--%>
EL: ${userObj.addr.city} 传统方式: <%=((User)request.getAttribute("userObj")).getAddr().getCity()%>
<hr>
EL:${userObj.addr.street} 传统方式: <%=((User)request.getAttribute("userObj")).getAddr().getStreet()%>
<hr>
EL:${userObj.addr.email} 传统方式: <%=((User)request.getAttribute("userObj")).getAddr().getEmail()%>