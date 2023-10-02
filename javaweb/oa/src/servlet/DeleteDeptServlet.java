package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

import utils.JDBCUtils;

public class DeleteDeptServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int no = Integer.parseInt(request.getParameter("deptno"));
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int affectRows = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "delete from dept where deptno = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, no);

            conn.setAutoCommit(false);
            affectRows = pstat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, pstat, conn);
        }

        if (affectRows == 1) {
//                out.print("<h1>删除成功！请自行后退上一级界面！</h1>");
            //删除成功自动返回list页面
//            request.getRequestDispatcher("/dept/list").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/dept/list");

        } else {
            //删除失败
//            request.getRequestDispatcher("/error.html").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
//        out.print("<a href=\"/oa/dept/list\">后退</a>");
    }
}
