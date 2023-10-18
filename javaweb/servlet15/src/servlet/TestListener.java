package servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/test/httpsession"})
public class TestListener extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //向session域中添加修改和删除数据
        HttpSession session = request.getSession(false);

        //add
        session.setAttribute("message","hi");

        //replace
        session.setAttribute("message","真不搓");

        //destroy
        session.removeAttribute("message");

        //
        session.invalidate();
    }
    /**
     * Context对象初始化了
     *
     * 请求对象创建了
     * session创建了
     * 请求对象销毁了
     *
     * 请求对象创建了
     * session attribute added
     * session attribute replaced
     * session attribute removed
     * session销毁了
     * 请求对象销毁了
     *
     * Context对象销毁了
     */

}
