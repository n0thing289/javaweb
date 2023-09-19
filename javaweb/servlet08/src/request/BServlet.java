package request;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class BServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        //尝试拿到请求域中的nowdate
        Object date = request.getAttribute("nowdate");
        //尝试打印到前端
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>请求域的当前时间为:" + request.getAttribute("nowdate") + "</h1>");
    }
}
