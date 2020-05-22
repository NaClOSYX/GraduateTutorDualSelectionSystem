package com.naclo.service.impl;


import com.naclo.dao.AdminDao;
import com.naclo.dao.impl.AdminDaoImpl;
import com.naclo.pojo.Admin;
import com.naclo.service.AdminService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    AdminDao adminDao = new AdminDaoImpl();

    @Override
    public List<Admin> queryAllAdmins() {
        Connection connection = DBUtil.getConnection();
        List<Admin> adminList = adminDao.queryAllAdmins(connection);
        DBUtil.closeResource(connection, null, null);
        return adminList;
    }

    @Override
    public List<Admin> queryAllAdminsExceptAll() {
        Connection connection = DBUtil.getConnection();
        List<Admin> adminList = adminDao.queryAllAdminsExceptAll(connection);
        DBUtil.closeResource(connection, null, null);
        return adminList;
    }

    @Override
    public Admin queryAdminsByAdminId(String adminId) {
        Connection connection = DBUtil.getConnection();
        Admin admin = adminDao.queryAdminsByAdminId(connection, adminId);
        DBUtil.closeResource(connection, null, null);
        return admin;
    }

    @Override
    public List<Admin> queryAdminsByMajor(String major) {
        Connection connection = DBUtil.getConnection();
        List<Admin> adminList = adminDao.queryAdminsByMajor(connection, major);
        DBUtil.closeResource(connection, null, null);
        return adminList;
    }

    @Override
    public boolean insertAdmin(Admin admin) {
        Connection connection = DBUtil.getConnection();
        int i = adminDao.insertAdmin(connection, admin);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateAdminPasswordById(String id, String newPwd) {
        Connection connection = DBUtil.getConnection();
        int i = adminDao.updateAdminPasswordById(connection, id, newPwd);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateAdminById(Admin admin) {
        Connection connection = DBUtil.getConnection();
        int i = adminDao.updateAdminById(connection, admin);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteAdminById(String id) {
        Connection connection = DBUtil.getConnection();
        int i = adminDao.deleteAdminById(connection, id);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
