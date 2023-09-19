package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LoginServlet extends GenericServlet{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("正在处理用户登录请求， 请稍后");

        //想在子类使用Servlet对象怎么办？
        ServletConfig config = super.getServletConfig();
        System.out.println("service方法获取ServletConfig对象：" + config);
    }
}
