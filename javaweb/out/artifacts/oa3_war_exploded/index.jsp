<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>欢迎使用OA系统</title>
</head>

<body>
<%--    <%--%>
<%--        String contextPath = request.getContextPath();--%>
<%--        out.print("<a href=\"" + contextPath + "/list.jsp\">查看部门列表</a>");--%>
<%--    %>--%>
      <a href="<%=request.getContextPath()%>/dept/list">查看部门列表</a>
</body>

</html>