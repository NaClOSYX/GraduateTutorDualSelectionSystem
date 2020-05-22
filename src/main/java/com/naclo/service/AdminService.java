package com.naclo.service;


import com.naclo.pojo.Admin;

import java.util.List;

public interface AdminService {
    //获取所有管理员
    List<Admin> queryAllAdmins();

    //获取所有非研究生院管理员
    List<Admin> queryAllAdminsExceptAll();

    //根据id查询管理员
    Admin queryAdminsByAdminId(String adminId);

    //根据id查询管理员
    List<Admin> queryAdminsByMajor(String major);

    //插入管理员
    boolean insertAdmin(Admin admin);

    //根据id修改管理员
    boolean updateAdminPasswordById(String id, String newPwd);

    //修改管理员信息
    boolean updateAdminById(Admin admin);

    //根据id删除管理员
    boolean deleteAdminById(String id);
}
