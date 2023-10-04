<%--
  Created by IntelliJ IDEA.
  User: 28927
  Date: 2023/10/4
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>请登录</title>
    <script type="text/javascript">
        window.onload = function(){
            let uid = document.querySelector(".uid")
            let pwd = document.querySelector(".pwd")
            let form = document.querySelector(".form")
            let submit = document.querySelector(".sb");

            submit.addEventListener("click", function(){
                console.log(uid.value)
                console.log(pwd.value)
                if(uid.value == null && pwd.value == null){
                    alert("no null")
                }else{
                    form.setAttribute("action", "<%=request.getContextPath()%>/dept/login")
                }
            })
        }
    </script>
</head>
<body>
<h1>
    欢迎使用OA,请先登录!
</h1>
<hr>
<br>
<form method="post" action="javascript:void(0);" class="form">
    用户名<input type="text" name="uid" value="" class="uid"> <br>
    密码  <input type="password" name="pwd" value="" class="pwd"> <br>
    <input type="submit" value="登录" class="sb">
</form>
</body>

</html>
