package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.*;
import java.sql.*;

import exceptions.AppException;
import exceptions.NotEnoughBalanceException;
import utils.*;

@WebServlet({"/transfer"})
public class TransferServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //默认设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取表单的参数
        String fromActNo = request.getParameter("fromActNo");
        String toActNo = request.getParameter("toActNo");
        double money = Double.parseDouble(request.getParameter("money"));
        //连接数据库,完成转账
        //1. 判断自己有没有这么多钱
        //2. 进行转账,修改数据
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        int affectRows = 0;
        try {
            conn = JDBCUtils.getConnection();
//            out.println("TransferServlet-30:" + conn);
            String verify_sql = "select balance from t_act where actno = ?";
            ps1 = conn.prepareStatement(verify_sql);
            ps1.setString(1, fromActNo);
            rs = ps1.executeQuery();

            if(rs.next()){
                double fromActNo_balance = Double.parseDouble(rs.getString("balance"));
//                System.out.println(fromActNo + "的余额: " + fromActNo_balance);
                // 判断自己的余额和要转出金额是否合理
                if(money > fromActNo_balance){
                    throw new NotEnoughBalanceException("对不起,余额不足");
                }
                //走到这里可以进行转账
                //自己的钱减少
                conn.setAutoCommit(false);//设置不自动提交sql语句
                String sql1 = "update t_act set balance = balance - ? where actno = ?";
                ps2 = conn.prepareStatement(sql1);
                ps2.setDouble(1, money);
                ps2.setString(2, fromActNo);
                affectRows += ps2.executeUpdate();
                //对方的钱增加
                String sql2 = "update t_act set balance = balance + ? where actno = ?";
                ps3 = conn.prepareStatement(sql2);
                ps3.setDouble(1, money);
                ps3.setString(2,toActNo);
                affectRows += ps3.executeUpdate();

                if(affectRows != 2){
                    throw new AppException("App异常,请联系管理员");
                }
                conn.commit();
                out.println("<h1>转账成功!</h1>");
            }

        } catch (Exception e) {
            //只要有异常就回滚
            try {
                if(conn != null){
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            out.println(e.getMessage());
        } finally {
            JDBCUtils.close(rs, ps1, conn);
            JDBCUtils.close(null, ps2, null);
            JDBCUtils.close(null, ps3, null);
        }
    }
}
