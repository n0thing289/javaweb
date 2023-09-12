import javax.servlet.*;
import java.io.*;
import java.util.*;

public class reviewServletConfig implements Servlet {
    public ServletConfig sconfig = null;

    public void init(ServletConfig config) throws ServletException {
        sconfig = config;
    }

    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //在后台和前端分别打印ServletConfig测试的效果
        //getInitParameter()
        String specValue = sconfig.getInitParameter("hwk");
        //getInitParameterNames()
        Enumeration<String> enumNames = sconfig.getInitParameterNames();
        String names = "";
        String values = "";
        while (enumNames.hasMoreElements()) {
            String name = enumNames.nextElement();
            String value = sconfig.getInitParameter(name);
            names += name + "  ";
            values += value + "  ";
        }
        //getServletContext()
        ServletContext context = sconfig.getServletContext();
        //getServletName()
        String servletName = sconfig.getServletName();

        //print
        System.out.println("hwk对应的:" + specValue);
        System.out.println("所有的param-name: " + names + " | 所有的param-value: " + values);
        System.out.println("上下文接口的具体实现:" + context + "以及ServletConfig接口的具体实现:" + sconfig);
        System.out.println("servlet的名字:" + servletName);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("hwk对应的:" + specValue);
        out.println("<br>所有的param-name: " + names + " | 所有的param-value: " + values);
        out.println("<br>上下文接口的具体实现:" + context + "以及ServletConfig接口的具体实现:" + sconfig);
        out.println("<br>servlet的名字:" + servletName);
    }

    public void destroy() {

    }

    public String getServletInfo() {
        return null;
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }
}
