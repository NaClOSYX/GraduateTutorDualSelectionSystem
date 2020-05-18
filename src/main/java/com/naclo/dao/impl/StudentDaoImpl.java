package com.naclo.dao.impl;

import com.naclo.dao.StudentDao;
import com.naclo.pojo.Student;
import com.naclo.utils.DBUtil;
import com.naclo.utils.MD5Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> queryAllStudents(Connection connection) {
        List<Student> studentList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from student";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setStudentPassword(rs.getString(3));
                    student.setStudentMajor(rs.getString(4));
                    studentList.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return studentList;
    }

    @Override
    public Student queryStudentById(Connection connection, String id) {
        Student student = new Student();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from student where studentId = ?";
            Object[] params = {id};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    student.setStudentId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setStudentPassword(rs.getString(3));
                    student.setStudentMajor(rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return student;
    }


    @Override
    public List<Student> queryStudentByMajor(Connection connection, String major) {
        List<Student> studentList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from student where studentMajor = ?";
            Object[] params = {major};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setStudentPassword(rs.getString(3));
                    student.setStudentMajor(rs.getString(4));
                    studentList.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return studentList;
    }

    @Override
    public List<Student> queryStudentByName(Connection connection, String name) {
        List<Student> studentList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from student where studentName like ?";
            name = "%" + name + "%";
            Object[] params = {name};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setStudentPassword(rs.getString(3));
                    student.setStudentMajor(rs.getString(4));
                    studentList.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return studentList;
    }


    @Override
    public List<Student> queryAllStudentsLimit(Connection connection, int startIndex, int pageSize) {
        List<Student> studentList = new ArrayList<>();
        ResultSet rs = null;
        int start = (startIndex - 1) * pageSize;
        int end = pageSize;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from student limit ?,? ";
            Object[] params = new Object[]{start, end};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    Student student = new Student();
                    student.setStudentId(rs.getString(1));
                    student.setStudentName(rs.getString(2));
                    student.setStudentPassword(rs.getString(3));
                    student.setStudentMajor(rs.getString(4));
                    studentList.add(student);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return studentList;
    }

    @Override
    public int insertStudent(Connection connection, Student student) {
        String password = MD5Utils.stringToMD5(student.getStudentId());
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "insert into student values(?,?,?,?);";
            Object[] params = {student.getStudentId(), student.getStudentName(), password, student.getStudentMajor()};
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
    public int updateStudentPasswordById(Connection connection, String id, String newPwd) {
        String newPassword = MD5Utils.stringToMD5(newPwd);
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update student set studentPassword=? where studentId=?";
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
    public int updateStudentById(Connection connection, Student student) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "update student set studentName=?,studentMajor=? where studentId=?";
            Object[] params = {student.getStudentName(), student.getStudentMajor(), student.getStudentId()};
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
    public int deleteStudentById(Connection connection, String id) {
        PreparedStatement pstm = null;
        int flag = 0;
        if (connection != null) {
            String sql = "delete from student where studentId=?";
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

