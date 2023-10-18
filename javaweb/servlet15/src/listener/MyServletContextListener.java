package listener;
import javax.servlet.*;
import javax.servlet.annotation.*;

/**
 * ServletContextListener主要功能是在Context对象创建，也就是服务器启动和关闭的时候，需要执行一些代码
* */
@WebListener
public class MyServletContextListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent sce){
        System.out.println("Context对象初始化了");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Context对象销毁了");
    }
}
