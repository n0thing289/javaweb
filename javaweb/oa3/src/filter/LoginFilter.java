package filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("过滤器工作开始");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //放行的情况
        //1.已经登录的用户
        //2.直接访问登录页面的
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        String client = null;
        String str = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("client".equals(cookie.getName())) {
                    str = cookie.getValue();
                }
            }
        }

        String servletPath = request.getServletPath();
        if (("/user/login".equals(servletPath) || "/user/quit".equals(servletPath)) ||
                (session != null && session.getAttribute("user") != null)) {
            chain.doFilter(request, response);
        } else if (str != null) {
            chain.doFilter(request, response);
        } else if (servletPath.equals("/welcome.jsp")) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        }
        System.out.println("过滤器工作结束");
    }
}
