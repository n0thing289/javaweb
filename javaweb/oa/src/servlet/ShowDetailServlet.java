package servlet;

import javax.servlet.http.*;
import java.sql.*;

import utils.JDBCUtils;

import java.io.*;

public class ShowDetailServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>详情页面</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <h1>部门详情</h1>");
        //接收部门编号参数
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        //根据部门编号,去查找对应的部门
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from dept where deptno = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setInt(1, deptno);

            rs = pstat.executeQuery();

            while (rs.next()) {
                int no = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("<hr> 部门编号:" + no + " <br> 部门名称:" + dname + " <br> 部门位置:" + loc + " <br>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs,pstat,conn);
        }
        //将页面返回给前端
        out.print("<a href=\"/oa/dept/list\">后退</a>");
        out.print("</body>\n" +
                "\n" +
                "</html>");

    }
}
