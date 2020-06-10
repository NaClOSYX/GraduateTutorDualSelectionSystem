package com.naclo.service.impl;

import com.naclo.dao.LoginLogsDao;
import com.naclo.dao.impl.LoginLogsDaoImpl;
import com.naclo.pojo.LoginLogs;
import com.naclo.service.LoginLogsService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Author NaClO
 * @create 2020/6/10 18:54
 */
public class LoginLogsServiceImpl implements LoginLogsService {
    LoginLogsDao loginLogsDao = new LoginLogsDaoImpl();

    @Override
    public List<LoginLogs> getAllLoginLogs() {
        Connection connection = DBUtil.getConnection();
        List<LoginLogs> loginLogsList = loginLogsDao.getAllLoginLogs(connection);
        DBUtil.closeResource(connection, null, null);
        return loginLogsList;
    }

    @Override
    public boolean insertLoginLogs(LoginLogs loginLogs) {
        Connection connection = DBUtil.getConnection();
        int i = loginLogsDao.insertLoginLogs(connection, loginLogs);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
