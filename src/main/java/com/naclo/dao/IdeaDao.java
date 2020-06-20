package com.naclo.dao;


import com.naclo.pojo.Idea;

import java.sql.Connection;
import java.util.List;

public interface IdeaDao {
    //获取所有志愿
    List<Idea> queryAllIdeas(Connection connection);

    //根据志愿号获取志愿
    Idea queryIdeasByIdeaId(Connection connection, String ideaId);

    //根据学号获取志愿
    List<Idea> queryIdeasByStudentId(Connection connection, String studentId);

    //根据学号获取志愿数量
    int queryIdeasByStudentIdCount(Connection connection, String studentId);

    //根据学号获取老师确定志愿
    Idea queryTeacherByStudentId(Connection connection, String studentId);

    //根据工号获取志愿
    List<Idea> queryIdeasByTeacherId(Connection connection, String teacherId);

    //根据工号获取志愿数量
    int queryIdeasByTeacherIdCount(Connection connection, String teacherId);

    //根据工号获取确定的志愿
    List<Idea> queryIdeasByTeacherIdDecided(Connection connection, String teacherId);

    //根据工号获取管理员确定的志愿
    List<Idea> queryIdeasByTeacherIdAdminDecided(Connection connection, String teacherId);

    //根据专业获取志愿
    List<Idea> queryIdeasByMajor(Connection connection, String major);

    //新增志愿
    int insertIdea(Connection connection, Idea Idea);

    //删除志愿
    int deleteIdea(Connection connection, int ideaId);

    //根据工号和学号修改志愿状态
    int updateIdeaStateById(Connection connection, String studentId, String teacherId, int state);

    //根据工号和学号修改志愿导师
    int updateIdeaTeacherByIdeaId(Connection connection, int ideaId, String teacherId);

    //根据工号和学号修改志愿状态
    int updateIdeaStateByIdeaId(Connection connection, int ideaId, int state);

    //根据学号获取志愿
    List<Idea> queryIdeasByStudentIdAndState(Connection connection, String studentId, int state);

    //根据学号获取志愿
    List<Idea> queryIdeasByTeacherIdAndState(Connection connection, String teacherId, int state);

    //根据专业获取志愿
    List<Idea> queryIdeasByMajorAndState(Connection connection, String major, int state);
}
