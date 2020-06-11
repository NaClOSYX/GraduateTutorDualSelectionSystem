package com.naclo.dao.impl;


import com.naclo.dao.IdeaDao;
import com.naclo.pojo.Idea;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IdeaDaoImpl implements IdeaDao {
    @Override
    public List<Idea> queryAllIdeas(Connection connection) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from idea";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByStudentId(Connection connection, String studentId) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询学生的所有志愿
            String sql = "select * from idea where studentId=?";
            Object[] params = new Object[]{studentId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }

    @Override
    public int queryIdeasByStudentIdCount(Connection connection, String studentId) {
        int count = 0;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询正常志愿的学生
            String sql = "select count(*) from idea where studentId=?";
            Object[] params = new Object[]{studentId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return count;
    }

    @Override
    public Idea queryTeacherByStudentId(Connection connection, String studentId) {
        Idea idea = new Idea();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询学生的所有志愿
            String sql = "select * from idea where studentId=? and state=2";
            Object[] params = new Object[]{studentId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                if (rs.next()) {
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return idea;
    }

    @Override
    public List<Idea> queryIdeasByTeacherId(Connection connection, String teacherId) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询正常志愿的老师
            String sql = "select * from idea where teacherId=? and state=1";
            Object[] params = new Object[]{teacherId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }

    @Override
    public int queryIdeasByTeacherIdCount(Connection connection, String teacherId) {
        int count = 0;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询老师的所有学生
            String sql = "select count(*) from idea where teacherId=? and state in (2,5)";
            Object[] params = new Object[]{teacherId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    count = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return count;
    }

    @Override
    public List<Idea> queryIdeasByTeacherIdDecided(Connection connection, String teacherId) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询正常志愿的老师
            String sql = "select * from idea where teacherId=? and state=2";
            Object[] params = new Object[]{teacherId};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByMajor(Connection connection, String major) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from idea where majorName=? and state=1";
            Object[] params = new Object[]{major};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }


    @Override
    public int insertIdea(Connection connection, Idea idea) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "insert into idea values(null,?,?,?,?,?);";
            Object[] params = {idea.getMajorName(), idea.getStudentId(), idea.getTeacherId(), idea.getTime(), idea.getState()};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }


    @Override
    public int updateIdeaStateById(Connection connection, String studentId, String teacherId, int state) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update idea set state=? where studentId=? and teacherId=?";
            Object[] params = new Object[]{state, studentId, teacherId};
            if (null == teacherId) {
                sql = "update idea set state=? where studentId=?";
                params = new Object[]{state, studentId};
            }
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }

    @Override
    public int updateIdeaStateByIdeaId(Connection connection, int ideaId, int state) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update idea set state=? where ideaId=?";
            Object[] params = new Object[]{state, ideaId};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }

    @Override
    public List<Idea> queryIdeasByStudentIdAndState(Connection connection, String studentId, int state) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询学生的所有志愿
            String sql = "";
            Object[] params = null;
            if (state == -1) {
                sql = "select * from idea where studentId=?";
                params = new Object[]{studentId};
            } else {
                sql = "select * from idea where studentId=? and state=?";
                params = new Object[]{studentId, state};
            }
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByTeacherIdAndState(Connection connection, String teacherId, int state) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询学生的所有志愿
            String sql = "";
            Object[] params = null;
            if (state == -1) {
                sql = "select * from idea where teacherId=?";
                params = new Object[]{teacherId};
            } else {
                sql = "select * from idea where teacherId=? and state=?";
                params = new Object[]{teacherId, state};
            }
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }

    @Override
    public List<Idea> queryIdeasByMajorAndState(Connection connection, String major, int state) {
        List<Idea> ideaList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            //查询学生的所有志愿
            String sql = "";
            Object[] params = null;
            if (state == -1) {
                sql = "select * from idea where majorName=?";
                params = new Object[]{major};
            } else {
                sql = "select * from idea where majorName=? and state=?";
                params = new Object[]{major, state};
            }
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Idea idea = new Idea();
                    idea.setIdeaId(rs.getInt(1));
                    idea.setMajorName(rs.getString(2));
                    idea.setStudentId(rs.getString(3));
                    idea.setTeacherId(rs.getString(4));
                    idea.setTime(rs.getTimestamp(5));
                    idea.setState(rs.getInt(6));
                    ideaList.add(idea);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return ideaList;
    }
}
