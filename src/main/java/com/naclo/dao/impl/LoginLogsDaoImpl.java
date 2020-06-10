package com.naclo.dao.impl;


import com.naclo.dao.LoginLogsDao;
import com.naclo.pojo.LoginLogs;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginLogsDaoImpl implements LoginLogsDao {

    @Override
    public List<LoginLogs> getAllLoginLogs(Connection connection) {
        List<LoginLogs> loginLogsList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from loginlogs";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    LoginLogs loginLogs = new LoginLogs();
                    loginLogs.setLogId(rs.getInt(1));
                    loginLogs.setUserId(rs.getString(2));
                    loginLogs.setUserRole(rs.getString(3));
                    loginLogs.setUserOp(rs.getString(4));
                    loginLogs.setOpTime(rs.getTimestamp(5));
                    loginLogs.setOpIp(rs.getString(6));
                    loginLogsList.add(loginLogs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return loginLogsList;
    }

    @Override
    public List<LoginLogs> getAllLoginLogsLimit(Connection connection, int offset, int limit, String search) {
        List<LoginLogs> loginLogsList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "";
            Object[] params = new Object[]{};
            if (null == search || "".equals(search)) {
                sql = "select * from loginlogs limit ?,?";
                params = new Object[]{offset, limit};
            } else {
                search = "%" + search + "%";
                sql = "select * from loginlogs  where userId like ?  limit ?,?";
                params = new Object[]{search, offset, limit};
            }
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    LoginLogs loginLogs = new LoginLogs();
                    loginLogs.setLogId(rs.getInt(1));
                    loginLogs.setUserId(rs.getString(2));
                    loginLogs.setUserRole(rs.getString(3));
                    loginLogs.setUserOp(rs.getString(4));
                    loginLogs.setOpTime(rs.getTimestamp(5));
                    loginLogs.setOpIp(rs.getString(6));
                    loginLogsList.add(loginLogs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return loginLogsList;
    }

    @Override
    public int getAllLoginLogsLimitCount(Connection connection, int offset, int limit, String search) {
        int count = 0;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "";
            Object[] params = new Object[]{};
            if (null == search || "".equals(search)) {
                sql = "select count(*) from loginlogs";
                params = new Object[]{};
            } else {
                search = "%" + search + "%";
                sql = "select count(*) from loginlogs  where userId like ?";
                params = new Object[]{search};
            }
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return count;
    }

    @Override
    public int insertLoginLogs(Connection connection, LoginLogs loginLogs) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "insert into loginlogs values(null,?,?,?,?,?);";
            Object[] params = {loginLogs.getUserId(), loginLogs.getUserRole(), loginLogs.getUserOp(), loginLogs.getOpTime(), loginLogs.getOpIp()};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }
}
