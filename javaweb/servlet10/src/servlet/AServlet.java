package servlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import bean.User;
public class AServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        //创建javabean
        User user = new User(1,"jack");
        //向请求域传数据
        request.setAttribute("user", user);
        //转发给BServlet
        request.getRequestDispatcher("/b").forward(request,response);
    }
}
