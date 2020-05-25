package com.naclo.service.impl;


import com.naclo.pojo.Idea;
import com.naclo.service.IdeaService;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class IdeaServiceImplTest {
    IdeaService ideaService = new IdeaServiceImpl();

    @Test
    void queryAllIdeasTest() {
        ideaService.queryAllIdeas().forEach(System.out::println);
    }

    @Test
    void queryIdeasByStudentIdTest() {
        ideaService.queryIdeasByStudentId("20171422").forEach(System.out::println);
    }

    @Test
    void queryIdeasByStudentIdCountTest() {
        System.out.println(ideaService.queryIdeasByStudentIdCount("20171422"));
    }

    @Test
    void queryIdeasByTeacherIdTest() {
        ideaService.queryIdeasByTeacherId("1234567890").forEach(System.out::println);
    }

    @Test
    void queryIdeasByTeacherIdCountTest() {
        System.out.println(ideaService.queryIdeasByTeacherIdCount("1234567890"));
    }

    @Test
    void queryIdeasByMajorTest() {
        ideaService.queryIdeasByMajor("软件工程").forEach(System.out::println);
    }

    @Test
    void queryIdeasByTeacherIdDecidedTest() {
        ideaService.queryIdeasByTeacherIdDecided("1234567890").forEach(System.out::println);
    }

    @Test
    void insertIdeaTest() {
        Idea idea = new Idea(0, "软件工程", "20170004", "1234567890", new Date(), 1);
        System.out.println(idea);
        boolean flag = ideaService.insertIdea(idea);
        if (flag) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    @Test
    void updateIdeaStateByIdTest() {
        boolean flag = ideaService.updateIdeaStateById("20171422", "1234567890", 2);
        if (flag) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
