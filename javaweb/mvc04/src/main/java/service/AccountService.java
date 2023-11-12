package service;

import bean.Account;
import dao.AccountDAO;
import exceptions.AppException;
import exceptions.NotEnoughMoneyException;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 属于mvc中的model层
 * 属于三层架构中的业务逻辑层：service
 */
public class AccountService {
    //创建一个AccountDAO对象,专门用来使用数据库crud操作
    private final AccountDAO accountDAO = new AccountDAO();

    /**
     * 完成转账业务
     *
     * @param fromActNo
     * @param toActNo
     * @param money
     */
    public void transfer(String fromActNo, String toActNo, double money) throws NotEnoughMoneyException, AppException {
        //动态+动态代理才能让service不写jdbc代码,(现在的事务还是需要在service里写一点jdbc代码)
        Connection conn = JDBCUtil.getConnection();
        try {
            //开启事务
            conn.setAutoCommit(false);

            //1. 检查余额是否充足
            //调用AccountDAO查询转出账户的余额
            Account fromAct = accountDAO.selectByActNo(fromActNo);
            Double balance = fromAct.getBalance();
            if (money > balance) {
                //转出金额大于余额,不能转出
                throw new NotEnoughMoneyException("对不起,余额不足");
            }
            //走到这里说明可以转账
            //拿到对方账户对象
            Account toAct = accountDAO.selectByActNo(toActNo);
            //转出账户的余额减少, 转入账户的余额增加
            fromAct.setBalance(balance - money);
            toAct.setBalance(toAct.getBalance() + money);
            //修改数据库中数据
            int count = accountDAO.update(fromAct);
            //模拟异常
//            int i = 1 / 0;
            count += accountDAO.update(toAct);
            //
            if (count != 2) {
                throw new AppException("账户转账异常");
            }
            //提交事务
            conn.commit();
        } catch (SQLException e) {
            //回滚事务
            /**
             * 但是现在的问题是, 这里的conn并不是dao中方法里的conn,不是同一个conn,那么这里开启的事务无法作用于dao update方法
             *
             * 解决方法:
             * 1. 可以把dao中的每个方法,传入Connection对象,就可以保证与service中的Connection对象是同一个,事务就可以起作用
             *
             * 一个线程就是一个栈
             */
        } finally {
            JDBCUtil.close(null, null, conn);
        }
    }
}
