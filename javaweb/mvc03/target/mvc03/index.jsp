<%@page contentType="text/html;charset=UTF-8"%>
<%@page isELIgnored="false"%>
<html>
    <title>mvc03模拟银行转账不使用mvc</title>
<body>
<h2>Hello World!</h2>

<form action="${pageContext.request.contextPath}/transfer" method="POST">
    转出账户：<input type="text" name="fromActNo"> <br>
    转入账户：<input type="text" name="toActNo"> <br>
    转账金额：<input type="text" name="money"> <br>
    <input type="submit" value="转账">
</form>
<p><%=request.getContextPath()%></p>
<p>${pageContext.request.contextPath}</p>
</body>
</html>
