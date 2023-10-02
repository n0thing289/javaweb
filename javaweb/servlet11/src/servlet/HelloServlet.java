package servlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.*;
import java.util.Enumeration;
@WebServlet(name="hello", urlPatterns={"/hello"}, loadOnStartup=1,
        initParams={@WebInitParam(name="user",value="root"), @WebInitParam(name="password", value="hsp")})
public class HelloServlet extends HttpServlet{

    public HelloServlet(){
        System.out.println("<HelloServlet 无参构造器启动了！>");
    }

    //1体验注解开发的基本作用
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.print("<h1>Hello World!</h1>");

            //获取servlet name
            out.print("<br> servletName=" + this.getServletName());
            //获取servlet path
            out.print("<br> servletPath=" + request.getServletPath());
            //获取contect path
            out.print("<br> contextPath=" + request.getContextPath());

            //获取initParams
            out.print("<br>");
            Enumeration<String> names = this.getInitParameterNames();
            while(names.hasMoreElements()){
                String name = names.nextElement();
                String value = getInitParameter(name);
                out.print("name="+name);
                out.print("  value="+value);
                out.print("<br>");
            }
    }
}
