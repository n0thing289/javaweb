package servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 学习ServletConfig
 *  1. ServletConfig是什么？
 *      是servlet规范的一员
 *      ServletConfig是一个接口
 *  2. 谁去实现了这个接口？
 *      org.apache.catalina.core.StandardWrapperFacade
 *      结论:tomcat服务器实现了这个接口
 *      思考: 如果把tomcat服务器换成jetty服务器,输出ServletConfig对象的时候,还是一样的结果吗?
 *          不一定一样
 *  3. 各个Servlet对象是否共享同一个ServletConfig对象？
 *      不共享,一一对应的
 *  4. ServletConfig对象是谁创建的?是在什么时候创建的?
 *      tomcat创建的, 创建Servlet对象的的时候同时创建ServletConfig对象
 *  5. ServletConfig接口到底是干啥的?有什么用?
 *      Config是Configuration是的缩写
 *      ServletConfig对象就是Servlet对象的配置信息对象
 *  6. 那么到底包装了那些信息呢?
 *      <servlet>
 *         <servlet-name>testconfig</servlet-name>
 *         <servlet-class>servlet.ConfigTestServlet</servlet-class>
 *     </servlet>
 *     ServletConfig包装了web.xml文件<servlet></servlet>标签的配置信息(Tomcat解析web.xml的时候)
 * 7. ServletConfig接口有哪些方法?
 *      xml文件里配置初始化参数<init-param></init>
 *      通过ServletConfig对象的两个方法,可以拿到web.xml文件中的初始化信息
 *
 *      servletConfig.getInitParameterNames()
 *      servletConfig.getInitParameter(String parameterName)
 *
 *      Enumeration
 *          hasMoreElements()
 *          nextElement()
 * 8. ServletConfig接口中有四个方法(都可以在父类Generic中直接调用)
 *      1: getInitParameter()
 *      2: getInitParameterNames()
 *      3. getServletContext()
 *      4. getServletName()
 * */
public class ConfigTestServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //获取
        ServletConfig servletConfig = super.getServletConfig();
        //输出该对象到前端
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = servletResponse.getWriter();
        pw.println(servletConfig);
        //org.apache.catalina.core.StandardWrapperFacade@6862f00f

        //获取<servlet-name>
        String name = servletConfig.getServletName();
        pw.println("<br/>");
        pw.println(name);

        //通过ServletConfig对象的两个方法,可以拿到web.xml文件中的初始化信息
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        //遍历集合Enumeration<String> initParameterNames
        pw.println("<br/>");
        while(initParameterNames.hasMoreElements()){
            String parameterName = initParameterNames.nextElement();
            pw.print(parameterName + ":" + servletConfig.getInitParameter(parameterName));
            pw.println("<br/>");
        }

        //第一种通过 ServletConfig对象获取ServletContext对象 也可以通过GenericServlet的getServletContext()
        ServletContext application = servletConfig.getServletContext();//为什么起名application,到后面jsp就理解了
        pw.println("<br/>" + application);//org.apache.catalina.core.ApplicationContextFacade@25e3c7b

        //第二种 GenericServlet的getServletContext()
        ServletContext application2 = super.getServletContext();
        pw.println("<br/>" + application2);//org.apache.catalina.core.ApplicationContextFacade@25e3c7b
    }
}
