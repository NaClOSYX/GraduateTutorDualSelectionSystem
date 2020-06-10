package com.naclo.dao;

import com.naclo.pojo.LoginLogs;

import java.sql.Connection;
import java.util.List;


public interface LoginLogsDao {
    List<LoginLogs> getAllLoginLogs(Connection connection);

    List<LoginLogs> getAllLoginLogsLimit(Connection connection, int offset, int limit, String search);

    int getAllLoginLogsLimitCount(Connection connection, int offset, int limit, String search);

    int insertLoginLogs(Connection connection, LoginLogs loginLogs);
}
