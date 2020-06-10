package com.naclo.service;

import com.naclo.pojo.LoginLogs;

import java.util.List;

/**
 * @Author NaClO
 * @create 2020/6/10 18:54
 */
public interface LoginLogsService {
    List<LoginLogs> getAllLoginLogs();

    boolean insertLoginLogs(LoginLogs loginLogs);
}
