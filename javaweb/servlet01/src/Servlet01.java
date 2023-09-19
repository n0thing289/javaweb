import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class Servlet01 implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse)
            throws ServletException, IOException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/hsp_db02";
            String user = "root";
            String pwd = "hsp";
            conn = DriverManager.getConnection(url, user, pwd);
            // 操作数据库
            String sql = "select * from student;";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            String title = "<h1>id &nbsp;&nbsp;&nbsp;&nbsp; name &nbsp;&nbsp;&nbsp;&nbsp; chinese &nbsp;&nbsp;&nbsp;&nbsp; english &nbsp;&nbsp;&nbsp;&nbsp; math<h2>";
            String result = "<h2>";
            while (resultSet.next()) {
                result += resultSet.getInt("id") + "&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("name")
                        + "&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getInt("chinese") + "&nbsp;&nbsp;&nbsp;&nbsp;"
                        + resultSet.getInt("english") + "&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getInt("math");
                result += "<br />";
            }
            result += "</h2>";

            // 输出到前端
            writer.println(title);
            writer.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
