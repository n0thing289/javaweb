import javax.servlet.*;
import java.io.IOException;

public class BServlet implements Servlet {
    public BServlet(){
        System.out.println("BServlet`s constructor method execute!");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("BServlet`s init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("BServlet`s service method execute!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
