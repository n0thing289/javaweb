package servlet;

import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import javax.servlet.annotation.*;
import java.sql.*;
import java.util.*;
import utils.*;

@WebServlet({"/dept/list","/dept/add","/dept/delete"})
public class DeptServlet extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取路径, 再分别执行对应的功能
        String path = request.getServletPath();
        switch (path) {
            case "/dept/list":
                doList(request, response);
                break;
            case "/dept/add":
                doAdd(request,response);
                break;
            case "/dept/delete":
                doDelete(request,response);
                break;
        }
    }

    public void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //查询数据库,获取所有部门编号和名称
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer,String> data = new HashMap<>();
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
        request.setAttribute("data", data);
        //转发给list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }
    public void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
        try{
            conn = JDBCUtil.getConnection();
            String sql = "insert into dept value(?,?,?);";
            ps = conn.prepareStatement(sql);

            ps.setInt(1,deptno);
            ps.setString(2,dname);
            ps.setString(3, loc);

            affectRows = ps.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.close(rs,ps,conn);
        }
        //写入成功重定向到list页面，失败就重定向到error页面
        if(affectRows == 1){
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else{
            response.sendRedirect(request.getContextPath()+"/error.html");
        }
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //获取要删除的deptno值
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        //根据值去数据库删除对应的部门
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int affectRows = 0;
        try{
            conn = JDBCUtil.getConnection();
            String sql = "delete from dept where deptno = ?;";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, deptno);

            affectRows = ps.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            JDBCUtil.close(rs,ps,conn);
        }
        //删除成功重定向到list页面，失败重定向到error页面
        if(affectRows == 1){
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }else{
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }
}
