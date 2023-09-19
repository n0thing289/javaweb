package get_post;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PostServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter out = servletResponse.getWriter();

        out.println("<!doctype HTML>");
        out.println("<html>");
        out.println("    <head>");
        out.println("        <title>from post servlet</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <h1>from post servlet</h1>");
        out.println("    </body>");
        out.println("</html>");
    }
}
