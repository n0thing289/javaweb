package servletcontext;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * 1. ServletContext是什么?
 * 是Servlet规范的一员
 * 2. ServletContext是谁实现的
 * Tomcat服务器实现了ServletContext的接口
 * org.apache.catalina.core.ApplicationContextFacade
 * 3. ServletContext对象是谁创建的?在什么时候创建的
 * 在服务器启动的时候,由服务器创建的
 * 对于一个webapp来说,ServletContext对象只有一个(可以理解为包含了xml文件的所以信息)
 * 在服务器关闭的时候,由服务器销毁
 * 4. ServletContext怎么理解?
 * Servlet对象的上下文对象
 * ServletContext对象就是对应这web.xml文件
 * 放在ServletContext对象中的数据,所以的Servlet一定是共享的(可以理解为: 50个学生每个学生就是servlet, 他们都在一个班级里,这个班级就是ServletContext)
 * Tomcat是一个容器, 可以放很多webapp, 一个webapp对应一个ServletContext对象
 */
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletContext application = this.getServletContext();
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();
        out.print(application + "<br/>");//org.apache.catalina.core.ApplicationContextFacade@181b2f0f

        // 获取上下文的初始化参数
        out.print("<br>获取上下文初始化参数:<br>");
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String key = initParameterNames.nextElement();
            out.print(application.getInitParameter(key) + "<br>");
        }

        //DONE 获取context path (动态获取应用的根路径 /servlet04(老杜的项目名)) 用于拿项目名
        out.print("<br>getContextPath:<br>");
        String contextPath = application.getContextPath();
        out.print(contextPath + "<br>");

        //DONE getRealPath() 获取文件的绝对路径(真实路径) 用于文件流传路径
        //不加/ 也可以. 记住是从web这里开始找的
        out.print("<br>getRealPath:<br>");
        String realPath = application.getRealPath("/index.html");
        out.print(realPath + "<br>");

        //DONE log()|log(String meg, Throwable t) 用于记录日志(原始开发方式才会记录到CATALINA_HOME下的logs)
        //idea开发的你的logs需要在idea相关的文件找(CATALINA_BASE)-> logs(因为idea可以创建多个小猫咪)
        //catalina2021-11-05.log(就是控制台信息)
        //localhost2021-11-05.txt(会记录用户log的日志信息信息)
        //localhost_access_2021-11-05.txt(记录用户登录信息)
        application.log("今天是8月11日!");
        int age = 17;
        if (age < 18) {
            application.log("小屁孩上什么网吧", new RuntimeException("18禁"));
        }

    }
}
