package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;

import utils.*;

@WebServlet({"/dept/list", "/dept/add", "/dept/delete", "/dept/detail", "/dept/edit"})
public class DeptServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);//如果没有现有的session,说明是第一次访问服务器
        if (session != null && session.getAttribute("uid") != null) {
            System.out.println("session验证成功");
            //获取路径, 再分别执行对应的功能
            String path = request.getServletPath();
            switch (path) {
                case "/dept/list":
                    doList(request, response);
                    break;
                case "/dept/add":
                    doAdd(request, response);
                    break;
                case "/dept/delete":
                    doDelete(request, response);
                    break;
                case "/dept/detail":
                    doDetail(request, response);
                    break;
                case "/dept/edit":
                    doEdit(request, response);
                    break;
            }
        } else if (cookies != null) {// 没有cookie, 创建都不创建
            doCookieVerify(request, response, cookies);
        } else {
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        }

    }

    public void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //查询数据库,获取所有部门编号和名称
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer, String> data = new HashMap<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select deptno, dname from dept;";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                //向map中绑定数据
                data.put(deptno, dname);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //向request域中绑定数据
        request.setAttribute("list-data", data);
        //转发给list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取参数的值
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");
//        System.out.println(deptno + dname + loc);
        //把值写入数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int affectRows = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into dept value(?,?,?);";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);

            affectRows = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //写入成功重定向到list页面，失败就重定向到error页面
        if (affectRows == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取要删除的deptno值
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        //根据值去数据库删除对应的部门
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int affectRows = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from dept where deptno = ?;";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, deptno);

            affectRows = ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //删除成功重定向到list页面，失败重定向到error页面
        if (affectRows == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    public void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset-UTF-8");
        //获取dept值
        int no = Integer.parseInt(request.getParameter("deptno"));
        //根据值取数据库中的对应部门详细数据
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<String> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select deptno, dname, loc from dept where deptno = ?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, no);

            rs = ps.executeQuery();
            rs.next();
            int deptno = rs.getInt("deptno");
            String dname = rs.getString("dname");
            String loc = rs.getString("loc");

            //封装数据
            list.add(deptno + "");
            list.add(dname);
            list.add(loc);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        //向request域中绑定数据
        request.setAttribute("detail-data", list);
        //查询成功则转发给detail.jsp，否则重定向error页面
        if (list.size() != 0) {
            request.getRequestDispatcher("/detail.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    public void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String method = request.getMethod();
        if ("GET".equals(method)) {
            //（一）先拿到deptno对应的部门数据，传回detail.jsp
            //获取deptno的值
            int no = Integer.parseInt(request.getParameter("deptno"));
            //根据deptno值取查询数据库
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            ArrayList<String> list = new ArrayList<>(3);
            try {
                conn = JDBCUtil.getConnection();
                String sql = "select deptno, dname, loc from dept where deptno = ?;";
                ps = conn.prepareStatement(sql);

                ps.setInt(1, no);

                rs = ps.executeQuery();
                rs.next();
                int deptno = rs.getInt("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                //封装数据
                list.add(deptno + "");
                list.add(dname);
                list.add(loc);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtil.close(rs, ps, conn);
            }
            //在request域中绑定数据
            request.setAttribute("old-data", list);
            //转发给edit.jsp
            if (list.size() != 0) {
                request.getRequestDispatcher("/edit.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/error.html");
            }
        } else if ("POST".equals(method)) {
            //（二）前端提交后再拿到新的参数写入数据库
            //获取所有的参数值
            int deptno = Integer.parseInt(request.getParameter("deptno"));
            String dname = request.getParameter("dname");
            String loc = request.getParameter("loc");
            //将这些值写入数据库
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            int affectRows = 0;
            try {
                conn = JDBCUtil.getConnection();
                String sql = "update dept set dname=?,loc=? where deptno=?;";
                ps = conn.prepareStatement(sql);

                ps.setString(1, dname);
                ps.setString(2, loc);
                ps.setInt(3, deptno);

                affectRows = ps.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                JDBCUtil.close(rs, ps, conn);
            }
            //写入成功则重定向list页面，失败则重定向到失败页面
            if (affectRows == 1) {
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/error.html");
            }
        }


    }

    public void doCookieVerify(HttpServletRequest request, HttpServletResponse response, Cookie[] cookies) throws ServletException, IOException {
        //拿出一个账户cookie,
        String client = null;
        String str = null;
        String uid = null;
        String pwd = null;
        for (Cookie cookie : cookies) {
            if ("client".equals(cookie.getName())) {
                str = cookie.getValue();
            }
        }
        if (str == null) {
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
            return;
        } else {
            String[] split = str.split("-");
            uid = split[0];
            pwd = split[1];
        }
        //与数据库进行比对
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String password = null;
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
        //判断通过则给一个session,重定向到用户一开始想打开的页面
        if (pwd != null && pwd.equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("uid", uid);
            response.sendRedirect(request.getRequestURL().toString());
        }else{
            response.sendRedirect(request.getContextPath() + "/welcome.jsp");
        }
    }


}
