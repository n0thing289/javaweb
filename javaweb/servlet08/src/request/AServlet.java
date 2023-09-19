package request;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class AServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //1. 获取当前时间
        Date date = new Date();

        //3.
        request.setAttribute("nowdate", date);

        //2. 输出到前端并且测试能否在BServlet输出
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        out.print("<h1>请求域的当前时间为:" + request.getAttribute("nowdate") + "</h1>");
        //AServlet能不能跳转到BServlet
        //让AServlet和BServlet放到一次请求中?
        /**
         * BServlet bservlet = new BServlet();
         * bservlet.doGet(request,response);
         * 这样可以达到效果,但是不妥
         *      1. 不受tomcat的管理,对象无法销毁
         * */
        //通过转发请发机制即可

        //TODO 转发其他资源
        //TODO getRemoteAddr()
        //TODO setCharacterEncoding()

    }
}
