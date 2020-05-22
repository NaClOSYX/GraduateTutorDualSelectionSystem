package com.naclo.service.impl;


import com.naclo.pojo.Teacher;
import com.naclo.service.TeacherService;
import org.junit.jupiter.api.Test;

import java.util.Map;

class TeacherServiceImplTest {
    TeacherService teacherService = new TeacherServiceImpl();

    @Test
    void queryAllTeachersTest() {
        teacherService.queryAllTeachers().forEach(System.out::println);
    }

    @Test
    void queryTeacherByIdTest() {
        Teacher teacher = teacherService.queryTeacherById("1234567890");
        System.out.println(teacher);
    }

    @Test
    void queryTeacherByMajorTest() {
        teacherService.queryTeacherByMajor("软件工程").forEach(System.out::println);
    }

    @Test
    void queryTeachersMapByMajorTest() {
        Map<String, String> teacherMap = teacherService.queryTeachersMapByMajor("ALL");
        for (String key : teacherMap.keySet()) {
            System.out.println(key + "--" + teacherMap.get(key));
        }
    }

    @Test
    void queryTeacherByNameTest() {
        teacherService.queryTeacherByName("周平").forEach(System.out::println);
    }

    @Test
    void queryAllTeachersLimitTest() {
        teacherService.queryAllTeachersLimit(1, 2).forEach(System.out::println);
    }

    @Test
    void insertTeacherTest() {
        Teacher teacher = new Teacher("1212121212", "三三", null, "软件工程", "123435345");
        boolean flag = teacherService.insertTeacher(teacher);
        if (flag) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    void updateTeacherPasswordByIdTest() {
        boolean flag = teacherService.updateTeacherPasswordById("1212121212", "12345678");
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void updateTeacherByIdTest() {
        Teacher teacher = new Teacher("1212121212", "三三", null, "计算机技术", "123435345sgheheheheh");
        boolean flag = teacherService.updateTeacherById(teacher);
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void deleteTeacherByIdTest() {
        boolean flag = teacherService.deleteTeacherById("1212121212");
        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
}