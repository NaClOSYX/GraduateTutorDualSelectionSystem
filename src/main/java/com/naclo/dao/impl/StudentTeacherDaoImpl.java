package com.naclo.dao.impl;

import com.naclo.dao.StudentTeacherDao;
import com.naclo.pojo.StudentTeacher;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentTeacherDaoImpl implements StudentTeacherDao {

    @Override
    public List<StudentTeacher> getAllStudentTeacher(Connection connection) {
        List<StudentTeacher> studentTeacherList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from studentteachertable";
            try {
                rs = DBUtil.query(connection, sql, pstm, new Object[]{}, rs);
                while (rs.next()) {
                    StudentTeacher studentTeacher = new StudentTeacher();
                    studentTeacher.setMajorName(rs.getString(1));
                    studentTeacher.setStudentId(rs.getString(2));
                    studentTeacher.setStudentName(rs.getString(3));
                    studentTeacher.setTeacherId(rs.getString(4));
                    studentTeacher.setTeacherName(rs.getString(5));
                    studentTeacherList.add(studentTeacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return studentTeacherList;
    }

    @Override
    public List<StudentTeacher> getAllStudentTeacherByMajor(Connection connection, String majorName) {
        List<StudentTeacher> studentTeacherList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstm = null;
        if (connection != null) {
            String sql = "select * from studentteachertable where majorName=?";
            Object[] params = new Object[]{majorName};
            try {
                rs = DBUtil.query(connection, sql, pstm, params, rs);
                while (rs.next()) {
                    StudentTeacher studentTeacher = new StudentTeacher();
                    studentTeacher.setMajorName(rs.getString(1));
                    studentTeacher.setStudentId(rs.getString(2));
                    studentTeacher.setStudentName(rs.getString(3));
                    studentTeacher.setTeacherId(rs.getString(4));
                    studentTeacher.setTeacherName(rs.getString(5));
                    studentTeacherList.add(studentTeacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        DBUtil.closeResource(null, pstm, rs);
        return studentTeacherList;
    }
}
