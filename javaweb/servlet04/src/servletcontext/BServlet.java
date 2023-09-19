package servletcontext;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        ServletContext application = this.getServletContext();
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();
        out.print(application + "<br/>");//org.apache.catalina.core.ApplicationContextFacade@181b2f0f
    }
}
