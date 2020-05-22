package com.naclo.service.impl;

import com.naclo.service.IdeaTableService;
import org.junit.jupiter.api.Test;


public class IdeaTableServiceTest {
    IdeaTableService ideaTableService = new IdeaTableServiceImpl();

    @Test
    void name() {
        ideaTableService.queryStudentIdeasByMajor("软件工程").forEach(System.out::println);
    }
}
