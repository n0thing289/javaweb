package servlet;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

import utils.JDBCUtils;

public class EditDeptServlet extends HttpServlet {
    private int oldDeptNo;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*先走get拿到数据传回前端*/
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int no = Integer.parseInt(request.getParameter("deptno"));
        oldDeptNo = no;

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnect();
            String sql = "select * from dept where deptno = ?;";
            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, no);

            rs = pstat.executeQuery();
            rs.next();
            int deptno = rs.getInt("deptno");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");

            out.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>修改页面</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<h1>修改部门</h1>\n" +
                    "<hr>\n" +
                    "<form method=\"post\" action=\"/oa/dept/edit\">\n" +
                    "    部门编号<input type=\"text\" name=\"deptno\" value=\"" + deptno + "\" readonly><br>\n" +
                    "    部门名称<input type=\"text\" name=\"dname\" value=\"" + dname + "\"><br>\n" +
                    "    部门位置<input type=\"text\" name=\"loc\" value=\"" + loc + "\"><br>\n" +
                    "    <input type=\"submit\" value=\"修改\">\n" +
                    "</form>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>");
            out.print("<a href=\"/oa/dept/list\">后退</a>");

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, pstat, conn);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int newDeptNo = Integer.parseInt(request.getParameter("deptno"));
        String newDName = request.getParameter("dname");
        String newLoc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int affectRows;
        try {
            conn = JDBCUtils.getConnect();
            String sql = "update dept set deptno = ?, dname = ?, loc = ? where deptno = ?;";
            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, newDeptNo);
            pstat.setString(2, newDName);
            pstat.setString(3, newLoc);
            pstat.setInt(4, oldDeptNo);

            affectRows = pstat.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, pstat, conn);
        }

        if (affectRows == 1) {
            out.print("<h1>修改成功! 请自行后退刷新一下</h1>");
        }
        out.print("<a href=\"/oa/dept/list\">后退</a>");
    }

}
