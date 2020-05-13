package com.naclo.service;

import com.naclo.pojo.User;

import java.sql.Connection;

/**
 * @Author NaClO
 * @create 2020/5/7 13:50
 */
public interface UserService {
    //根据id查询用户（学生/老师/管理员）
    User queryUserById(String id);
}
