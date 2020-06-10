package com.naclo.service.impl;

import com.naclo.pojo.LoginLogs;
import com.naclo.service.LoginLogsService;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @Author NaClO
 * @create 2020/6/10 18:56
 */
public class LoginLogsServiceImplTest {
    LoginLogsService loginLogsService = new LoginLogsServiceImpl();

    @Test
    void getAllLoginLogsTest() {
        loginLogsService.getAllLoginLogs().forEach(System.out::println);
    }

    @Test
    void getAllLoginLogsLimitTest() {
        loginLogsService.getAllLoginLogsLimit(10, 10, "").forEach(System.out::println);
    }

    @Test
    void getAllLoginLogsLimitCountTest() {
        int count = loginLogsService.getAllLoginLogsLimitCount(0, 10, "");
        System.out.println("count = " + count);
    }

    @Test
    void insertLoginLogsTest() {
        LoginLogs loginLogs = new LoginLogs(0, "1234567890", "教师", "登陆", new Date(), "127.0.0.1");
        boolean flag = loginLogsService.insertLoginLogs(loginLogs);
        if (flag) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }
}
