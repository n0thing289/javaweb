import javax.servlet.*;
import java.io.IOException;

public class AServlet implements Servlet {
    public AServlet(){
        System.out.println("AServlet`s constructor method execute!");
    }
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AServlet`s init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("AServlet`s service method execute!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
