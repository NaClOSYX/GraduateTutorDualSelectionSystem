package com.naclo.service;

import com.naclo.pojo.Student;

import java.util.List;


public interface StudentService {
    //根据id查询学生
    Student queryStudentById(String id);

    //根据专业查询学生
    List<Student> queryStudentByMajor(String major);

    //获取所有学生
    List<Student> queryAllStudents();

    //获取所有学生分页
    List<Student> queryAllStudentsLimit(int startIndex, int pageSize);

    //插入学生
    boolean insertStudent(Student student);

    //根据id修改学生密码
    boolean updateStudentPasswordById(String id, String newPwd);

    //修改学生信息
    boolean updateStudentById(Student student);

    //根据id删除学生
    boolean deleteStudentById(String id);
}
