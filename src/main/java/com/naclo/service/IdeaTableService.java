package com.naclo.service;


import com.naclo.pojo.IdeaTable;

import java.util.List;

public interface IdeaTableService {
    List<IdeaTable> queryStudentIdeasByMajor(String major);
}
