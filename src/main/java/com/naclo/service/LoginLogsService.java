package com.naclo.service;

import com.naclo.pojo.LoginLogs;

import java.util.List;

/**
 * @Author NaClO
 * @create 2020/6/10 18:54
 */
public interface LoginLogsService {
    List<LoginLogs> getAllLoginLogs();

    List<LoginLogs> getAllLoginLogsLimit(int offset, int limit, String search);

    int getAllLoginLogsLimitCount(int offset, int limit, String search);

    boolean insertLoginLogs(LoginLogs loginLogs);
}
