package com.naclo.service.impl;


import com.naclo.service.MajorService;
import org.junit.jupiter.api.Test;

class MajorServiceImplTest {
    MajorService majorService = new MajorServiceImpl();


    @Test
    void queryAllStudentsTest() {
        majorService.queryAllMajors().forEach(System.out::println);
    }

    @Test
    void queryMajorByNameTest() {
        majorService.queryMajorByName("软件工程").forEach(System.out::println);
    }
}