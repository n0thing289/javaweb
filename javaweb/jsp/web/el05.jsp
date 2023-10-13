<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.*, bean.User" %>
<%
    List<User> list = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
        list.add(new User("user" + i, "666" + i));
    }

    request.setAttribute("userList",list);
%>

EL:${userList[0]} 传统方式: <%=((List<?>)request.getAttribute("userList")).get(0)%>
<hr>