package com.naclo.dao;

import com.naclo.pojo.Student;
import com.naclo.pojo.User;

import java.sql.Connection;
import java.util.List;


public interface StudentDao {
    //根据id查询学生
    Student queryStudentById(Connection connection, String id);

    //根据专业查询学生
    List<Student> queryStudentByMajor(Connection connection, String major);

    //获取所有学生
    List<Student> queryAllStudents(Connection connection);

    //获取所有学生分页
    List<Student> queryAllStudentsLimit(Connection connection, int startIndex, int pageSize);

    //插入学生
    int insertStudent(Connection connection, Student student);

    //根据id修改学生密码
    int updateStudentPasswordById(Connection connection, String id, String newPwd);

    //修改学生信息
    int updateStudentById(Connection connection, Student student);

    //根据id删除学生
    int deleteStudentById(Connection connection, String id);
}
