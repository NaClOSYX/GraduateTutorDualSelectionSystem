package com.naclo.service;

import com.naclo.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    //获取所有导师
    List<Teacher> queryAllTeachers();

    //根据id查询学生
    Teacher queryTeacherById(String id);

    //根据专业查询学生
    List<Teacher> queryTeacherByMajor(String major);

    //根据姓名查询学生
    List<Teacher> queryTeacherByName(String name);

    //获取所有学生分页
    List<Teacher> queryAllTeachersLimit(int startIndex, int pageSize);

    //插入学生
    boolean insertTeacher(Teacher teacher);

    //根据id修改学生密码
    boolean updateTeacherPasswordById(String id, String newPwd);

    //修改学生信息
    boolean updateTeacherById(Teacher teacher);

    //根据id删除学生
    boolean deleteTeacherById(String id);
}
