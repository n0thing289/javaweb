<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //在四个域中存数据
    pageContext.setAttribute("data","pageContext");
    request.setAttribute("data","request");
    session.setAttribute("data","session");
    application.setAttribute("data", "application");
%>
${data}
