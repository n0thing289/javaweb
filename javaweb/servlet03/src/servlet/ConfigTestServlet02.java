package servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 这个程序的目的是测试一下,是否共享同一个ServletConfig对象
 * */
public class ConfigTestServlet02 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //获取
        ServletConfig servletConfig = super.getServletConfig();
        //输出该对象到前端
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = servletResponse.getWriter();
        pw.println(servletConfig);
        //org.apache.catalina.core.StandardWrapperFacade@5195d1a7
    }
}
