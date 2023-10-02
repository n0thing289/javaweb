package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

import utils.JDBCUtils;

public class AddDeptServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //接收参数
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        String dname = request.getParameter("dname");
        String loc = request.getParameter("location");

        //将获取的参数写入数据库
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int affectRows = 0;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into dept value(?, ?, ?);";
            pstat = conn.prepareStatement(sql);

            pstat.setInt(1, deptno);
            pstat.setString(2, dname);
            pstat.setString(3, loc);

            //事务
            conn.setAutoCommit(false);
            affectRows = pstat.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(rs, pstat, conn);
        }

        if (affectRows == 1) {
//            out.print("<h1>添加成功! 请自行后退刷新一下</h1>");

            request.getRequestDispatcher("/dept/list").forward(request, response);//转发后，由于list是doget方法，但是你这add是post方法，转发后还是post请求，转发后会报错
            //这里最好是一个重定向，因为当前转发的是一个post的request，转发后那个servlet要有doPost方法
            response.sendRedirect(request.getContextPath() + "/dept/list");

        } else {
            //添加失败的情况
//            request.getRequestDispatcher("/error.html").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
//        out.print("<a href=\"/oa/dept/list\">后退</a>");
    }
}
