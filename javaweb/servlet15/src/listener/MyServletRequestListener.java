package listener;
import javax.servlet.*;
import javax.servlet.annotation.*;

@WebListener()
public class MyServletRequestListener implements ServletRequestListener {
    public void requestInitialized(ServletRequestEvent sre){
        System.out.println("请求对象创建了");
    }

    public void requestDestroyed(ServletRequestEvent sre){
        System.out.println("请求对象销毁了");
    }
}
