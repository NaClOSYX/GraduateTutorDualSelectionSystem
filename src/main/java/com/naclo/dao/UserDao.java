package com.naclo.dao;

import com.naclo.pojo.User;

import java.sql.Connection;


public interface UserDao {
    //根据id查询用户（学生/老师/管理员）
    User queryUserById(Connection connection, String id);
}
