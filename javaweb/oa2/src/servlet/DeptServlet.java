package servlet;

import utils.JDBCUtils;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.annotation.*;

/**
 * /dept/list
 * /dept/detail
 * /dept/delete
 * /dept/add
 * /dept/edit
 */
//注解代替xml
@WebServlet(name = "deptservlet", urlPatterns = {"/dept/list", "/dept/detail", "/dept/delete", "/dept/add", "/dept/edit"})
public class DeptServlet extends HttpServlet {
    private int oldDeptNo;

    //重写doGet和doPost的上一级方法service,模板方法设计模式
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();//getRequestURI() getRequestURL()
        switch (path) {
            case "/dept/list":
                doList(request, response);
                break;
            case "/dept/detail":
                doDetail(request, response);
                break;
            case "/dept/delete":
                doDel(request, response);
                break;
            case "/dept/add":
                doAdd(request, response);
                break;
            case "/dept/edit":
                doEdit(request, response);
                break;
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        //链接数据库
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet reset = null;

        String sql = "select * from dept";
        //得到数据库数据
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            conn = JDBCUtils.getConnection();
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
                    "<script type='text/javascript'>\n" +
                    "                function del(deptno) {\n" +
                    "                    if(window.confirm(\"亲，删除后不可回复哦！\")) {\n" +
                    "                        document.location.href = \"" + contextPath + "/dept/delete?deptno=\" + deptno;\n" +
                    "                    }\n" +
                    "                }\n" +
                    "</script>" +
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
                        "                <a href=\"javascript:void(0);\" onclick='del(" + deptno + ")'>删除</a>\n" +
                        "                <a href=\"" + contextPath + "/dept/edit?deptno=" + deptno + "\">修改</a>\n" +
                        "               <a href=\"" + contextPath + "/dept/detail?deptno=" + deptno + "\">详情</a>\n" +
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
                "    <a href=\"" + contextPath + "/add.html\">新增部门</a>\n" +
                "</body>\n" +
                "\n" +
                "</html>");
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
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
            JDBCUtils.close(rs, pstat, conn);
        }
        //将页面返回给前端
        out.print("<a href=\"" + contextPath + "/dept/list\">后退</a>");
        out.print("</body>\n" +
                "\n" +
                "</html>");
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        PrintWriter out = null;
        String method = request.getMethod();
        switch (method) {
            case "GET":
                /*先走get拿到数据传回前端*/
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                out = response.getWriter();

                int no = Integer.parseInt(request.getParameter("deptno"));
                oldDeptNo = no;

//                Connection conn = null;
//                PreparedStatement pstat = null;
//                ResultSet rs = null;
                try {
                    conn = JDBCUtils.getConnection();
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
                            "<form method=\"post\" action=\"" + request.getContextPath() + "/dept/edit\">\n" +
                            "    部门编号<input type=\"text\" name=\"deptno\" value=\"" + deptno + "\" readonly><br>\n" +
                            "    部门名称<input type=\"text\" name=\"dname\" value=\"" + dname + "\"><br>\n" +
                            "    部门位置<input type=\"text\" name=\"loc\" value=\"" + loc + "\"><br>\n" +
                            "    <input type=\"submit\" value=\"修改\">\n" +
                            "</form>\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>");
                    out.print("<a href=\"" + request.getContextPath() + "/dept/list\">后退</a>");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBCUtils.close(rs, pstat, conn);
                }
                break;
            case "POST":
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                out = response.getWriter();

                int newDeptNo = Integer.parseInt(request.getParameter("deptno"));
                String newDName = request.getParameter("dname");
                String newLoc = request.getParameter("loc");

//                Connection conn = null;
//                PreparedStatement pstat = null;
//                ResultSet rs = null;
                int affectRows;
                try {
                    conn = JDBCUtils.getConnection();
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
//            request.getRequestDispatcher("/dept/list").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/dept/list");
                } else {
//            request.getRequestDispatcher("/error.html").forward(request,response);
                    response.sendRedirect(request.getContextPath() + "/error.html");
                }

                out.print("<a href=\"" + request.getContextPath() + "/dept/list\">后退</a>");
                break;
        }

    }
}
