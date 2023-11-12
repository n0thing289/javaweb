package servlet;

import exceptions.AppException;
import exceptions.NotEnoughMoneyException;
import service.AccountService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

/**
 * 属于mvc中的controller层
 * 属于三层架构中的表现层：servlet
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {
    //创建一个AccountService对象,专门用来处理业务
    private final AccountService accountService = new AccountService();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //接收转账表单的数据
        String fromActNo = request.getParameter("fromActNo");
        String toActNo = request.getParameter("toActNo");
        double money = Double.parseDouble(request.getParameter("money"));
        try {
            //使用AccountService 完成转账业务
            accountService.transfer(fromActNo, toActNo, money);
            //执行到这里说明转账成功
            //将转账结果展示
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (NotEnoughMoneyException e) {
            //执行到这里说明转账失败了
            //展示处理结果
            response.sendRedirect(request.getContextPath() + "/notenoughmoney.jsp");
        } catch (AppException e) {
            //执行到这里说明转账失败了
            //展示处理结果
            response.sendRedirect(request.getContextPath() + "/apperror.jsp");
        }

    }
}
