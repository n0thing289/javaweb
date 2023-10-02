package servlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import bean.User;
public class BServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Object o = request.getAttribute("user");
        User user = (User)o;

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<h1>获取到的请求域对象数据: </h1>" + user.toString());
    }
}
