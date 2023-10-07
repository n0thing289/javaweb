package testcookie;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet({"/cookie/generate"})
public class TestCookie extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1. 创建一个cookie
        Cookie cookie = new Cookie("user", "2892734127");
        //2. 只要cookie的设置有效时间>0， 浏览器会存放在硬盘中。 不设置有效时间默认cookie存在浏览器内存（缓存）中
        cookie.setMaxAge(60);//0 用于删去浏览器的同名cookie //<0 不会被存储
        //3. 响应给客户端
        response.addCookie(cookie);
    }
}
