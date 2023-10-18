package listener;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
@WebListener()
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent hsbe){
        System.out.println("session attribute added");
    }
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session attribute replaced");
    }
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session attribute removed");
    }
}
