<%@ page import="java.util.*" %>
<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="bean.User"%>
<%
    Map<String, User> map = new HashMap<>();

    map.put("u1", new User("zhangsan", "123"));
    map.put("u2", new User("zhangsan2", "123"));
    map.put("u3", new User("zhangsan3", "123"));
    map.put("u4", new User("zhangsan4", "123"));

    request.setAttribute("userMap", map);
%>

EL: ${userMap.u1} 传统方式<%=((Map<?,?>)request.getAttribute("userMap")).get("u1")%>
<hr>