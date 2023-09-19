package servlet;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

import utils.JDBCUtils;

public class DeleteDeptServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int no = Integer.parseInt(request.getParameter("deptno"));
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnect();
            String sql = "delete from dept where deptno = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, no);

            int affectRows = pstat.executeUpdate();
            if (affectRows == 1) {
                out.print("<h1>删除成功！请自行后退上一级界面！</h1>");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, pstat, conn);
        }
        out.print("<a href=\"/oa/dept/list\">后退</a>");
    }
}
