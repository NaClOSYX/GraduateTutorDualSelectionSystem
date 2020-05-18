package com.naclo.service.impl;

import com.naclo.dao.StudentDao;
import com.naclo.dao.impl.StudentDaoImpl;
import com.naclo.pojo.Student;
import com.naclo.service.StudentService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;


public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> queryAllStudents() {
        Connection connection = DBUtil.getConnection();
        List<Student> studentList = studentDao.queryAllStudents(connection);
        DBUtil.closeResource(connection, null, null);
        return studentList;
    }

    @Override
    public Student queryStudentById(String id) {
        Connection connection = DBUtil.getConnection();
        Student student = studentDao.queryStudentById(connection, id);
        DBUtil.closeResource(connection, null, null);
        return student;
    }

    @Override
    public List<Student> queryStudentByMajor(String major) {
        Connection connection = DBUtil.getConnection();
        List<Student> studentList = studentDao.queryStudentByMajor(connection, major);
        DBUtil.closeResource(connection, null, null);
        return studentList;
    }

    @Override
    public List<Student> queryStudentByName(String name) {
        Connection connection = DBUtil.getConnection();
        List<Student> studentList = studentDao.queryStudentByName(connection, name);
        DBUtil.closeResource(connection, null, null);
        return studentList;
    }


    @Override
    public List<Student> queryAllStudentsLimit(int startIndex, int pageSize) {
        Connection connection = DBUtil.getConnection();
        List<Student> studentList = studentDao.queryAllStudentsLimit(connection, startIndex, pageSize);
        DBUtil.closeResource(connection, null, null);
        return studentList;
    }

    @Override
    public boolean insertStudent(Student student) {
        Connection connection = DBUtil.getConnection();
        int i = studentDao.insertStudent(connection, student);
        DBUtil.closeResource(connection, null, null);
        if (i > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateStudentPasswordById(String id, String newPwd) {
        Connection connection = DBUtil.getConnection();
        int i = studentDao.updateStudentPasswordById(connection, id, newPwd);
        DBUtil.closeResource(connection, null, null);
        if (i > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean updateStudentById(Student student) {
        Connection connection = DBUtil.getConnection();
        int i = studentDao.updateStudentById(connection, student);
        DBUtil.closeResource(connection, null, null);
        if (i > 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteStudentById(String id) {
        Connection connection = DBUtil.getConnection();
        int i = studentDao.deleteStudentById(connection, id);
        DBUtil.closeResource(connection, null, null);
        if (i > 0)
            return true;
        else
            return false;
    }
}

