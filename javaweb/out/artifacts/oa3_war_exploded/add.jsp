<%@page contentType="text/html;charset=UTF-8"%>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>新增页面</title>
    </head>

    <body>
        <h1>新增部门</h1>
        <hr>
        <form method="post" action="<%=request.getContextPath()%>/dept/add">
            部门编号<input type="text" name="deptno"><br>
            部门名称<input type="text" name="dname"><br>
            部门位置<input type="text" name="loc"><br>
            <input type="submit" name="提交">
        </form>
        <br>
        <a  href="<%=request.getContextPath()%>/dept/list">后退</a>
    </body>

    </html>