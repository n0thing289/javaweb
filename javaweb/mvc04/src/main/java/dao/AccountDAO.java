package dao;

import bean.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.*;

/**
 * 属于mvc中的model层
 * 属于三层架构中的持久化层：dao
 */
public class AccountDAO {
    /**
     * 向数据库插入一条account数据
     *
     * @param account
     * @return
     */
    public int insert(Account account) {

        PreparedStatement ps = null;
        int affectRows = 0;
        try {
            //获得连接
            Connection conn = JDBCUtil.getConnection();
            //编译sql
            String sql = "insert into t_act values(?,?,?);";
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setLong(1, account.getId());
            ps.setString(2, account.getActno());
            ps.setDouble(3, account.getBalance());
            //执行
            affectRows += ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return affectRows;
    }


    /**
     * 通过账户id来删除这条数据
     *
     * @param actNo
     * @return
     */
    public int deleteByActNo(String actNo) {

        PreparedStatement ps = null;
        int affectRows = 0;
        try {
            //获得连接
            Connection conn = JDBCUtil.getConnection();
            //编译sql
            String sql = "delete from t_act where actno = ?;";
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setString(1, actNo);
            //execute
            affectRows += ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return affectRows;
    }


    /**
     * 通过account的账号， 新的account数据替换旧的account数据
     *
     * @param account
     * @return
     */
    public int update(Account account) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        int affectRows = 0;
        try {
            //获得连接
            Connection conn = JDBCUtil.getConnection();
            //编译sql
            String sql = "update t_act set balance = ? where actno = ?;";
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setDouble(1, account.getBalance());
            ps.setString(2, account.getActno());
            //execute
            affectRows += ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(null, ps, null);
        }
        return affectRows;
    }


    /**
     * 通过actno查询一条数据
     *
     * @param actNo
     * @return
     */
    public Account selectByActNo(String actNo) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            //获得连接
            Connection conn = JDBCUtil.getConnection();
            //编译sql
            String sql = "select id, actno, balance from t_act where actno = ?;";
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setString(1, actNo);
            //execute
            rs = ps.executeQuery();
            //解析结果集
            if (rs.next()) {
                Long id = rs.getLong("id");
                String actno = rs.getString("actno");
                Double balance = rs.getDouble("balance");
                account = new Account(id, actno, balance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, null);
        }
        return account;
    }


    /**
     * 查询所有的账户数据
     *
     * @return
     */
    public List<Account> selectAll() {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Account> accounts = new ArrayList<>();
        try {
            //获得连接
            Connection conn = JDBCUtil.getConnection();
            //编译sql
            String sql = "select id,actno,balance from t_act;";
            ps = conn.prepareStatement(sql);
            //execute
            rs = ps.executeQuery();
            //解析结果集
            while (rs.next()) {
                Long id = rs.getLong("id");
                String actno = rs.getString("actno");
                Double balance = rs.getDouble("balance");
                //封装数据
                Account account = new Account(id, actno, balance);
                accounts.add(account);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close(rs, ps, null);
        }
        return accounts;
    }


}
