package com.naclo.service;

import com.naclo.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    //获取所有导师
    List<Teacher> queryAllTeachers();

    //根据id查询导师
    Teacher queryTeacherById(String id);

    //根据专业查询导师
    List<Teacher> queryTeacherByMajor(String major);

    //根据专业获取所有导师Map
    Map<String, String> queryTeachersMapByMajor(String major);


    //根据姓名查询导师
    List<Teacher> queryTeacherByName(String name);

    //获取所有导师分页
    List<Teacher> queryAllTeachersLimit(int startIndex, int pageSize);

    //插入导师
    boolean insertTeacher(Teacher teacher);

    //根据id修改导师密码
    boolean updateTeacherPasswordById(String id, String newPwd);

    //修改导师信息
    boolean updateTeacherById(Teacher teacher);

    //根据id删除导师
    boolean deleteTeacherById(String id);
}
