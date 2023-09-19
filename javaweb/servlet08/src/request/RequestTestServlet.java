package request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO 打印resquest
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(req);//org.apache.catalina.connector.RequestFacade@6862f00f

        //TODO 编写前端注册代码

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        /**Map<String, String[]> parameterMap = req.getParameterMap()
         /*String[] parameterValues = req.getParameterValues(String key)
         /*Enumeration<String> parameterKeys = getParameterNames()
         String value = req.getParameter(String)*/

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Map<String, String[]> parameterMap = req.getParameterMap();
        Set<String> strings = parameterMap.keySet();
        for (String s : strings) {
            String[] parameterValues = req.getParameterValues(s);

            for (String s2 : parameterValues) {
//                out.print(s + ":" + s2 + "\t");
                System.out.print(s + ":" + s2 + "\t");
            }
//            out.print("<br>");
            System.out.println();
        }

        //TODO 研究请求的参数中文乱码问题

        //TODO getContextPath() 获取项目名

        //TODO getMethod()

        //TODO 获取请求的URI getRequestURI() (带项目名)

        //TODO 获取servlet path getServletPath() (不带项目名)
    }
}
