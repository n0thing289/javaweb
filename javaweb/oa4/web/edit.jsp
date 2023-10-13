<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改页面</title>
</head>

<body>
<h1>修改部门</h1>
<hr>
<%
    Object o = request.getAttribute("old-data");
    ArrayList<?> list = (ArrayList<?>) o;
%>
<form method="post" action="<%=request.getContextPath()%>/dept/edit">
    部门编号<input type="text" name="deptno" value="<%=list.get(0)%>" readonly><br>
    部门名称<input type="text" name="dname" value="<%=list.get(1)%>"><br>
    部门位置<input type="text" name="loc" value="<%=list.get(2)%>"><br>
    <input type="submit" value="修改">
    <a  href="<%=request.getContextPath()%>/dept/list">后退</a>
</form>
</body>

</html>