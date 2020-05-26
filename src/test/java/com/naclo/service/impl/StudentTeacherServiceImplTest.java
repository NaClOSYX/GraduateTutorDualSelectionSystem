package com.naclo.service.impl;


import com.naclo.service.StudentTeacherService;
import org.junit.jupiter.api.Test;

public class StudentTeacherServiceImplTest {
    StudentTeacherService studentTeacherService = new StudentTeacherServiceImpl();

    @Test
    void getAllStudentTeacherDaoTest() {
        studentTeacherService.getAllStudentTeacher().forEach(System.out::println);
    }

    @Test
    void getAllStudentTeacherDaoByMajorTest() {
        studentTeacherService.getAllStudentTeacherByMajor("软件工程").forEach(System.out::println);
    }
}
