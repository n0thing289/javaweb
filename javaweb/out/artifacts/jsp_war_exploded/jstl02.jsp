<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--http://localhost:8080/jsp/jstl02.jsp?aihao=1&aihao=2&aihao=3&age=1--%>

<c:forEach items="${paramValues.aihao}" var="hobby" varStatus="time">
    爱好:${hobby}
    序号:${time.count}
    <hr>
</c:forEach>

<br>
<hr>
<c:forEach var="i" begin="1" end="10" step="1">
    ${i}
    <br>
</c:forEach>

<hr>
<c:choose>
    <c:when test="${param.age < 18}">
        小屁孩上什么网
    </c:when>
    <c:when test="${param.age >18}">
        你已经成年了愉快上网吧
    </c:when>
</c:choose>
