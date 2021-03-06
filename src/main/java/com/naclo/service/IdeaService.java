package com.naclo.service;

import com.naclo.pojo.Idea;

import java.util.List;


public interface IdeaService {
    //获取所有志愿
    List<Idea> queryAllIdeas();

    //根据志愿号获取志愿
    Idea queryIdeasByIdeaId(String ideaId);

    //根据学号获取志愿
    List<Idea> queryIdeasByStudentId(String studentId);

    //根据学号获取志愿数量
    int queryIdeasByStudentIdCount(String studentId);

    //根据学号获取老师确定志愿
    Idea queryTeacherByStudentId(String studentId);

    //根据工号获取志愿
    List<Idea> queryIdeasByTeacherId(String teacherId);

    //根据工号获取志愿数量
    int queryIdeasByTeacherIdCount(String teacherId);

    //根据工号获取确定的志愿
    List<Idea> queryIdeasByTeacherIdDecided(String teacherId);

    //根据工号获取管理员确定的志愿
    List<Idea> queryIdeasByTeacherIdAdminDecided(String teacherId);

    //根据专业获取志愿
    List<Idea> queryIdeasByMajor(String major);

    //新增志愿
    boolean insertIdea(Idea idea);

    //删除志愿
    boolean deleteIdea(int ideaId);

    //根据工号和学号修改志愿状态
    boolean updateIdeaStateById(String studentId, String teacherId, int state);

    //根据工号和学号修改志愿导师
    boolean updateIdeaTeacherByIdeaId(int ideaId, String teacherId);

    //根据工号和学号修改志愿状态
    boolean updateIdeaStateByIdeaId(int ideaId, int state);

    //根据学号获取志愿
    List<Idea> queryIdeasByStudentIdAndState(String studentId, int state);

    //根据学号获取志愿
    List<Idea> queryIdeasByTeacherIdAndState(String teacherId, int state);

    //根据专业获取志愿
    List<Idea> queryIdeasByMajorAndState(String major, int state);
}