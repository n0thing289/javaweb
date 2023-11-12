package dao;

import pojo.Account;

import java.util.List;

public interface AccountDao {
    int insert(Account account);
    int deleteByActNo(String actNo);
    int update(Account account);
    Account selectByActNo(String actNo);
    List<Account> selectAll();
}
