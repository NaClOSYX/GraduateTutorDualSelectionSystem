package com.naclo.service;

import com.naclo.pojo.User;


public interface UserService {
    //根据id查询用户（学生/老师/管理员）
    User queryUserById(String id);
}
