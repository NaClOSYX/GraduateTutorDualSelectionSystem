package com.naclo.dao.impl;

import com.naclo.dao.MajorDao;
import com.naclo.pojo.Major;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MajorDaoImpl implements MajorDao {
    @Override
    public List<Major> queryAllMajors(Connection connection) {
        List<Major> majorList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from major";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Major major = new Major();
                    major.setMajorId(rs.getInt(1));
                    major.setMajorName(rs.getString(2));
                    major.setStudentMax(rs.getInt(3));
                    majorList.add(major);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return majorList;
    }

    @Override
    public List<Major> queryAllMajorsExceptALL(Connection connection) {
        List<Major> majorList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from major where majorName <>'ALL'";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Major major = new Major();
                    major.setMajorId(rs.getInt(1));
                    major.setMajorName(rs.getString(2));
                    major.setStudentMax(rs.getInt(3));
                    majorList.add(major);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return majorList;
    }

    @Override
    public List<Major> queryMajorByName(Connection connection, String name) {
        List<Major> majorList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from major where majorName=?";
            Object[] params = new Object[]{name};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Major major = new Major();
                    major.setMajorId(rs.getInt(1));
                    major.setMajorName(rs.getString(2));
                    major.setStudentMax(rs.getInt(3));
                    majorList.add(major);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return majorList;
    }


    @Override
    public Major queryMajorById(Connection connection, int id) {
        Major major = new Major();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from major where majorId=?";
            Object[] params = new Object[]{id};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    major.setMajorId(rs.getInt(1));
                    major.setMajorName(rs.getString(2));
                    major.setStudentMax(rs.getInt(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return major;
    }

    @Override
    public int queryMajorMaxStudents(Connection connection, String name) {
        int count = 0;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from major where majorName=?";
            Object[] params = new Object[]{name};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                if (rs.next()) {
                    count = rs.getInt(3);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return count;
    }

    @Override
    public int insertMajor(Connection connection, Major major) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "insert into major values(null,?,?);";
            Object[] params = {major.getMajorName(), major.getStudentMax()};
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
    public int updateMajor(Connection connection, Major major) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update major set majorName=?,studentMax=? where majorId=?";
            Object[] params = {major.getMajorName(), major.getStudentMax(), major.getMajorId()};
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
    public int deleteMajorById(Connection connection, int id) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "delete from major where majorId=?";
            Object[] params = {id};
            try {
                flag = DBUtil.execute(connection, sql, pstm, params);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }
}
