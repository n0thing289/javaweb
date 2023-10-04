package servlet;
import utils.JDBCUtil;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.annotation.*;
@WebServlet({"/dept/login"})

public class UserServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        doLogin(request,response);
    }
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取登录表单的值
        String uid = request.getParameter("uid");
        String pwd = request.getParameter("pwd");
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
        //验证成功则重定向到index页面,失败则重定向到error页面
        if (pwd.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("login","hsp");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}