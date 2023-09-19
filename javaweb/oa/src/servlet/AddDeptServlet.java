package servlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import utils.JDBCUtils;
public class AddDeptServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out =  response.getWriter();
        //接收参数
        int deptno = Integer.parseInt(request.getParameter("deptno"));
        String dname = request.getParameter("dname");
        String loc = request.getParameter("location");

        //将获取的参数写入数据库
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        int affectRows;
        try{
            conn = JDBCUtils.getConnect();
            String sql = "insert into dept value(?, ?, ?);";
            pstat = conn.prepareStatement(sql);

            pstat.setInt(1,deptno);
            pstat.setString(2, dname);
            pstat.setString(3,loc);

            affectRows = pstat.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            JDBCUtils.close(rs,pstat,conn);
        }

        if(affectRows == 1){
            out.print("<h1>添加成功! 请自行后退刷新一下</h1>");
        }
        out.print("<a href=\"/oa/dept/list\">后退</a>");
    }
}
