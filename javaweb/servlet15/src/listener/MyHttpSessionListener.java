package listener;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener()
public class MyHttpSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent hse){
        System.out.println("session创建了");
    }
    public void sessionDestroyed(HttpSessionEvent hse){
        System.out.println("session销毁了");
    }
}
