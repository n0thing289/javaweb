<%@page contentType="text/html;charset=UTF-8" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>部门</title>
    <script type="text/javascript">
        function del(deptno) {
            //给删除操作注册点击事件
            //确认用户是否删除
            //只有确定要删发起请求
            if (window.confirm("亲确定要删除吗?删除后不可恢复噢!")) {
                document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno=" + deptno
            }
        }
    </script>
</head>

<body>
<h1 align="center">部门列表</h1>
<hr>
<table align="center" width="50%" border="1px">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
    <%
        Object o = request.getAttribute("list-data");
        Map<?, ?> map = (HashMap<?, ?>) o;
        int num = 0;

        Set<?> set = map.keySet();
        Object[] arr = set.toArray();
        Arrays.sort(arr);
        for (Object key : arr) {
            int deptno = (int) key;
            String dname = (String) map.get(deptno);
    %>
        <tr>
            <td><%=(++num)%></td>
            <td><%=deptno%></td>
            <td><%=dname%></td>
            <td>
                <a href="javascript:void(0);" onclick='del(<%=deptno%>)'>删除</a>
                <a href="<%=request.getContextPath()%>/dept/edit?deptno=<%=deptno%>">修改</a>
                <a href="<%=request.getContextPath()%>/dept/detail?deptno=<%=deptno%>">详情</a>
            </td>
        </tr>
    <%
        }
    %>
</table>

<hr>
<a href="http://localhost:8080<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>

</html>




