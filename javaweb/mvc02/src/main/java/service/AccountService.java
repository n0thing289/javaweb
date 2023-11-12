package service;

import bean.Account;
import dao.AccountDAO;
import exceptions.AppException;
import exceptions.NotEnoughMoneyException;

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
        count += accountDAO.update(toAct);
        //
        if (count != 2) {
            throw new AppException("账户转账异常");
        }
    }
}
