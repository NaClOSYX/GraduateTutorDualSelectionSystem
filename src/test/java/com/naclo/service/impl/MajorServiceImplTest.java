package com.naclo.service.impl;


import com.naclo.pojo.Major;
import com.naclo.service.MajorService;
import org.junit.jupiter.api.Test;

class MajorServiceImplTest {
    MajorService majorService = new MajorServiceImpl();


    @Test
    void queryAllStudentsTest() {
        majorService.queryAllMajors().forEach(System.out::println);
    }

    @Test
    void queryAllMajorsExceptALLTest() {
        majorService.queryAllMajorsExceptALL().forEach(System.out::println);
    }

    @Test
    void queryMajorByNameTest() {
        majorService.queryMajorByName("软件工程").forEach(System.out::println);
    }

    @Test
    void queryMajorByIdTest() {
        System.out.println(majorService.queryMajorById(1));
    }

    @Test
    void queryMajorMaxStudentsTest() {
        System.out.println(majorService.queryMajorMaxStudents("软件工程"));
    }

    @Test
    void insertMajorTest() {
        Major major = new Major(0, "aaa", 20);
        boolean flag = majorService.insertMajor(major);
        if (flag) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    void updateMajorTest() {
        Major major = new Major(10, "aaaaaa", 20);
        boolean flag = majorService.updateMajor(major);
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }

    @Test
    void deleteMajorByIdTest() {
        Major major = new Major(0, "aaa", 20);
        boolean flag = majorService.deleteMajorById(10);
        if (flag) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }
}