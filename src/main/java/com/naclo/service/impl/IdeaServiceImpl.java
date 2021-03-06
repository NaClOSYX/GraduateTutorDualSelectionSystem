package com.naclo.service.impl;


import com.naclo.dao.IdeaDao;
import com.naclo.dao.impl.IdeaDaoImpl;
import com.naclo.pojo.Idea;
import com.naclo.service.IdeaService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

public class IdeaServiceImpl implements IdeaService {
    IdeaDao ideaDao = new IdeaDaoImpl();

    @Override
    public List<Idea> queryAllIdeas() {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryAllIdeas(connection);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public Idea queryIdeasByIdeaId(String ideaId) {
        Connection connection = DBUtil.getConnection();
        Idea idea = ideaDao.queryIdeasByIdeaId(connection, ideaId);
        DBUtil.closeResource(connection, null, null);
        return idea;
    }

    @Override
    public List<Idea> queryIdeasByStudentId(String studentId) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByStudentId(connection, studentId);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public int queryIdeasByStudentIdCount(String studentId) {
        Connection connection = DBUtil.getConnection();
        int count = ideaDao.queryIdeasByStudentIdCount(connection, studentId);
        DBUtil.closeResource(connection, null, null);
        return count;
    }

    @Override
    public Idea queryTeacherByStudentId(String studentId) {
        Connection connection = DBUtil.getConnection();
        Idea idea = ideaDao.queryTeacherByStudentId(connection, studentId);
        DBUtil.closeResource(connection, null, null);
        return idea;
    }

    @Override
    public List<Idea> queryIdeasByTeacherId(String teacherId) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByTeacherId(connection, teacherId);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public int queryIdeasByTeacherIdCount(String teacherId) {
        Connection connection = DBUtil.getConnection();
        int count = ideaDao.queryIdeasByTeacherIdCount(connection, teacherId);
        DBUtil.closeResource(connection, null, null);
        return count;
    }

    @Override
    public List<Idea> queryIdeasByTeacherIdDecided(String teacherId) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByTeacherIdDecided(connection, teacherId);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    //根据工号获取管理员确定的志愿
    public List<Idea> queryIdeasByTeacherIdAdminDecided(String teacherId) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByTeacherIdAdminDecided(connection, teacherId);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByMajor(String major) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByMajor(connection, major);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public boolean insertIdea(Idea idea) {
        Connection connection = DBUtil.getConnection();
        int i = ideaDao.insertIdea(connection, idea);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteIdea(int ideaId) {
        Connection connection = DBUtil.getConnection();
        int i = ideaDao.deleteIdea(connection, ideaId);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateIdeaStateById(String studentId, String teacherId, int state) {
        Connection connection = DBUtil.getConnection();
        int i = ideaDao.updateIdeaStateById(connection, studentId, teacherId, state);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateIdeaTeacherByIdeaId(int ideaId, String teacherId) {
        Connection connection = DBUtil.getConnection();
        int i = ideaDao.updateIdeaTeacherByIdeaId(connection, ideaId, teacherId);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateIdeaStateByIdeaId(int ideaId, int state) {
        Connection connection = DBUtil.getConnection();
        int i = ideaDao.updateIdeaStateByIdeaId(connection, ideaId, state);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Idea> queryIdeasByStudentIdAndState(String studentId, int state) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByStudentIdAndState(connection, studentId, state);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByTeacherIdAndState(String teacherId, int state) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByTeacherIdAndState(connection, teacherId, state);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByMajorAndState(String major, int state) {
        Connection connection = DBUtil.getConnection();
        List<Idea> ideaList = ideaDao.queryIdeasByMajorAndState(connection, major, state);
        DBUtil.closeResource(connection, null, null);
        return ideaList;
    }
}
