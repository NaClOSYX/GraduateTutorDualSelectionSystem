package com.naclo.dao;


import com.naclo.pojo.Teacher;

import java.sql.Connection;
import java.util.List;

public interface TeacherDao {
    //获取所有导师
    List<Teacher> queryAllTeachers(Connection connection);

    //根据id查询导师
    Teacher queryTeacherById(Connection connection, String id);

    //根据专业查询导师
    List<Teacher> queryTeacherByMajor(Connection connection, String major);

    //根据姓名查询导师
    List<Teacher> queryTeacherByName(Connection connection, String name);

    //获取所有导师分页
    List<Teacher> queryAllTeachersLimit(Connection connection, int startIndex, int pageSize);

    //插入导师
    int insertTeacher(Connection connection, Teacher teacher);

    //根据id修改导师密码
    int updateTeacherPasswordById(Connection connection, String id, String newPwd);

    //修改导师信息
    int updateTeacherById(Connection connection, Teacher teacher);

    //根据id删除导师
    int deleteTeacherById(Connection connection, String id);
}
