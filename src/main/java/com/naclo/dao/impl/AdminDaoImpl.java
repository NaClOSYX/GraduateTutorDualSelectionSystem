package com.naclo.dao.impl;


import com.naclo.dao.AdminDao;
import com.naclo.pojo.Admin;
import com.naclo.utils.DBUtil;
import com.naclo.utils.MD5Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    @Override
    public List<Admin> queryAllAdmins(Connection connection) {
        List<Admin> adminList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from admin";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getString(1));
                    admin.setAdminPassword(rs.getString(2));
                    admin.setAdminMajor(rs.getString(3));
                    adminList.add(admin);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return adminList;
    }

    @Override
    public List<Admin> queryAllAdminsExceptAll(Connection connection) {
        List<Admin> adminList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from admin where adminMajor <>'ALL'";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getString(1));
                    admin.setAdminPassword(rs.getString(2));
                    admin.setAdminMajor(rs.getString(3));
                    adminList.add(admin);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return adminList;
    }

    @Override
    public Admin queryAdminsByAdminId(Connection connection, String adminId) {
        Admin admin = new Admin();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from admin where adminId=?";
            Object[] params = new Object[]{adminId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    admin.setAdminId(rs.getString(1));
                    admin.setAdminPassword(rs.getString(2));
                    admin.setAdminMajor(rs.getString(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return admin;
    }

    @Override
    public List<Admin> queryAdminsByMajor(Connection connection, String major) {
        List<Admin> adminList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from admin where adminMajor=?";
            Object[] params = new Object[]{major};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getString(1));
                    admin.setAdminPassword(rs.getString(2));
                    admin.setAdminMajor(rs.getString(3));
                    adminList.add(admin);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return adminList;
    }

    @Override
    public int insertAdmin(Connection connection, Admin admin) {
        String password = MD5Util.stringToMD5(admin.getAdminId());
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "insert into admin values(?,?,?);";
            Object[] params = {admin.getAdminId(), password, admin.getAdminMajor()};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }

    @Override
    public int updateAdminPasswordById(Connection connection, String id, String newPwd) {
        String newPassword = MD5Util.stringToMD5(newPwd);
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update admin set adminPassword=? where adminId=?";
            Object[] params = {newPassword, id};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }

    @Override
    public int updateAdminById(Connection connection, Admin admin) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update admin set adminMajor=? where adminId=?";
            Object[] params = {admin.getAdminMajor(), admin.getAdminId()};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }

    @Override
    public int deleteAdminById(Connection connection, String id) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "delete from admin where adminId=?";
            Object[] paramas = {id};
            try {
                flag = DBUtil.execute(connection, sql, pstm, paramas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }
}
