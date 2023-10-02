package servlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.sql.*;
import utils.JDBCUtils;
import java.io.*;
public class StudentSaveServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        request.setCharacterEncoding("UTF-8");
        int no = Integer.parseInt(request.getParameter("no"));
        String name = request.getParameter("name");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try{
            conn = JDBCUtils.getConnection();
            String sql = "insert into student value(?,?);";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, no);
            ps.setString(2, name);

            count = ps.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }finally{
            JDBCUtils.close(null, ps,conn);
        }

        if(count == 1){
            request.getRequestDispatcher("/success.html").forward(request,response);
//            response.sendRedirect("/servlet10/success.html");
        }else{
            request.getRequestDispatcher("/error.html").forward(request,response);
//            response.sendRedirect("/servlet10/error.html");
        }
    }
}
