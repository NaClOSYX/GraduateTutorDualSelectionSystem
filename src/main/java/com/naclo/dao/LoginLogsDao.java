package com.naclo.dao;

import com.naclo.pojo.LoginLogs;

import java.sql.Connection;
import java.util.List;


public interface LoginLogsDao {
    List<LoginLogs> getAllLoginLogs(Connection connection);

    int insertLoginLogs(Connection connection, LoginLogs loginLogs);
}
