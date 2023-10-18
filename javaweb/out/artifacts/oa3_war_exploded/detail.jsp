<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.*" %>
<%@ page import="bean.Dept" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>详情页面</title>
</head>

<body>
<h1>部门详情</h1>
<%
    Object o = request.getAttribute("detail-data");
//    ArrayList<?> list = (ArrayList<?>) o;
    Dept dept = (Dept)o;
%>
<hr>
部门编号:<%=dept.getDeptno()%> <br>
部门名称:<%=dept.getDname()%> <br>
部门位置:<%=dept.getLoc()%> <br>
<a href="<%=request.getContextPath()%>/dept/list">后退</a>

</body>

</html>