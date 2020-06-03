package com.naclo.service.impl;


import com.naclo.pojo.IdeaView;
import com.naclo.service.IdeaViewService;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IdeaViewServiceImplTest {
    IdeaViewService ideaViewService = new IdeaViewServiceImpl();

    @Test
    void queryIdeasTest() {
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas(null, null, null, -1);
        ideaViewList.forEach(System.out::println);
    }

    @Test
    void queryIdeasTest1() {
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas("20171422", "1234567890", "软件工程", 2);
        ideaViewList.forEach(System.out::println);
    }

    @Test
    void queryIdeasTest2() {
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas(null, "1234567890", "软件工程", 1);
        ideaViewList.forEach(System.out::println);
    }

    @Test
    void queryIdeasTest3() {
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas("20171422", null, "软件工程", 1);
        ideaViewList.forEach(System.out::println);
    }
}
