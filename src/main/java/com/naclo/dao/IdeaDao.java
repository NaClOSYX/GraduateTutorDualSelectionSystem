package com.naclo.dao;


import com.naclo.pojo.Idea;

import java.sql.Connection;
import java.util.List;

public interface IdeaDao {
    //获取所有志愿
    List<Idea> queryAllIdeas(Connection connection);

    //根据学号获取志愿
    List<Idea> queryIdeasByStudentId(Connection connection, String studentId);

    //根据学号获取志愿数量
    int queryIdeasByStudentIdCount(Connection connection, String studentId);

    //根据工号获取志愿
    List<Idea> queryIdeasByTeacherId(Connection connection, String teacherId);

    //根据工号获取志愿数量
    int queryIdeasByTeacherIdCount(Connection connection, String teacherId);

    //根据专业获取志愿
    List<Idea> queryIdeasByMajor(Connection connection, String major);

    //新增志愿
    int insertIdea(Connection connection, Idea Idea);
}
