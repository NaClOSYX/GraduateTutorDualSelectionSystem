package com.naclo.service.impl;


import com.naclo.pojo.Student;
import com.naclo.service.StudentService;
import org.junit.jupiter.api.Test;


class StudentServiceImplTest {
    StudentService studentService = new StudentServiceImpl();

    @Test
    void queryStudentByIdTest() {
        Student student = studentService.queryStudentById("20171422");
        System.out.println(student);
    }

    @Test
    void queryStudentByMajorTest() {
        studentService.queryStudentByMajor("软件工程").forEach(System.out::println);
    }

    @Test
    void queryAllStudentsTest() {
        studentService.queryAllStudents().forEach(System.out::println);
    }

    @Test
    void queryAllStudentsLimitTest() {
        studentService.queryAllStudentsLimit(1, 2).forEach(System.out::println);
        studentService.queryAllStudentsLimit(2, 2).forEach(System.out::println);
        studentService.queryAllStudentsLimit(3, 3).forEach(System.out::println);
        studentService.queryAllStudentsLimit(1, 5).forEach(System.out::println);
        studentService.queryAllStudentsLimit(2, 5).forEach(System.out::println);
        studentService.queryAllStudentsLimit(3, 5).forEach(System.out::println);
    }

    @Test
    void insertStudentTest() {
        Student student = new Student("20170000", "阿三", null, "软件工程");
        boolean flag = studentService.insertStudent(student);
        if (flag) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    void updateStudentPasswordByIdTest() {
        boolean flag = studentService.updateStudentPasswordById("20171422", "20171422");
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void updateStudentByIdTest() {
        Student student = new Student("20171422", "史杨霄11", null, "电气工程");
        boolean flag = studentService.updateStudentById(student);
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void deleteStudentByIdTest() {
        boolean flag = studentService.deleteStudentById("20171422");
        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

}