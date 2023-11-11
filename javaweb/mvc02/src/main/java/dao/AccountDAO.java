package dao;

import bean.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import utils.*;

/**
 *
 */
public class AccountDAO {
    /**
     * 向数据库插入一条account数据
     *
     * @param account
     * @return
     */
    public int insert(Account account) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into t_act values(?,?,?)";
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return 0;
    }

    /**
     * 通过账户id来删除这条数据
     *
     * @param actNo
     * @return
     */
    public int deleteByActNo(String actNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return 0;
    }

    /**
     * 通过account的账号， 新的account数据替换旧的account数据
     *
     * @param account
     * @return
     */
    public int update(Account account) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return 0;
    }

    /**
     * 通过actno查询一条数据
     *
     * @param actNo
     * @return
     */
    public Account selectByActNo(String actNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return null;
    }

    /**
     * 查询所有的账户数据
     *
     * @return
     */
    public List<Account> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return null;
    }
}
