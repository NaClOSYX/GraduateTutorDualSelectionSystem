package com.naclo.service.impl;

import com.naclo.dao.StudentTeacherDao;
import com.naclo.dao.impl.StudentTeacherDaoImpl;
import com.naclo.pojo.StudentTeacher;
import com.naclo.service.StudentTeacherService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Author NaClO
 * @create 2020/5/26 16:18
 */
public class StudentTeacherServiceImpl implements StudentTeacherService {
    StudentTeacherDao studentTeacherDao = new StudentTeacherDaoImpl();

    @Override
    public List<StudentTeacher> getAllStudentTeacher() {
        Connection connection = DBUtil.getConnection();
        List<StudentTeacher> studentTeacherList = studentTeacherDao.getAllStudentTeacher(connection);
        DBUtil.closeResource(connection, null, null);
        return studentTeacherList;
    }

    @Override
    public List<StudentTeacher> getAllStudentTeacherByMajor(String majorName) {
        Connection connection = DBUtil.getConnection();
        List<StudentTeacher> studentTeacherList = studentTeacherDao.getAllStudentTeacherByMajor(connection, majorName);
        DBUtil.closeResource(connection, null, null);
        return studentTeacherList;
    }
}
