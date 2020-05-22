package com.naclo.dao.impl;

import com.naclo.dao.TeacherDao;
import com.naclo.pojo.Teacher;
import com.naclo.utils.DBUtil;
import com.naclo.utils.MD5Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TeacherDaoImpl implements TeacherDao {
    @Override
    public List<Teacher> queryAllTeachers(Connection connection) {
        List<Teacher> teacherList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from teacher";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(rs.getString(1));
                    teacher.setTeacherName(rs.getString(2));
                    teacher.setTeacherPassword(rs.getString(3));
                    teacher.setTeacherMajor(rs.getString(4));
                    teacher.setTeacherIntroduce(rs.getString(5));
                    teacherList.add(teacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return teacherList;
    }

    @Override
    public Teacher queryTeacherById(Connection connection, String id) {
        Teacher teacher = new Teacher();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from teacher where teacherId = ?";
            Object[] params = new Object[]{id};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                if (rs.next()) {
                    teacher.setTeacherId(rs.getString(1));
                    teacher.setTeacherName(rs.getString(2));
                    teacher.setTeacherPassword(rs.getString(3));
                    teacher.setTeacherMajor(rs.getString(4));
                    teacher.setTeacherIntroduce(rs.getString(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return teacher;
    }

    @Override
    public List<Teacher> queryTeacherByMajor(Connection connection, String major) {
        List<Teacher> teacherList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from teacher where teacherMajor = ?";
            Object[] params = new Object[]{major};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(rs.getString(1));
                    teacher.setTeacherName(rs.getString(2));
                    teacher.setTeacherPassword(rs.getString(3));
                    teacher.setTeacherMajor(rs.getString(4));
                    teacher.setTeacherIntroduce(rs.getString(5));
                    teacherList.add(teacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return teacherList;
    }

    @Override
    public Map<String, String> queryTeachersMapByMajor(Connection connection, String major) {
        Map<String, String> teacherMap = new HashMap<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        Object[] params = new Object[]{};
        if (connection != null) {
            String sql = null;
            if ("ALL".equals(major)) {
                sql = "select * from teacher";
            } else {
                sql = "select * from teacher where teacherMajor = ?";
                params = new Object[]{major};
            }
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    String teacherId = rs.getString(1);
                    String teacherName = rs.getString(2);
                    teacherMap.put(teacherId, teacherName);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return teacherMap;
    }

    @Override
    public List<Teacher> queryTeacherByName(Connection connection, String name) {
        List<Teacher> teacherList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from teacher where teacherName like ?";
            name = "%" + name + "%";
            Object[] params = new Object[]{name};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(rs.getString(1));
                    teacher.setTeacherName(rs.getString(2));
                    teacher.setTeacherPassword(rs.getString(3));
                    teacher.setTeacherMajor(rs.getString(4));
                    teacher.setTeacherIntroduce(rs.getString(5));
                    teacherList.add(teacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return teacherList;
    }

    @Override
    public List<Teacher> queryAllTeachersLimit(Connection connection, int startIndex, int pageSize) {
        List<Teacher> teacherList = new ArrayList<>();
        ResultSet rs = null;
        int start = (startIndex - 1) * pageSize;
        int end = pageSize;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from teacher limit ?,?";
            Object[] params = new Object[]{start, end};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setTeacherId(rs.getString(1));
                    teacher.setTeacherName(rs.getString(2));
                    teacher.setTeacherPassword(rs.getString(3));
                    teacher.setTeacherMajor(rs.getString(4));
                    teacher.setTeacherIntroduce(rs.getString(5));
                    teacherList.add(teacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return teacherList;
    }

    @Override
    public int insertTeacher(Connection connection, Teacher teacher) {
        String passwoed = MD5Utils.stringToMD5(teacher.getTeacherId());
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "insert into teacher values(?,?,?,?,?)";
            Object[] params = {teacher.getTeacherId(), teacher.getTeacherName(), passwoed, teacher.getTeacherMajor(), teacher.getTeacherIntroduce()};
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
    public int updateTeacherPasswordById(Connection connection, String id, String newPwd) {
        String newPassword = MD5Utils.stringToMD5(newPwd);
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update teacher set teacherPassword=? where teacherId=?";
            Object[] params = {newPassword, id};
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
    public int updateTeacherById(Connection connection, Teacher teacher) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update teacher set teacherName=?,teacherMajor=?,teacherIntroduce=? where teacherId=?";
            Object[] params = {teacher.getTeacherName(), teacher.getTeacherMajor(), teacher.getTeacherIntroduce(), teacher.getTeacherId()};
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
    public int deleteTeacherById(Connection connection, String id) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "delete from teacher where teacherId=?";
            Object[] paramas = {id};
            try {
                flag = DBUtil.execute(connection, sql, pstm, paramas);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, null);
        return flag;
    }
}
