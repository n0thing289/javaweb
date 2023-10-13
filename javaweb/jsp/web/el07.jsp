<%@ page import="java.util.Arrays" %><%--隐式对象demo--%>
<%@page contentType="text/html;charset=UTF-8"%>
<h2 >获取参数</h2>
EL: ${param.username} 传统方式: <%=request.getParameter("username")%>
<hr>

<h2>获取多组参数</h2>
EL: ${paramValues.aihao[0]}${paramValues.aihao[1]}${paramValues.aihao[2]} 传统方式: <%=Arrays.toString(request.getParameterValues("aihao"))%>
<hr>

<h2>initParam</h2>
EL: ${initParam.username} 传统方式: <%=application.getInitParameter("username")%>