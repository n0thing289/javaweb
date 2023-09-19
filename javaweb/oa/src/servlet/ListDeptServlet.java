package servlet;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

import utils.JDBCUtils;

import java.sql.*;

public class ListDeptServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //链接数据库
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet reset = null;

        String sql = "select * from dept";
        //得到数据库数据
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            conn = JDBCUtils.getConnect();
            pstat = conn.prepareStatement(sql);
            reset = pstat.executeQuery();
            //打印这部分死数据
            out.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>部门</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <h1 align=\"center\">部门列表</h1>\n" +
                    "    <hr>\n" +
                    "    <table align=\"center\" width=\"50%\" border=\"1px\">\n" +
                    "        <tr>\n" +
                    "            <th>序号</th>\n" +
                    "            <th>部门编号</th>\n" +
                    "            <th>部门名称</th>\n" +
                    "            <th>操作</th>\n" +
                    "        </tr>");
            //打印这部分动态的数据
            int num = 0;
            while (reset.next()) {
                int deptno = reset.getInt("deptno");
                String dname = reset.getString("dname");
                String loc = reset.getString("loc");
                out.print("<tr>\n" +
                        "            <td>" + (++num) + "</td>\n" +
                        "            <td>" + deptno + "</td>\n" +
                        "            <td>" + dname + "</td>\n" +
                        "            <td>\n" +
                        "                <a href=\"/oa/dept/delete?deptno=" + deptno + "\">删除</a>\n" +
                        "                <a href=\"/oa/dept/edit?deptno=" + deptno + "\">修改</a>\n" +
                        "               <a href=\"/oa/dept/detail?deptno=" + deptno + "\">详情</a>\n" +
                        "            </td>\n" +
                        "        </tr>");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(reset, pstat, conn);
        }
        //打印这部分死的数据
        out.print("</table>\n" +
                "\n" +
                "    <hr>\n" +
                "    <a href=\"http://localhost:8080/oa/add.html\">新增部门</a>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }
}