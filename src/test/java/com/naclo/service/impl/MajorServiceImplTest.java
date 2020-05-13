package com.naclo.service.impl;


import com.naclo.pojo.Major;
import com.naclo.pojo.Student;
import com.naclo.service.MajorService;
import com.naclo.service.StudentService;
import org.junit.jupiter.api.Test;

import java.util.List;

class MajorServiceImplTest {
    MajorService majorService = new MajorServiceImpl();


    @Test
    void queryAllStudentsTest() {
        List<Major> majorList = majorService.queryAllMajors();
        majorList.forEach(System.out::println);
    }
}