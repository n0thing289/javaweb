import javax.servlet.*;
import java.io.*;
import java.util.*;
public class reviewServletContext extends GenericServlet{
    private ServletContext context = super.getServletContext();
    public void service(ServletRequest request, ServletResponse response) throws ServletException,IOException{
        //getInitParameter()
        String specValue = context.getInitParameter("review");
        //getInitParameterNames()
        Enumeration<String> enumNames = context.getInitParameterNames();
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        while(enumNames.hasMoreElements()){
            String name = enumNames.nextElement();
            String value = context.getInitParameter(name);
            names.append(name).append("  ");
            values.append(value).append("  ");
        }
        //getContextPath()
        String contextPath = context.getContextPath();
        //log()
        //setAttribute()

        //getAttribute()

        //removeAttribute()

    }
}
