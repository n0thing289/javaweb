package filterServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
// {"/a.do", "/b.do"}
// {"*.do"}
@WebFilter({"*.do"})
public class Filetr01 implements Filter {
    public Filetr01() {
        System.out.println("Filter01的无参构造器被调用");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init方法被调用");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter方法被调用");
        chain.doFilter(request,response);
        System.out.println("doFilter方法结束");
    }

    @Override
    public void destroy() {
        System.out.println("destroy方法被调用");
    }
}
//TODO p57测试第一个过滤器
