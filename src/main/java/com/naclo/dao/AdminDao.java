package com.naclo.dao;


import com.naclo.pojo.Admin;

import java.sql.Connection;
import java.util.List;

public interface AdminDao {
    //获取所有管理员
    List<Admin> queryAllAdmins(Connection connection);

    //获取所有非研究生院管理员
    List<Admin> queryAllAdminsExceptAll(Connection connection);

    //根据id查询管理员
    Admin queryAdminsByAdminId(Connection connection, String adminId);

    //根据id查询管理员
    List<Admin> queryAdminsByMajor(Connection connection, String major);

    //插入管理员
    int insertAdmin(Connection connection, Admin admin);

    //根据id修改管理员
    int updateAdminPasswordById(Connection connection, String id, String newPwd);

    //修改管理员信息
    int updateAdminById(Connection connection, Admin admin);

    //根据id删除管理员
    int deleteAdminById(Connection connection, String id);
}
