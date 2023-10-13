<%@page contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty param.username}">
  ${param.username}
</c:if>
<c:if test="${empty param.username}">
  ${"用户名不能为空"}
</c:if>