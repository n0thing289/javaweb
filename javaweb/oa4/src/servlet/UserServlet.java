package servlet;

import utils.JDBCUtil;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.annotation.*;

@WebServlet({"/user/login", "/user/quit"})

public class UserServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        String path = request.getServletPath();
        switch(path){
            case "/user/login":
                doLogin(request, response);
                break;
            case "/user/quit":
                doQuit(request, response);
        }
    }

    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取登录表单的值
        String uid = request.getParameter("uid");
        String pwd = request.getParameter("pwd");
        String loginCookie = request.getParameter("auto-login-10");
        //根据uid获取数据库中的password
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String password = "";
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select pwd from user where uid = ?;";
            ps = conn.prepareStatement(sql);

            ps.setString(1, uid);

            rs = ps.executeQuery();
            rs.next();
            password = rs.getString("pwd");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //验证成功则重定向到index页面,失败则重定向到登录页面
        if (pwd != null && pwd.equals(password)) {
            if("on".equals(loginCookie)){
                String str = uid + "-" + pwd;
                //创建cookie
                Cookie cookie = new Cookie("client", str);
                //设置cookie失效时间
                cookie.setMaxAge(60 * 10);
                //设置cookie路径(UserServlet的路径是/oa3/user, 后面重定向到/oa3/dept/*了; 要重新设置关联路径,不然会出现不携带cookie)
                cookie.setPath(request.getContextPath());
                //返回给浏览器
                response.addCookie(cookie);
            }
            HttpSession session = request.getSession();
            session.setAttribute("uid", uid);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    public void doQuit(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath()+"/welcome.jsp");
    }
}
