package com.naclo.dao;


import com.naclo.pojo.StudentTeacher;

import java.sql.Connection;
import java.util.List;

public interface StudentTeacherDao {
    //获取所有学生导师对
    List<StudentTeacher> getAllStudentTeacher(Connection connection);

    //根据专业获取所有学生导师对
    List<StudentTeacher> getAllStudentTeacherByMajor(Connection connection, String majorName);
}
