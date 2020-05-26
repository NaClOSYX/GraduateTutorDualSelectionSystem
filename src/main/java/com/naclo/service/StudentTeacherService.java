package com.naclo.service;

import com.naclo.pojo.StudentTeacher;

import java.util.List;


public interface StudentTeacherService {
    //获取所有学生导师对
    List<StudentTeacher> getAllStudentTeacher();

    //根据专业获取所有学生导师对
    List<StudentTeacher> getAllStudentTeacherByMajor(String majorName);

}
